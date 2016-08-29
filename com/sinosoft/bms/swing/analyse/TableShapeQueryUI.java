package com.sinosoft.bms.swing.analyse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.ByteArrayInputStream;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.service.budgets.AnalyseQuery;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.BmsDialog;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.swing.common.FuncPanel;
import com.sinosoft.bms.swing.common.MainApplet;
import com.sinosoft.bms.swing.common.TitleButton;
import com.sinosoft.bms.swing.templet.DesignSpreadSheetPane;
import com.sinosoft.bms.swing.templet.TpDesignPanel;
import com.sinosoft.bmscell.report.Report;
import com.sinosoft.bmscell.report.SpreadSheet;
import com.sinosoft.bmscell.report.gui.SpreadSheetPane;
import com.sinosoft.bmscell.report.io.TemplateImporter;
import com.sinosoft.utility.SSRS;

public class TableShapeQueryUI extends FuncPanel{

	public TitleButton m_btnQuery = new TitleButton("查询");
	public AnalyseQueryDlg queryDlg = null;
	public DesignSpreadSheetPane editPanel = new DesignSpreadSheetPane();
	public TpDesignPanel designPanel = new TpDesignPanel();
	
	public AnalyseQuery tpBean = null;
	public Templete  tempBean=null;
	public static final int NORMAL_STATUS = 0;
	public int currStatus = NORMAL_STATUS;
	
	public TitleButton [] m_btnArray = new TitleButton [] {
			m_btnQuery
	}; 
	/**
	 * @return the currStatus
	 */
	public int getCurrStatus() {
		return currStatus;
	}

	/**
	 * @param currStatus the currStatus to set
	 */
	public void setCurrStatus(int currStatus) {
		this.currStatus = currStatus;
	}

	/**
	 * @param mainApplet
	 */
	public TableShapeQueryUI(MainApplet mainApplet) {
		super(mainApplet);
		initialize();
	}
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(309, 202));
		try {	
			setButtons(m_btnArray);
			setContentPanel(editPanel);
			tpBean = (AnalyseQuery)ClientBeanFactory.getBean("QueryAnalyse");	
			tempBean = (Templete) ClientBeanFactory.getBean("Templete");
			//refreshData();
		} catch (Exception ex) {
			ex.printStackTrace();
			ClientUtils.showErrMsg(this, ex.getMessage());
		}
	}
	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.FuncPanel#onButtonClick(com.sinosoft.bms.swing.common.TitleButton)
	 */
	public void onButtonClick(TitleButton button) {
		try {
			if(button==m_btnQuery) {
				onQuery();
			}			
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}
	public void setContentPanel(SpreadSheetPane panel) throws Exception {
		this.removeAll();
		this.add(panel,BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	public void onQuery() throws Exception {
		if(getQueryDlg().showModal()==BmsDialog.ID_OK) {
			refreshData();
		}
	}

	/**
	 * @return the queryDlg
	 * @throws Exception 
	 */
	public AnalyseQueryDlg getQueryDlg() throws Exception {
		if(queryDlg==null) {
			queryDlg = new AnalyseQueryDlg(this);
		}		
		return queryDlg;
	}
	public void refreshData() throws Exception {		
		
		
//		BmsTemplet tp = editPanel.getSelectTemplet();
//		if(tp==null) throw new Exception("请选择您要设计的表样");
//		
//		designPanel.loadTemplete(tp);
//		
//		setContentPanel(designPanel);
//		setDesignStatus();		
		
		BmsTemplet[]  bt = tpBean.queryAnalyseWithCond(String.valueOf(getQueryDlg().refBgSch.getRefId()));
		//editPanel.setBmsTemplete(tps);
		loadTemplete(String.valueOf(getQueryDlg().refBgSch.getRefId()));
		
	}

	public void loadTemplete(String tpId) throws Exception {		
		editPanel.setReport(new SpreadSheet());
		try {
			String fileName = "tp"+tpId+".xml";
			byte [] bytes = tempBean.loadFile(fileName);
			ByteArrayInputStream bin = new ByteArrayInputStream(bytes);
			TemplateImporter imp = new TemplateImporter(bin);
			Report report = imp.generateReport();
			editPanel.setReport(report);
		} catch (java.io.FileNotFoundException ex) {
			ex.printStackTrace();
		}
		
	}
}
