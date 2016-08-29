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

	
	public TitleButton m_btnQuery = new TitleButton("��ѯ");
	public TitleButton m_btnInput = new TitleButton("����");
	public TitleButton m_btnDeleteSheet = new TitleButton("ɾ������");
	public TitleButton m_btnRep = new TitleButton("�ϱ�");
	public TitleButton m_btnUnRep = new TitleButton("ȡ���ϱ�");
	public TitleButton m_btnSave = new TitleButton("����");
	public TitleButton m_btnSaveAndReturn = new TitleButton("���沢����");
	public TitleButton m_btnCancel = new TitleButton("����");
	
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
		
		if(index<0) throw new Exception("��ѡ��Ҫȡ�����Ƶ�Ԥ���");
		
		SheetVO vo = sheetvos[index];
		
		if(vo.getBmsSheet()==null) throw new Exception("��δ���Ƹ�Ԥ���");
		
		if(JOptionPane.showConfirmDialog(this, "���Ҫɾ��������", "ȷ��", 
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
		if(index<0) throw new Exception("��ѡ��Ҫȡ�����Ƶ�Ԥ���");
		SheetVO vo = sheetvos[index];	
		
		if(vo.getBmsSheet()==null) throw new Exception ("��δ���Ʋ����ϱ�");
		
		if(ClientUtils.showYesNoDlg(this, "���Ҫ�����ϱ�������")) {
			vo.getBmsSheet().setRepFlag(BmsUtils.boolToChar(true));
			tpBean.updateSheet(vo.getBmsSheet());
			refreshData();
		}
		
	}
	
	public void onUnRep() throws Exception {
		int index = tablePanel.mainTable.getSelectedRow();
		if(index<0) throw new Exception("��ѡ��Ҫȡ�����Ƶ�Ԥ���");
		SheetVO vo = sheetvos[index];
		
		if(vo.getBmsSheet()==null) throw new Exception ("��δ���Ʋ���ȡ���ϱ�");
		
		if(ClientUtils.showYesNoDlg(this, "���Ҫ����ȡ���ϱ�������")) {
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
		
		if(index<0) throw new Exception("��ѡ��Ҫ���б��Ƶ�Ԥ���");
		
		SheetVO vo = sheetvos[index];
		BmsTemplet tp = tpBean.queryTemplete(vo.getTpID());
		
		if(tp==null) throw new Exception("��ѯ����ʧ��");
		
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
//			throw new Exception("��ѡ��Ҫ���Ƶ�����");
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
