/**
 *
 * Created on 2009-4-22
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import javax.swing.JPanel;

import org.nfunk.jep.JEP;

import bsh.Interpreter;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.entity.BmsTpColDim;
import com.sinosoft.bms.entity.BmsTpRowDim;
import com.sinosoft.bms.formula.FormulaTools;
import com.sinosoft.bms.funcs.FuncCol;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.BmsDialog;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.valueobject.BmsDouble;
import com.sinosoft.bms.valueobject.ColTypeVO;
import com.sinosoft.bms.valueobject.TempleteVO;
import com.sinosoft.bmscell.base.Constants;
import com.sinosoft.bmscell.report.CellElement;
import com.sinosoft.bmscell.report.CellStyle;
import com.sinosoft.bmscell.report.Report;
import com.sinosoft.bmscell.report.SpreadSheet;
import com.sinosoft.bmscell.report.event.CellElementEvent;
import com.sinosoft.bmscell.report.event.CellElementListener;
import com.sinosoft.bmscell.report.gui.CellSelection;
import com.sinosoft.bmscell.report.gui.event.ReportDataChangeEvent;
import com.sinosoft.bmscell.report.gui.event.ReportDataChangeListener;
import com.sinosoft.bmscell.report.io.TemplateExporter;
import com.sinosoft.bmscell.report.io.TemplateImporter;

/**
 * @author sunrui
 *
 */
public class TpDesignPanel extends JPanel {

	public DesignSpreadSheetPane sheetPanel = new DesignSpreadSheetPane();
	
	public BmsTemplet templete = null;
	
	public Templete tpBean = null;
	
	public TpPropDlg propDlg = null;
	
	public TempleteVO templeteVO = null;
	
	//public Interpreter bsh = new Interpreter();
	
	/**
	 * 
	 */
	public TpDesignPanel() {
		init();
	}
	public void init() {
		this.setLayout(new BorderLayout());
		this.add(sheetPanel,BorderLayout.CENTER);
		try {
			tpBean = (Templete)ClientBeanFactory.getBean("Templete");
		} catch (Exception ex) {
			ex.printStackTrace();
			ClientUtils.showErrMsg(this,ex.getMessage());
		}
		
		
		
	}
	
	public void saveTemplete() throws Exception {
		String fileName = "tp"+templete.getTpId()+".xml";
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		TemplateExporter exp = new TemplateExporter(bout);
		exp.exportReport(sheetPanel.getReport());
		byte [] bytes = bout.toByteArray();
		
		tpBean.saveFile(fileName, bytes);
		
		tpBean.update(templete);
		
		//保存行维度信息
		if(propDlg!=null) {
			BmsTpRowDim [] rowDims = propDlg.getRowDimPanel().getRowDims();
			for (int i = 0; i < rowDims.length; i++) {
				rowDims[i].setBmsTemplet(templete);
			}
			
			BmsTpColDim [] colDims = propDlg.getColDimPanel().getTpColDims();
			for (int i = 0; i < colDims.length; i++) {
				colDims[i].setBmsTemplet(templete);
			}
			
			tpBean.saveTpRowDim(templete.getTpId(), rowDims);
			tpBean.saveTpColDim(templete.getTpId(), colDims);
		}
	}
	
	public void onClear() throws Exception {
		Report report = sheetPanel.getReport();
		
		if(templete.getDataStartRow()!=null && templete.getDataEndRow()!=null) {
			int tpStartRow = templete.getDataStartRow().intValue();
			int tpEndRow = templete.getDataEndRow().intValue();
			int removeRowCount = tpEndRow-tpStartRow+1;
			for(int i=0;i<removeRowCount;i++) {
				report.removeRow(tpStartRow);
			}
			templete.setDataStartCol(null);
			templete.setDataEndCol(null);
			templete.setDataStartRow(null);
			templete.setDataEndRow(null);
			sheetPanel.repaint();
		}
	}
	
	public void onProp() throws Exception {
		if(propDlg==null) {
			propDlg = new TpPropDlg(this);
			propDlg.setTemplet(templete,sheetPanel);
			//加载行维度
			BmsTpRowDim [] rowDims = tpBean.queryBmsRowDim(templete.getTpId());
			propDlg.getRowDimPanel().setRowDims(rowDims);
			
			BmsTpColDim [] colDims = tpBean.queryBmsColDim(templete.getTpId());
			propDlg.getColDimPanel().setTpColDims(colDims);
		}
		
		
		CellSelection cellselection = sheetPanel.getCellSelection();
		int col = cellselection.getEditColumn();
		int row = cellselection.getEditRow();
		
		propDlg.setCurrPostion(col,row);
		
		if(propDlg.showModal()==BmsDialog.ID_OK) {
			
		}
	}
	
