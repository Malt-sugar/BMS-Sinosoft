/**
 *
 * Created on 2009-4-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.entity.BmsTpBgObj;
import com.sinosoft.bms.entity.BmsTpParamDim;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.BmsDialog;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.swing.common.FuncPanel;
import com.sinosoft.bms.swing.common.MainApplet;
import com.sinosoft.bms.swing.common.TitleButton;
import com.sinosoft.bms.valueobject.TempleteVO;
import com.sinosoft.bms.valueobject.UseFlagVO;
import com.sinosoft.bmscell.report.MultiReport;
import com.sinosoft.bmscell.report.io.ExcelImporter;

/**
 * @author sunrui
 *
 */
public class TempletUI extends FuncPanel {

	public TitleButton m_btnQuery = new TitleButton("查询");
	public TitleButton m_btnAdd = new TitleButton("新增");
	public TitleButton m_btnDelete = new TitleButton("删除");
	public TitleButton m_btnEdit = new TitleButton("编辑");
	public TitleButton m_btnDist = new TitleButton("下发");
	public TitleButton m_btnUnDist = new TitleButton("取消下发");
	public TitleButton m_btnImport = new TitleButton("导入");
	public TitleButton m_btnExport = new TitleButton("导出");
	public TitleButton m_btnDesign = new TitleButton("设计");
	
	
	public TitleButton m_btnSave = new TitleButton("保存");
	public TitleButton m_btnSaveAndReturn = new TitleButton("保存并返回");
	public TitleButton m_btnCancel = new TitleButton("取消");
	
	
	//public TitleButton m_btnItem = new TitleButton("预算项目");
	public TitleButton m_btnProp = new TitleButton("设置数据区");
	public TitleButton m_btnClear = new TitleButton("清除数据区");
	public TitleButton m_btnRefreshData = new TitleButton("刷新数据");
	public TitleButton m_btnClearData = new TitleButton("清除数据");
	public TitleButton m_btnPrint = new TitleButton("打印");
	
	public TpEditPanel editPanel = new TpEditPanel();
	public TpDesignPanel designPanel = new TpDesignPanel();
	
	public static final int NORMAL_STATUS = 0;
	public static final int EDIT_STATUS = 1;
	public static final int DESIGN_STATUS = 2;
	public static final int NEW_STATUS = 3;
	
	public int currStatus = NORMAL_STATUS;
	
	public TpQueryDlg queryDlg = null;
	
	public Templete tpBean = null;
	
	public TitleButton [] m_btnArray = new TitleButton [] {
			m_btnQuery,m_btnAdd,m_btnEdit,m_btnDelete,m_btnSave,m_btnCancel,
			m_btnDist,m_btnUnDist,m_btnImport,m_btnExport,m_btnDesign
	}; 
	
	public TitleButton [] m_btnDesignArray = new TitleButton [] {
			m_btnSave,m_btnSaveAndReturn,m_btnCancel,/*m_btnItem,*/m_btnProp,m_btnClear,m_btnRefreshData,m_btnClearData,m_btnPrint
	};

	public SheetQueryDlg sheetQueryDlg = null;

