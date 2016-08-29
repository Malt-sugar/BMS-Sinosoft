/**
 *
 * Created on 2009-4-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.entity.BmsTpColDim;
import com.sinosoft.bms.service.bd.BmsItemObj;
import com.sinosoft.bms.service.bd.Dim;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.BmsButton;
import com.sinosoft.bms.swing.common.BmsDialog;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.valueobject.ItemVO;
import com.sinosoft.bmscell.report.CellElement;
import com.sinosoft.bmscell.report.CellStyle;
import com.sinosoft.bmscell.report.Report;
import com.sinosoft.bmscell.report.background.ColorBackground;

/**
 * @author sunrui
 *
 */
public class TpPropDlg extends BmsDialog {

	public JPanel contentPanel = new JPanel();
	public JTabbedPane tabPane = new JTabbedPane();
	
	public PropPanel propPanel = new PropPanel();
	public RowDimPanel rowDimPanel = new RowDimPanel();
	public ColDimPanel colDimPanel = new ColDimPanel();
		
	public JPanel bottomPanel = new JPanel();
	public BmsButton btnOK = new BmsButton("确定");
	public BmsButton btnCancel = new BmsButton("取消");
	
	public BmsTemplet templet = null;
	public Templete tpBean = null;
	public BmsItemObj itemBean = null;
	public Dim dimBean = null;
	public DesignSpreadSheetPane sheetPanel = null;
	
	/**
	 * @throws HeadlessException
	 */
	public TpPropDlg() throws HeadlessException {
		super();
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public TpPropDlg(Dialog owner) throws HeadlessException {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public TpPropDlg(Frame owner) throws HeadlessException {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 */
	public TpPropDlg(Component owner) {
		super(owner);
		init();
	}
	
	public void init() {
		try {
			this.setSize(500,400);
			this.setTitle("数据区属性");
			
			tpBean = (Templete) ClientBeanFactory.getBean("Templete");
			itemBean = (BmsItemObj) ClientBeanFactory.getBean("BmsItemObj");
			dimBean = (Dim) ClientBeanFactory.getBean("Dim");
			
			setContentPane(contentPanel);
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(bottomPanel,BorderLayout.SOUTH);
			contentPanel.add(tabPane,BorderLayout.CENTER);
			
			tabPane.addTab("常规", propPanel);
			tabPane.addTab("行维度", rowDimPanel);
			tabPane.addTab("列维度", colDimPanel);
			
			bottomPanel.setPreferredSize(new Dimension(40,40));
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
			
			bottomPanel.add(btnOK);
			bottomPanel.add(btnCancel);
			
			btnOK.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					onOK();
				}
			});
			
			btnCancel.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					onCancel();
				}
				
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}


