/**
 *
 * Created on 2009-4-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.gather;


import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.ClientEnv;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.swing.common.FuncPanel;
import com.sinosoft.bms.swing.common.MainApplet;
import com.sinosoft.bms.swing.common.TitleButton;
import com.sinosoft.bms.valueobject.SheetVO;

/**
 * @author sunrui
 *
 */
public class GatherUI extends FuncPanel {

	
	public TitleButton m_btnQuery = new TitleButton("��ѯ");
	public TitleButton m_btnNew = new TitleButton("�½�");
	public TitleButton m_btnDelete = new TitleButton("ɾ��");
	public TitleButton m_btnEdit = new TitleButton("�༭");
	public TitleButton m_btnRep = new TitleButton("�ϱ�");
	public TitleButton m_btnUnRep = new TitleButton("ȡ���ϱ�");
	public TitleButton m_btnView = new TitleButton("�鿴");
	public TitleButton m_btnDetail = new TitleButton("��ϸ");
	public TitleButton m_btnSave = new TitleButton("����");
	public TitleButton m_btnCancel = new TitleButton("����");
	
	public TitleButton m_btnViewBack = new TitleButton("����");
	
	public TitleButton [] m_btnArray = new TitleButton[]{
			m_btnQuery,m_btnNew,m_btnDelete,m_btnEdit,m_btnRep,m_btnUnRep,m_btnView/*m_btnDetail*/
	};
	
	public TitleButton [] m_btnEditArray = new TitleButton[]{
			m_btnSave,m_btnCancel
	};
	
	public TitleButton [] m_btnViewArray = new TitleButton[]{
			m_btnViewBack
	};
	
	public GatherTablePanel tablePanel = new GatherTablePanel();
	public GatherEditPanel editPanel = new GatherEditPanel();
	
	public Templete tpBean = null;
	
	public BmsSheet [] sheets = null;
	
	public GatherSheetPanel sheetPanel = new GatherSheetPanel();
	
	/**
	 * @param mainApplet
	 */
	public GatherUI(MainApplet mainApplet) {
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

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.FuncPanel#onButtonClick(com.sinosoft.bms.swing.common.TitleButton)
	 */
	public void onButtonClick(TitleButton button) {
		try {
			if(button==m_btnQuery) {
				onQuery();
			}
			if(button==m_btnSave) {
				onSave();
			}
			if(button==m_btnCancel) {
				onCancel();
			}
			if(button==m_btnNew) {
				onNew();
			}
			if(button==m_btnDelete) {
				onDelete();
			}
			if(button==m_btnRep) {
				onRep();
			}
			if(button==m_btnUnRep) {
				onUnRep();
			}
			if(button==m_btnView) {
				onView();
			}
			if(button==m_btnViewBack) {
				onViewBack();
			}
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}
	
	public void onViewBack() throws Exception {
		setContentPanel(tablePanel);
		setButtons(m_btnArray);
	}
	
	public void onView() throws Exception {
		BmsSheet sheet = getSelectedSheet();
		
		SheetVO svo = new SheetVO();
		svo.setBmsSheet(sheet);
		svo.setBmsSheetItems(tpBean.queryBmsSheetItem(sheet.getSheetId()));
		
		BmsTemplet tp = tpBean.queryTemplete(sheet.getBmsTemplet().getTpId());
		
		sheetPanel.loadTemplete(tp, svo);
		setContentPanel(sheetPanel);
		setButtons(m_btnViewArray);
		sheetPanel.repaint();
		
	}
	
	public void onRep() throws Exception {
		BmsSheet sheet = getSelectedSheet();
		sheet.setRepFlag(BmsUtils.boolToChar(true));
		tpBean.updateSheet(sheet);
		onQuery();
	}
	
	public void onUnRep() throws Exception {
		BmsSheet sheet = getSelectedSheet();
		sheet.setRepFlag(BmsUtils.boolToChar(false));
		tpBean.updateSheet(sheet);
		onQuery();
	}
	
	public BmsSheet getSelectedSheet() throws Exception {
		int index = tablePanel.mainTable.getSelectedRow();
		if(index==-1) throw new Exception ("��ѡ��Ԥ���");
		return sheets[index];
	}
	
	public void onDelete() throws Exception {
		BmsSheet sheet = getSelectedSheet();
		if(ClientUtils.showYesNoDlg(this, "���Ҫɾ���ñ���")) {
			tpBean.deleteSheet(sheet.getSheetId());
			onQuery();
		}
	}
	
	public void onNew() throws Exception {
		editPanel = new GatherEditPanel();
		setContentPanel(editPanel);
		setButtons(m_btnEditArray);
	}
	
	public void onQuery() throws Exception {
//		tps = tpBean.queryAll();
//		Object [][] data = new Object[tps.length][];
//		for (int i = 0; i < data.length; i++) {
//			data[i] = new Object[5];
//			data[i][0] = tps[i].getTpCode();
//			data[i][1] = tps[i].getTpName();
//			data[i][2] = "���";
//			data[i][3] = "δ�ϱ�";
//			data[i][4] = "δ����";
//		}
		
		sheets = tpBean.queryBmsSheet("adduser="+ClientEnv.getInstance().getUserID()+" and IsGatherSheet='1'");
		
		
		
		Object [][] data = new Object[sheets.length][];
		for (int i = 0; i < sheets.length; i++) {
			data[i] = new Object[GatherTablePanel.header.length];
			
			SheetVO svo = new SheetVO();
			svo.setBmsSheet(sheets[i]);
			
			data[i][0] = sheets[i].getSheetCode();
			data[i][1] = sheets[i].getSheetTitle();
			data[i][2] = svo.getRepStatus();
			data[i][3] = svo.getEnabledStatus();
			
		}
//		Vector vData = new Vector();
//		Object [] line = new Object[4];
//		line[0]="01";
//		line[1]="���ջ��ܱ�";
//		line[2]="���ϱ�";
//		line[3]="δ����";
//		vData.add(line);
//		
//		line = new Object[4];
//		line[0]="02";
//		line[1]="���ջ��ܱ�";
//		line[2]="δ�ϱ�";
//		line[3]="δ����";
//		vData.add(line);
//		
//		data = new Object[vData.size()][];
//		vData.copyInto(data);
		
		
		
		tablePanel.setData(data);
	}
	
	
	public void onSave() throws Exception {
		editPanel.saveData();
		onCancel();
		onQuery();
	}
	
	public void onSaveAndReturn() throws Exception {
		
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