	/**
	 * @param mainApplet
	 */
	public TempletUI(MainApplet mainApplet) {
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
			setNormalStatus();
			setContentPanel(editPanel);
			tpBean = (Templete)ClientBeanFactory.getBean("Templete");			
			refreshData();
		} catch (Exception ex) {
			ex.printStackTrace();
			ClientUtils.showErrMsg(this, ex.getMessage());
		}
	}

	public void onAdd() throws Exception {
		setEditStatus();
		setCurrStatus(NEW_STATUS);
		BmsTemplet tp = new BmsTemplet();
		tp.setUseFlag(new UseFlagVO(UseFlagVO.USEFALG_INUSE).toCharValue());
		editPanel.getBasicInfoPanel().setBmsTemplet(tp);
		editPanel.setRightVisible(true);
		
	}
	
	public void onClear() throws Exception {
		if(JOptionPane.showConfirmDialog(this, "真的要清除数据区吗？", "确认", 
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
			designPanel.onClear();
		}
	}
	
	public void onDist() throws Exception {
		BmsTemplet tp = editPanel.getSelectTemplet();
		if(tp==null) {
			throw new Exception ("请选择要删除的表样");
		}
		if(BmsUtils.charToBool(tp.getDisFlag())) {
			throw new Exception ("指定的表样已经下发");
		}
		tp.setDisFlag(BmsUtils.boolToChar(true));
		tpBean.update(tp);
		ClientUtils.showMsg(this, "表样下发成功！");
	}
	
	public void onUnDist() throws Exception {
		BmsTemplet tp = editPanel.getSelectTemplet();
		if(tp==null) {
			throw new Exception ("请选择要删除的表样");
		}
		if(!BmsUtils.charToBool(tp.getDisFlag())) {
			throw new Exception ("指定的表样未下发");
		}
		tp.setDisFlag(BmsUtils.boolToChar(false));
		tpBean.update(tp);
		ClientUtils.showMsg(this, "取消表样下发成功！");
	}
	
	public void onRefreshData() throws Exception {
		if(sheetQueryDlg==null) {
			sheetQueryDlg = new SheetQueryDlg(this);
		}
		
		sheetQueryDlg.setTempleteVO(editPanel.getSelectTempletVO());
		sheetQueryDlg.setSheetPanel(designPanel.getSheetPanel());
		
		
		if(sheetQueryDlg.showModal()==BmsDialog.ID_OK) {
			designPanel.executeFormula();
		}
	}
	public void onClearData() throws Exception {
		if(JOptionPane.showConfirmDialog(this, "真的要清除数据吗？", "确认", 
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
			designPanel.clearData();
		}
	}
	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.FuncPanel#onButtonClick(com.sinosoft.bms.swing.common.TitleButton)
	 */
	public void onButtonClick(TitleButton button) {
		try {
			if(button==m_btnDesign) {
				onDesign();
			}
			if(button==m_btnAdd) {
				onAdd();
			}
			if(button==m_btnRefreshData) {
				onRefreshData();
			}
			if(button==m_btnClearData) {
				onClearData();
			}
			if(button==m_btnDist) {
				onDist();
			}
			if(button==m_btnUnDist) {
				onUnDist();
			}
			if(button==m_btnSave) {
				if(getCurrStatus()==EDIT_STATUS||getCurrStatus()==NEW_STATUS) {
					onTpSave();
				}
				if(getCurrStatus()==DESIGN_STATUS) {
					onDesignSave();
				}
			}
			if(button==m_btnCancel) {
				if(getCurrStatus()==EDIT_STATUS||getCurrStatus()==NEW_STATUS) {
					onTpCancel();
				}
				if(getCurrStatus()==DESIGN_STATUS) {
					onDesignCancel();
				}
			}
			if(button==m_btnImport) {
				onImport();
			}
			
			if(button==m_btnEdit) {
				onEdit();
			}
			if(button==m_btnSaveAndReturn) {
				onDesignSaveAndReturn();
			}
			if(button==m_btnQuery) {
				onQuery();
			}
			if(button==m_btnDelete) {
				onDelete();
			}
			if(button==m_btnProp) {
				onProp();
			}
			if(button==m_btnClear) {
				onClear();
			}
			if(button==m_btnPrint) {
				onPrint();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}
	
	public void onPrint() throws Exception {
		designPanel.onPrint();
	}
	
	public void onProp() throws Exception {

		designPanel.onProp();
	}
	
	public void onImport() throws Exception {
		BmsTemplet tp = editPanel.getSelectTemplet();
		if(tp==null) throw new Exception("请选择您要导入的表样");
		
		
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new ExcelFileFilter());
		if(fc.showOpenDialog(this)==JFileChooser.APPROVE_OPTION) {
			ExcelImporter imp = new ExcelImporter(fc.getSelectedFile());
			MultiReport report = imp.generateMultiReport(fc.getSelectedFile());
			int size = report.size();
			designPanel.loadTemplete(tp);
			designPanel.getSheetPanel().setReport(report.getReport(0));
			setContentPanel(designPanel);
			setDesignStatus();
		}
		
		
	}
	
	public void onDelete() throws Exception {
		BmsTemplet tp = editPanel.getSelectTemplet();
		if(tp==null) {
			throw new Exception ("请选择要删除的表样");
		}
		if(JOptionPane.showConfirmDialog(this, "真的要删除该表样吗？", "确认", 
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
			tpBean.delete(tp);
			refreshData();
		}
		
	}
	
	public void onQuery() throws Exception {
		if(getQueryDlg().showModal()==BmsDialog.ID_OK) {
			refreshData();
		}
	}
	
	public void refreshData() throws Exception {
		BmsTemplet [] tps = tpBean.queryAll();
		editPanel.setBmsTemplete(tps);
	}
	
	public void onEdit() throws Exception {
		BmsTemplet tp = editPanel.getSelectTemplet();
		if(tp==null) {
			throw new Exception("请选择要编辑的表样");
		}
		setEditStatus();
	}
	
	public void onTpSave() throws Exception {
		BmsTemplet tp = editPanel.getBasicInfoPanel().getBmsTemplet();
		
		int tpid = 0;
		
		if(getCurrStatus()==NEW_STATUS) {
			tpid=tpBean.insert(tp).intValue();
			tp.setTpId(tpid);
		} else {
			tpBean.update(tp);
			tpid=tp.getTpId();
		}
		
		//保存下发主体
		BmsTpBgObj [] tpBgObjs = editPanel.getBgObjPanel().getTpBgObjs();
		for (int i = 0; i < tpBgObjs.length; i++) {
			tpBgObjs[i].setBmsTemplet(tp);
		}
		tpBean.saveTpBgObj(tpid, tpBgObjs);
		
		//保存参数维度
		BmsTpParamDim [] tpParamDims = editPanel.getParamDimPanel().getTpParamDims();
		for (int i = 0; i < tpParamDims.length; i++) {
			tpParamDims[i].setBmsTemplet(tp);
		}
		tpBean.saveTpParamDim(tpid, tpParamDims);
		
		
		setNormalStatus();
		refreshData();
	}
	
	public void onDesignSave() throws Exception {
		designPanel.saveTemplete();
	}
	
	public void onTpCancel() throws Exception {
		setNormalStatus();
		BmsTemplet tp = editPanel.getSelectTemplet();
		if(tp==null) {
			editPanel.setRightVisible(false);
		} else {
			editPanel.onTreeSelected();
		}
	}
	
	public void onDesignCancel() throws Exception {
		setContentPanel(editPanel);
		setNormalStatus();
		
	}
	
	public void onDesignSaveAndReturn() throws Exception {
		onDesignSave();
		setNormalStatus();
		setContentPanel(editPanel);
	}
	
	public void onDesign() throws Exception {
		
		BmsTemplet tp = editPanel.getSelectTemplet();
		if(tp==null) throw new Exception("请选择您要设计的表样");
		
		TempleteVO tvo = editPanel.getSelectTempletVO();
		
		designPanel.loadTemplete(tp);
		designPanel.setTempleteVO(tvo);
		
		setContentPanel(designPanel);
		setDesignStatus();
		designPanel.getSheetPanel().repaint();
		sheetQueryDlg = null;
		
	}
	
	public void setEditStatus() throws Exception {
		m_btnQuery.setEnabled(false);
		m_btnAdd.setEnabled(false);
		m_btnEdit.setEnabled(false);
		m_btnDelete.setEnabled(false);
		m_btnSave.setEnabled(true);
		m_btnCancel.setEnabled(true);
		m_btnDesign.setEnabled(false);
		m_btnDist.setEnabled(false);
		m_btnImport.setEnabled(false);
		m_btnExport.setEnabled(false);
		setButtons(m_btnArray);
		editPanel.setRightEnabled(true);
		editPanel.setLeftEnabled(false);
		setCurrStatus(EDIT_STATUS);
	}
	
	public void setNormalStatus() throws Exception {
		m_btnQuery.setEnabled(true);
		m_btnAdd.setEnabled(true);
		m_btnEdit.setEnabled(true);
		m_btnDelete.setEnabled(true);
		m_btnSave.setEnabled(false);
		m_btnCancel.setEnabled(false);
		m_btnDesign.setEnabled(true);
		m_btnDist.setEnabled(true);
		m_btnImport.setEnabled(true);
		m_btnExport.setEnabled(true);
		setButtons(m_btnArray);
		editPanel.setRightEnabled(false);
		editPanel.setLeftEnabled(true);
		setCurrStatus(NORMAL_STATUS);
	}
	
	public void setDesignStatus() throws Exception {
		
		m_btnSave.setEnabled(true);
		m_btnCancel.setEnabled(true);
		m_btnSaveAndReturn.setEnabled(true);
		setButtons(m_btnDesignArray);
		
		setCurrStatus(DESIGN_STATUS);
	}
	
	
	public void setContentPanel(JPanel panel) throws Exception {
		this.removeAll();
		this.add(panel,BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

	/**
	 * @return the queryDlg
	 */
	public TpQueryDlg getQueryDlg() {
		if(queryDlg==null) {
			queryDlg = new TpQueryDlg(this);
		}
		return queryDlg;
	}

	/**
	 * @param queryDlg the queryDlg to set
	 */
	public void setQueryDlg(TpQueryDlg queryDlg) {
		
		this.queryDlg = queryDlg;
	}
	
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



}
