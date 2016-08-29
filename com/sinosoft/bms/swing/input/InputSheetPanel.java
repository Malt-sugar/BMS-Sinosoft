/**
 *
 * Created on 2009-4-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.input;

import java.awt.BorderLayout;
import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JPanel;

import org.nfunk.jep.JEP;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.bms.entity.BmsSheetItem;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.entity.BmsTpBgObj;
import com.sinosoft.bms.entity.BmsTpColDim;
import com.sinosoft.bms.entity.BmsTpParamDim;
import com.sinosoft.bms.entity.BmsTpRowDim;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.funcs.FuncCol;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.ClientEnv;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.valueobject.BmsDouble;
import com.sinosoft.bms.valueobject.CellVO;
import com.sinosoft.bms.valueobject.ColTypeVO;
import com.sinosoft.bms.valueobject.SheetVO;
import com.sinosoft.bms.valueobject.TempleteVO;
import com.sinosoft.bmscell.base.Constants;
import com.sinosoft.bmscell.report.CellElement;
import com.sinosoft.bmscell.report.CellStyle;
import com.sinosoft.bmscell.report.Report;
import com.sinosoft.bmscell.report.SpreadSheet;
import com.sinosoft.bmscell.report.gui.event.ReportDataChangeEvent;
import com.sinosoft.bmscell.report.gui.event.ReportDataChangeListener;
import com.sinosoft.bmscell.report.io.TemplateImporter;

/**
 * @author sunrui
 *
 */
public class InputSheetPanel extends JPanel {

	
	public InputGrid inputGrid = new InputGrid();
	public InputSpreadSheetPane sheetPanel = new InputSpreadSheetPane(new SpreadSheet(),inputGrid);
	
	public Templete tpBean = null;
	public BmsTemplet templete = null;
	
	public TempleteVO tvo = null;
	
	public SheetVO sheetvo = null;
	
	/**
	 * 
	 */
	public InputSheetPanel() {
		super();
		init();
	}
	