	public void loadTemplete(BmsTemplet tp) throws Exception {
		templete = tp;
		this.remove(sheetPanel);
		sheetPanel = new DesignSpreadSheetPane();
		this.add(sheetPanel,BorderLayout.CENTER);
		sheetPanel.setReport(new SpreadSheet());
		
		
		
		
		try {
			propDlg = null;
			
			String fileName = "tp"+templete.getTpId()+".xml";
			byte [] bytes = tpBean.loadFile(fileName);
			ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
			TemplateImporter imp = new TemplateImporter(bin);
			Report report = imp.generateReport();
			sheetPanel.setReport(report);
			
		} catch (java.io.FileNotFoundException ex) {
		}
		
		sheetPanel.addReportDataChangeListener(new ReportDataChangeListener(){

			public void reportDataChanged(ReportDataChangeEvent reportdatachangeevent) {
				System.out.println("=====Report Data Changed=====");
				try {
					TpDesignPanel.this.executeFormula();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}}
		);
			
		
	}
	/**
	 * @return the sheetPanel
	 */
	public DesignSpreadSheetPane getSheetPanel() {
		return sheetPanel;
	}
	/**
	 * @param sheetPanel the sheetPanel to set
	 */
	public void setSheetPanel(DesignSpreadSheetPane sheetPanel) {
		this.sheetPanel = sheetPanel;
	}

	public void onPrint() throws Exception {
		sheetPanel.getReport().print(false);
	}
	
	public void clearData() throws Exception {
		Report report = sheetPanel.getReport();
		
		BmsTpColDim [] colDims = templeteVO.getColDims();
		BmsTpRowDim [] rowDims = templeteVO.getRowDims();
		
		BmsTemplet tp = templeteVO.getBmsTemplet();
		
		int startRow = tp.getDataStartRow().intValue();
		int startCol = tp.getDataStartCol().intValue();
		
		CellStyle cs = new CellStyle();
		cs.setBorderLeft(1);
		cs.setBorderRight(1);
		cs.setBorderTop(1);
		cs.setBorderBottom(1);
		cs.setHorizontalAlignment(Constants.RIGHT);
		
		for (int i = 0; i < rowDims.length; i++) {
			for (int j = 0; j < colDims.length; j++) {
				int row = i+1+startRow;
				int col = j+2+startCol;
				
				CellElement ce = new CellElement(col,row);
				ce.setCellStyle(cs);
				report.addCellElement(ce);
				
			}
		}
		
		sheetPanel.revalidate();
		sheetPanel.repaint();
		
	}
	
	public void executeFormula() throws Exception {
		Report report = sheetPanel.getReport();
		
		BmsTpColDim [] colDims = templeteVO.getColDims();
		BmsTpRowDim [] rowDims = templeteVO.getRowDims();
		
		BmsTemplet tp = templeteVO.getBmsTemplet();
		
		int startRow = tp.getDataStartRow().intValue();
		int startCol = tp.getDataStartCol().intValue();
		
		CellStyle cs = new CellStyle();
		cs.setBorderLeft(1);
		cs.setBorderRight(1);
		cs.setBorderTop(1);
		cs.setBorderBottom(1);
		cs.setHorizontalAlignment(Constants.RIGHT);
		
		HashMap hmCol = new HashMap();
		
		for (int i = 0; i < colDims.length; i++) {
			int col = i+2+startCol;
			hmCol.put(colDims[i].getColCode(), new Integer(col));
		}
		
		FuncCol funcCol = new FuncCol();
		funcCol.setSheetPanel(sheetPanel);
		funcCol.setHmCol(hmCol);
		funcCol.setTvo(templeteVO);
		
		
		
		JEP jep = new JEP();
		jep.addStandardConstants();
		jep.addStandardFunctions();
		jep.addFunction("col", funcCol);
		
		
		for (int i = 0; i < rowDims.length; i++) {
			for (int j = 0; j < colDims.length; j++) {
				
				int row = i+1+startRow;
				int col = j+2+startCol;
				
				if(colDims[j].getColType()==null) continue;
				int coltype = colDims[j].getColType().intValue();
				if(coltype==ColTypeVO.CT_DATABASE||coltype==ColTypeVO.CT_ACTUAL) {
					continue;
				} else if(coltype==ColTypeVO.CT_GENERAL) {
					
					CellElement ce = report.getCellElement(col, row);
					Object obj = (ce==null)?null:ce.getValue();
					BmsDouble value = new BmsDouble(obj==null?"0":obj.toString());
					ce.setValue(value.toString());
					ce.setEditable(true);
					ce.setCellStyle(cs);
					continue;
				}
				
				
				
				
				
				String formula = colDims[j].getFormula();
				
				double result = 0.0d;
				
				if(formula!=null && formula.trim().length()>0) {
					//String script = FormulaTools.getClientScript(colDims[j].getFormula());
					
//					bsh.set("tvo", templeteVO);
//					bsh.set("sheetPanel", sheetPanel);
//					bsh.set("hmCol", hmCol);
//					bsh.set("row", row);
//					bsh.eval(script);
					
//					result = ((Double)bsh.get("result")).doubleValue();
					
					
					funcCol.setRow(row);
					
					jep.parseExpression(formula);
					if(jep.hasError()) {
						throw new Exception("公式解析发生错误："+jep.getErrorInfo());
					}
					
					result = jep.getValue();
					
					
					CellElement ce = new CellElement(col,row,(new BmsDouble(result)).toString());
					ce.setCellStyle(cs);
					ce.setEditable(false);
					report.addCellElement(ce);
				} else {
					CellElement ce = report.getCellElement(col, row);
					ce.setCellStyle(cs);
					if(ce!=null) {
						ce.setEditable(true);
					}
				}
				
				
				
			}
		}
		
		sheetPanel.revalidate();
		sheetPanel.repaint();
		
		
	}
	/**
	 * @return the templeteVO
	 */
	public TempleteVO getTempleteVO() {
		return templeteVO;
	}
	/**
	 * @param templeteVO the templeteVO to set
	 */
	public void setTempleteVO(TempleteVO templeteVO) {
		this.templeteVO = templeteVO;
	}
	
	
}
