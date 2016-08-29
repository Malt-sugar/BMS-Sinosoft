/**
 *
 * Created on 2009-4-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.input;


import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.BmsDialog;
import com.sinosoft.bms.swing.common.ClientEnv;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.swing.common.FuncPanel;
import com.sinosoft.bms.swing.common.MainApplet;
import com.sinosoft.bms.swing.common.TitleButton;
import com.sinosoft.bms.valueobject.SheetQueryVO;
import com.sinosoft.bms.valueobject.SheetVO;

/**
 * @author sunrui
 *
 */
public class InputUI extends FuncPanel {

	
	public TitleButton m_btnQuery = new TitleButton("查询");
	public TitleButton m_btnInput = new TitleButton("编制");
	public TitleButton m_btnDeleteSheet = new TitleButton("删除编制");
	public TitleButton m_btnRep = new TitleButton("上报");
	public TitleButton m_btnUnRep = new TitleButton("取消上报");
	public TitleButton m_btnSave = new TitleButton("保存");
	public TitleButton m_btnSaveAndReturn = new TitleButton("保存并返回");
	public TitleButton m_btnCancel = new TitleButton("返回");
	
	public TitleButton [] m_btnArray = new TitleButton[]{
			m_btnQuery,m_btnRep,m_btnUnRep,m_btnInput,m_btnDeleteSheet
	};
	
	public TitleButton [] m_btnSheetArray = new TitleButton[]{
			m_btnSave,m_btnSaveAndReturn,m_btnCancel
	};
	
	public InputTablePanel tablePanel = new InputTablePanel();
	public InputSheetPanel sheetPanel = new InputSheetPanel();
	
	public Templete tpBean = null;
	//public BmsTemplet [] tps = null;
	
	public SheetVO [] sheetvos = null;
	
	/**
	 * @param mainApplet
	 */
	public InputUI(MainApplet mainApplet) {
		super(mainApplet);
		init();
	}

	public void init() {
		try {
			tpBean = (Templete)ClientBeanFactory.getBean("Templete");
			this.setLayout(new BorderLayout());
			setContentPanel(tablePanel);
			
			setButtons(m_btnArray);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}
	
	public void onDeleteSheet() throws Exception {
		int index = tablePanel.mainTable.getSelectedRow();
		
		if(index<0) throw new Exception("请选择要取消编制的预算表");
		
		SheetVO vo = sheetvos[index];
		
		if(vo.getBmsSheet()==null) throw new Exception("尚未编制该预算表");
		
		if(JOptionPane.showConfirmDialog(this, "真的要删除编制吗？", "确认", 
				JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
			tpBean.deleteSheet(vo.getBmsSheet().getSheetId());
			refreshData();
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
			if(button==m_btnInput) {
				onInput();
			}
			if(button==m_btnDeleteSheet) {
				onDeleteSheet();
			}
			if(button==m_btnRep) {
				onRep();
			}
			if(button==m_btnUnRep) {
				onUnRep();
			}
			if(button==m_btnSave) {
				onSave();
			}
			if(button==m_btnSaveAndReturn) {
				onSaveAndReturn();
			}
			if(button==m_btnCancel) {
				onCancel();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}
	
	public void onRep() throws Exception {
		int index = tablePanel.mainTable.getSelectedRow();
		if(index<0) throw new Exception("请选择要取消编制的预算表");
		SheetVO vo = sheetvos[index];	
		
		if(vo.getBmsSheet()==null) throw new Exception ("尚未编制不能上报");
		
		if(ClientUtils.showYesNoDlg(this, "真的要进行上报操作吗？")) {
			vo.getBmsSheet().setRepFlag(BmsUtils.boolToChar(true));
			tpBean.updateSheet(vo.getBmsSheet());
			refreshData();
		}
		
	}
	
	public void onUnRep() throws Exception {
		int index = tablePanel.mainTable.getSelectedRow();
		if(index<0) throw new Exception("请选择要取消编制的预算表");
		SheetVO vo = sheetvos[index];
		
		if(vo.getBmsSheet()==null) throw new Exception ("尚未编制不能取消上报");
		
		if(ClientUtils.showYesNoDlg(this, "真的要进行取消上报操作吗？")) {
			vo.getBmsSheet().setRepFlag(BmsUtils.boolToChar(false));
			tpBean.updateSheet(vo.getBmsSheet());
			refreshData();
		}
		
	}
	
	public void refreshData() throws Exception {
		SheetQueryVO qryvo = new SheetQueryVO();
		qryvo.setUserID(ClientEnv.getInstance().getUserID());
		
		sheetvos = tpBean.querySheet(qryvo);
		
		Object [][] data = new Object[sheetvos.length][];
		
		for (int i = 0; i < data.length; i++) {
			data[i] = new Object[InputTablePanel.header.length];
			data[i][0]=sheetvos[i].getSheetCode();
			data[i][1]=sheetvos[i].getSheetName();
			data[i][2]=sheetvos[i].getBgObjName();
			data[i][3]=sheetvos[i].getDimMemName();
			data[i][4]=sheetvos[i].getInputedStatus();
			data[i][5]=sheetvos[i].getRepStatus();
			data[i][6]=sheetvos[i].getEnabledStatus();
			
		}
		tablePanel.setData(data);
	}
	
	public void onQuery() throws Exception {
		
		InputQueryDlg dlg = new InputQueryDlg(this);
		if(dlg.showModal()==BmsDialog.ID_OK) {
			

			refreshData();
			
		}
	}
	
	public void onInput() throws Exception {
		
		int index = tablePanel.mainTable.getSelectedRow();
		
		if(index<0) throw new Exception("请选择要进行编制的预算表");
		
		SheetVO vo = sheetvos[index];
		BmsTemplet tp = tpBean.queryTemplete(vo.getTpID());
		
		if(tp==null) throw new Exception("查询表样失败");
		
		sheetPanel.loadTemplete(tp,vo);
		setContentPanel(sheetPanel);
		setButtons(m_btnSheetArray);
		sheetPanel.repaint();
		
//		if(tps!=null && index!=-1) {
//			
//			sheetPanel.loadTemplete(tps[index]);
//			
//			setContentPanel(sheetPanel);
//			setButtons(m_btnSheetArray);
//		} else {
//			throw new Exception("请选择要编制的样表");
//		}
		
	}
	
	public void onSave() throws Exception {
		sheetPanel.saveData();
	}
	
	public void onSaveAndReturn() throws Exception {
		onSave();
		onCancel();
		refreshData();
	}
	
	public void onCancel() throws Exception {
		setContentPanel(tablePanel);
		setButtons(m_btnArray);
	}
	
	public void setContentPanel(JPanel panel) throws Exception {
		this.removeAll();
		this.add(panel,BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

}