	public void init() {
		this.setLayout(new BorderLayout());
		this.add(sheetPanel,BorderLayout.CENTER);
		try {
			tpBean = (Templete)ClientBeanFactory.getBean("Templete");
			
			sheetPanel.addReportDataChangeListener(new ReportDataChangeListener(){

				public void reportDataChanged(ReportDataChangeEvent reportdatachangeevent) {
					System.out.println("====resport data change====");
					try {
						executeFormula();
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				
			});
			
		} catch (Exception ex) {
			ex.printStackTrace();
			ClientUtils.showErrMsg(this,ex.getMessage());
		}
	}
	
//	public void saveTemplete() throws Exception {
//		String fileName = "tp"+templete.getTpId()+".xml";
//		ByteArrayOutputStream bout = new ByteArrayOutputStream();
//		TemplateExporter exp = new TemplateExporter(bout);
//		exp.exportReport(sheetPanel.getReport());
//		byte [] bytes = bout.toByteArray();
//		tpBean.saveFile(fileName, bytes);
//	}
	
	public void saveData() throws Exception {
		//保存数据
		
		//保存预算表
		
		int sheetid = 0;
		
		if(sheetvo.getBmsSheet()==null) {
			BmsSheet bmsSheet = new BmsSheet();
			bmsSheet.setSheetCode(sheetvo.getSheetCode());
			bmsSheet.setSheetTitle(sheetvo.getSheetName());
			bmsSheet.setBmsTemplet(new BmsTemplet(sheetvo.getTpID()));
			bmsSheet.setBmsTpParamDim(new BmsTpParamDim(sheetvo.getTpParamID()));
			bmsSheet.setAddUser(new Integer(ClientEnv.getInstance().getUserID()));
			bmsSheet.setRepFlag(BmsUtils.boolToChar(false));
			bmsSheet.setApprFlag(BmsUtils.boolToChar(false));
			bmsSheet.setBmsObject(new BmsObject(sheetvo.getBgObjID()));
			bmsSheet.setIsGatherSheet(BmsUtils.boolToChar(false));
			bmsSheet.setGatheredFlag(BmsUtils.boolToChar(false));
			
			sheetid = tpBean.insertSheet(bmsSheet).intValue();
			sheetvo.setBmsSheet(new BmsSheet(sheetid));
		} else {
			sheetid = sheetvo.getBmsSheet().getSheetId();
		}
		
		//保存预算表项目
		Report report = sheetPanel.getReport();
		
		BmsTpColDim [] colDims = tvo.getColDims();
		BmsTpRowDim [] rowDims = tvo.getRowDims();
		
		BmsTemplet tp = tvo.getBmsTemplet();
		
		int startRow = tp.getDataStartRow().intValue();
		int startCol = tp.getDataStartCol().intValue();
		
		Vector vSheetItem = new Vector();
		
		for (int i = 0; i < rowDims.length; i++) {
			for (int j = 0; j < colDims.length; j++) {
				int row = i+1+startRow;
				int col = j+2+startCol;
				
				CellElement ce = report.getCellElement(col, row);
				
				BmsDouble value = new BmsDouble(ce.getValue()==null?"0":ce.getValue().toString());
				
				BmsSheetItem bsi = new BmsSheetItem();
				bsi.setBmsItem(new BmsItem(rowDims[i].getBmsItem().getItemId()));
				bsi.setBmsObject(new BmsObject(sheetvo.getBgObjID()));
				bsi.setBmsSheet(new BmsSheet(sheetid));
				bsi.setBmsTemplet(new BmsTemplet(sheetvo.getTpID()));
				bsi.setBmsTpColDim(colDims[j]);
				bsi.setBmsTpRowDim(rowDims[i]);
				bsi.setByFormulaFlag(BmsUtils.boolToChar(false));
				bsi.setPlanValFlag(BmsUtils.boolToChar(colDims[j].getColType().intValue()==ColTypeVO.CT_PLAN));
				
				bsi.setItemVal(new BigDecimal(value.doubleValue()));
				
				vSheetItem.add(bsi);
			}
		}
		
		BmsSheetItem [] sheetItems = new BmsSheetItem[vSheetItem.size()];
		vSheetItem.copyInto(sheetItems);
		tpBean.saveSheetItems(sheetid, sheetItems);
		
	}
	
	public void executeFormula() throws Exception {
		if(tvo==null) return;
		Report report = sheetPanel.getReport();
		
		BmsTpColDim [] colDims = tvo.getColDims();
		BmsTpRowDim [] rowDims = tvo.getRowDims();
		
		BmsTemplet tp = tvo.getBmsTemplet();
		
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
		funcCol.setTvo(tvo);
		
		
		
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
	
	public void loadTemplete(BmsTemplet tp,SheetVO sheetvo) throws Exception {
		templete = tp;
		this.sheetvo=sheetvo;
		
		SpreadSheet ss = new SpreadSheet();
		
		
		sheetPanel.setReport(ss);
		
		
		
		
		try {
			
			//加载模板
			String fileName = "tp"+templete.getTpId()+".xml";
			byte [] bytes = tpBean.loadFile(fileName);
			ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
			TemplateImporter imp = new TemplateImporter(bin);
			Report report = imp.generateReport();
			sheetPanel.setReport(report);
			
			tvo = new TempleteVO(tp);
			int tpID = tp.getTpId();
			tvo.setColDims(tpBean.queryBmsColDim(tpID));
			tvo.setRowDims(tpBean.queryBmsRowDim(tpID));
			
			
			int startRow = tp.getDataStartRow().intValue();
			int startCol = tp.getDataStartCol().intValue();
			
			//加载数据
			
			CellStyle cs = new CellStyle();
			cs.setBorderLeft(1);
			cs.setBorderRight(1);
			cs.setBorderTop(1);
			cs.setBorderBottom(1);
			cs.setHorizontalAlignment(Constants.RIGHT);
			
			if(sheetvo.bmsSheetItems!=null) {
				
				BmsTpColDim [] colDims = tvo.getColDims();
				BmsTpRowDim [] rowDims = tvo.getRowDims();
				BmsSheetItem [] sheetItems = sheetvo.bmsSheetItems;
				
				HashMap hmSheetItem = new HashMap();
				
				for (int i = 0; i < sheetItems.length; i++) {
					String key = sheetItems[i].getBmsTpRowDim().getTpRowDimId()+":"+
						sheetItems[i].getBmsTpColDim().getTpColDimId();
					hmSheetItem.put(key, sheetItems[i]);
				}
				
				for (int i = 0; i < rowDims.length; i++) {
					for (int j = 0; j < colDims.length; j++) {
						int row = i+1+startRow;
						int col = j+2+startCol;
						
						String key = rowDims[i].getTpRowDimId()+":"+colDims[j].getTpColDimId();
						Object objSheetItem = hmSheetItem.get(key);
						double value = 0.0d;
						if(objSheetItem!=null) {
							BmsSheetItem sheetItem = (BmsSheetItem)objSheetItem;
							value = sheetItem.getItemVal().doubleValue();
						}
						BmsDouble dval = new BmsDouble(value);
						CellElement ce = new CellElement(col,row,dval.toString());
						ce.setCellStyle(cs);
						report.addCellElement(ce);
						
					}
				}
				
			} else {
			
				//从数据库加载数据
				CellVO [] cells = tpBean.queryCellVO(tvo, sheetvo.getBgObjID(), sheetvo.getDimMemID());
				
				for (int i = 0; i < cells.length; i++) {
					CellElement ce = new CellElement(cells[i].getCol(),cells[i].getRow(),cells[i].getValue().toString());
					sheetPanel.getReport().addCellElement(ce);
					ce.setCellStyle(cs);
				}
				
				//加载表内公式
				executeFormula();
			}
			
			
			//设置可编辑区域
			int dataStartCol = tp.getDataEndCol().intValue();
			boolean [] colEditable = new boolean[tvo.getColDims().length+dataStartCol+2];
			BmsTpColDim [] colDims = tvo.getColDims();
			for (int i = 0; i < colDims.length; i++) {
				if(colDims[i].getFormula()==null || colDims[i].getFormula().trim().length()==0) {
					colEditable[i+dataStartCol+2]=true;
				} else {
					colEditable[i+dataStartCol+2]=false;
				}
			}
			
			inputGrid.setColEditable(colEditable);
			inputGrid.setEditStartRow(tp.getDataStartRow().intValue());
			inputGrid.setEditEndRow(tp.getDataEndRow().intValue());
			
			sheetPanel.revalidate();
			sheetPanel.repaint();
			
		} catch (java.io.FileNotFoundException ex) {
		}
		
	}



}
