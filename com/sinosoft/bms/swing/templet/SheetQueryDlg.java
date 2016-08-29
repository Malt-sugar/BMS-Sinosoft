/**
 *
 * Created on 2009-5-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;


import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.service.budgets.Templete;
import com.sinosoft.bms.swing.common.BmsButton;
import com.sinosoft.bms.swing.common.BmsDialog;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.swing.common.RefPanel;
import com.sinosoft.bms.swing.refmodel.BgObjRefModel;
import com.sinosoft.bms.swing.refmodel.DimMemberRefModel;
import com.sinosoft.bms.valueobject.CellVO;
import com.sinosoft.bms.valueobject.TempleteVO;
import com.sinosoft.bmscell.base.Constants;
import com.sinosoft.bmscell.report.CellElement;
import com.sinosoft.bmscell.report.CellStyle;

/**
 * @author sunrui
 *
 */
public class SheetQueryDlg extends BmsDialog {

	public JPanel contentPanel = new JPanel();
	public JPanel mainPanel = new JPanel();
	public JPanel bottomPanel = new JPanel();
	
	public BmsButton btnOK = new BmsButton("确定");
	public BmsButton btnCancel = new BmsButton("取消");
	
	public JLabel lblBgObj = new JLabel("预算主体");
	public RefPanel refBgObj = new RefPanel();
	public JLabel lblParamDim = new JLabel("参数维度");
	public RefPanel refParamDim = new RefPanel();
	
	public DesignSpreadSheetPane sheetPanel = null;
	public TempleteVO templeteVO = null;
	
	public Templete tpBean = null;
	
	/**
	 * @throws HeadlessException
	 */
	public SheetQueryDlg() throws HeadlessException {
		super();
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public SheetQueryDlg(Dialog owner) throws HeadlessException {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public SheetQueryDlg(Frame owner) throws HeadlessException {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws HeadlessException
	 */
	public SheetQueryDlg(Dialog owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws HeadlessException
	 */
	public SheetQueryDlg(Frame owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public SheetQueryDlg(Dialog owner, String title) throws HeadlessException {
		super(owner, title);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public SheetQueryDlg(Frame owner, String title) throws HeadlessException {
		super(owner, title);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @throws HeadlessException
	 */
	public SheetQueryDlg(Dialog owner, String title, boolean modal)
			throws HeadlessException {
		super(owner, title, modal);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @throws HeadlessException
	 */
	public SheetQueryDlg(Frame owner, String title, boolean modal)
			throws HeadlessException {
		super(owner, title, modal);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @param gc
	 * @throws HeadlessException
	 */
	public SheetQueryDlg(Dialog owner, String title, boolean modal,
			GraphicsConfiguration gc) throws HeadlessException {
		super(owner, title, modal, gc);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @param gc
	 */
	public SheetQueryDlg(Frame owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		init();
	}

	/**
	 * @param owner
	 */
	public SheetQueryDlg(Component owner) {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 */
	public SheetQueryDlg(Component owner, boolean modal) {
		super(owner, modal);
		init();
	}

	public void init() {
		
		try {
			tpBean = (Templete)ClientBeanFactory.getBean("Templete");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setSize(new Dimension(300,150));
		this.setTitle("刷新数据");
		this.setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
		contentPanel.add(mainPanel,BorderLayout.CENTER);
		contentPanel.add(bottomPanel,BorderLayout.SOUTH);
		
		mainPanel.setLayout(null);
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
		
		int x = 5;
		int y = 5;
		int w = 60;
		int h = 25;
		
		lblBgObj.setBounds(x, y, w, h);
		lblParamDim.setBounds(x, y+=h, w, h);
		
		x=65;
		y=10;
		w=150;
		h=20;
		
		
		int g = 5;
		
		refBgObj.setBounds(x, y, w, h);
		refParamDim.setBounds(x, y+=h+g, w, h);
		
		mainPanel.add(lblBgObj);
		mainPanel.add(lblParamDim);
		mainPanel.add(refBgObj);
		mainPanel.add(refParamDim);
		
		
		refBgObj.setRefModel(new BgObjRefModel());
		refParamDim.setRefModel(new DimMemberRefModel());
		
		
	}
	
	
	
	public void onOK() {
		
		try {
			BmsTemplet tp = templeteVO.getBmsTemplet();
			if(tp.getDataStartRow()==null) {
				throw new Exception("请先设置数据区域");
			}
			int startRow = tp.getDataStartRow().intValue();
			int startCol = tp.getDataStartCol().intValue();
			
			int bgObjID = refBgObj.getRefId();
			int dimMemID = refParamDim.getRefId();
			
			if(bgObjID==0) {
				throw new Exception("请选择主体");
			}
			
			
			CellVO [] cells = tpBean.queryCellVO(templeteVO,bgObjID,dimMemID);
			CellStyle cs = new CellStyle();
			cs.setBorderLeft(1);
			cs.setBorderRight(1);
			cs.setBorderTop(1);
			cs.setBorderBottom(1);
			cs.setHorizontalAlignment(Constants.RIGHT);
			for (int i = 0; i < cells.length; i++) {
				
				CellElement ce = new CellElement(cells[i].getCol(),cells[i].getRow(),cells[i].getValue().toString());
				sheetPanel.getReport().addCellElement(ce);
				ce.setCellStyle(cs);
				ce.setEditable(false);
			}
			
			sheetPanel.revalidate();
			sheetPanel.repaint();
			
			
			closeOK();
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
		
		
	}
	
	public void onCancel() {
		closeCancel();
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