	public void onOK() {
		
		try {
			
			ItemVO [] itemvos = rowDimPanel.getSelectedItems();
			
			
			Report report = sheetPanel.getReport();
			
			if(templet.getDataStartRow()!=null && templet.getDataEndRow()!=null) {
				int tpStartRow = templet.getDataStartRow().intValue();
				int tpEndRow = templet.getDataEndRow().intValue();
				int removeRowCount = tpEndRow-tpStartRow+1;
				for(int i=0;i<removeRowCount;i++) {
					report.removeRow(tpStartRow);
				}
			}
			
			BmsTpColDim [] colDims = getColDimPanel().getTpColDims();
			
			
			int startRow = propPanel.getRow();
			int startCol = propPanel.getCol();
			
			int row = startRow;
			int col = startCol;
			
			//添加表头
			report.insertRow(row);
			CellStyle csHeader = new CellStyle();
			csHeader.setBorderLeft(1);
			csHeader.setBorderRight(1);
			csHeader.setBorderTop(1);
			csHeader.setBorderBottom(1);
			csHeader.setBackground(new ColorBackground(new Color(230,230,230)));
			
			CellElement ceheader1 = new CellElement(col,row,"预算项目编码");
			CellElement ceheader2 = new CellElement(col+1,row,"预算项目名称");
			//CellElement ceheader3 = new CellElement(col+2,row,"计划值");
			//CellElement ceheader4 = new CellElement(col+3,row,"实际值");
			
			ceheader1.setCellStyle(csHeader);
			ceheader2.setCellStyle(csHeader);
			//ceheader3.setCellStyle(csHeader);
			//ceheader4.setCellStyle(csHeader);
			ceheader1.setEditable(false);
			ceheader2.setEditable(false);
			//ceheader3.setEditable(false);
			//ceheader4.setEditable(false);
			report.addCellElement(ceheader1);
			report.addCellElement(ceheader2);
			//report.addCellElement(ceheader3);
			//report.addCellElement(ceheader4);
			
			//加入定制列
			for (int i = 0; i < colDims.length; i++) {
				CellElement ceheader = new CellElement(col+2+i,row,colDims[i].getColName());
				ceheader.setCellStyle(csHeader);
				ceheader.setEditable(false);
				report.addCellElement(ceheader);
			}
			
			//添加数据
			CellStyle csData = new CellStyle();
			csData.setBorderLeft(1);
			csData.setBorderRight(1);
			csData.setBorderTop(1);
			csData.setBorderBottom(1);
			
			
			for (int i = 0; i < itemvos.length; i++) {
				row++;
				report.insertRow(row);
				CellElement ce1 = new CellElement(col,row,itemvos[i].getBmsItem().getItemCode());
				CellElement ce2 = new CellElement(col+1,row,itemvos[i].getBmsItem().getItemName());
				//CellElement ce3 = new CellElement(col+2,row,"");
				//CellElement ce4 = new CellElement(col+3,row,"");
				ce1.setEditable(false);
				ce2.setEditable(false);
				//ce3.setEditable(false);
				//ce4.setEditable(false);
				
				ce1.setCellStyle(csData);
				ce2.setCellStyle(csData);
				//ce3.setCellStyle(csData);
				//ce4.setCellStyle(csData);
				
				report.addCellElement(ce1);
				report.addCellElement(ce2);
				//report.addCellElement(ce3);
				//report.addCellElement(ce4);
				
				for (int j = 0; j < colDims.length; j++) {
					CellElement ce = new CellElement(col+2+j,row,"");
					ce.setEditable(false);
					ce.setCellStyle(csData);
					report.addCellElement(ce);
				}
				
			}
			
			sheetPanel.revalidate();
			sheetPanel.repaint();
			
			templet.setDataStartCol(new Integer(startCol));
			templet.setDataStartRow(new Integer(startRow));
			
			templet.setDataEndCol(new Integer(col));
			templet.setDataEndRow(new Integer(row));
			
			closeOK();
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
		
		
	}
	
	public void onCancel() {
		closeCancel();
	}
	
	public void setCurrPostion(int col,int row) throws Exception {
		propPanel.setCurrPostion(col,row);
	}

	/**
	 * @param templet the templet to set
	 */
	public void setTemplet(BmsTemplet templet,DesignSpreadSheetPane sheetPanel) {
		this.templet = templet;
		this.sheetPanel = sheetPanel;
		try {
			BmsItem [] items = itemBean.queryAllItems();
			rowDimPanel.setItems(items);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
		
	}

	/**
	 * @return the colDimPanel
	 */
	public ColDimPanel getColDimPanel() {
		return colDimPanel;
	}

	/**
	 * @param colDimPanel the colDimPanel to set
	 */
	public void setColDimPanel(ColDimPanel colDimPanel) {
		this.colDimPanel = colDimPanel;
	}

	/**
	 * @return the rowDimPanel
	 */
	public RowDimPanel getRowDimPanel() {
		return rowDimPanel;
	}

	/**
	 * @param rowDimPanel the rowDimPanel to set
	 */
	public void setRowDimPanel(RowDimPanel rowDimPanel) {
		this.rowDimPanel = rowDimPanel;
	}


}
