package com.sinosoft.bms.swing.analyse;

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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sinosoft.bms.swing.common.BmsButton;
import com.sinosoft.bms.swing.common.BmsDialog;
import com.sinosoft.bms.swing.common.RefPanel;
import com.sinosoft.bms.swing.refmodel.BgSchRefModel;
import com.sinosoft.bms.swing.refmodel.BmsTempleteModel;

public class AnalyseQueryDlg extends BmsDialog{

	public JPanel contentPanel = new JPanel();
	public JPanel mainPanel = new JPanel();
	public JPanel bottomPanel = new JPanel();
	public BmsButton btnOK = new BmsButton("确定");
	public BmsButton btnCancel = new BmsButton("取消");
	
	public JLabel lblTpCode = new JLabel("预算表样");	
	
	public JLabel lblSheetCode = new JLabel("预算表编码");
	public JLabel lblSheetName = new JLabel("预算表名称");
	public JLabel lblBgCode = new JLabel("预算主体编码");
	public JLabel lblBgName = new JLabel("预算主体名称");	
	
	public JLabel lblPeriodS = new JLabel("开始月份");
	public JLabel lblPeriodE = new JLabel("结束月份");
	
	public JTextField txtTpCode = new JTextField();
	public JTextField txtSheetCode = new JTextField();
	public JTextField txtSheetName = new JTextField();
	public JTextField txtBgCode = new JTextField();
	public JTextField txtBgName = new JTextField();
	public JTextField txtPeriodS = new JTextField();
	public JTextField txtPeriodE = new JTextField();
	public RefPanel refBgSch = new RefPanel();
	
	public void init() {
		setTitle("样表查询条件");
		setSize(new Dimension(300,220));
		setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		
		bottomPanel.setPreferredSize(new Dimension(30,30));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,2,2));
		bottomPanel.add(btnOK);
		bottomPanel.add(btnCancel);
		
		int x = 10;
		int y = 10;
		int w = 80;
		int h = 25;
		int g = 5;
		
		mainPanel.setLayout(null);
		
		lblTpCode.setBounds(x, y,w,h);
		lblSheetCode.setBounds(x, y+=h, w, h);
		lblSheetName.setBounds(x, y+=h, w, h);
		lblBgCode.setBounds(x, y+=h, w, h);
		lblBgName.setBounds(x, y+=h, w, h);	
		lblPeriodS.setBounds(x, y+=h, w, h);
		lblPeriodE.setBounds(x, y+=h, w, h);
		
		x = 90;
		y = 15;
		h = 20;
		w = 150;
		
		refBgSch.setBounds(x, y, w, h);
		txtTpCode.setBounds(x, y+=h,w,h);
		txtSheetCode.setBounds(x, y+=h, w, h);		
		txtBgCode.setBounds(x, y+=h, w, h);		
		txtSheetName.setBounds(x, y+=h, w, h);
		txtBgName.setBounds(x, y+=h, w, h);
		txtPeriodS.setBounds(x, y+=h, w, h);
		txtPeriodE.setBounds(x, y+=h, w, h);
		
		
		mainPanel.add(lblTpCode);
		mainPanel.add(lblSheetCode);
		mainPanel.add(lblSheetName);
		mainPanel.add(lblBgCode);
		mainPanel.add(lblBgName);	
		mainPanel.add(lblPeriodS);
		mainPanel.add(lblPeriodE);	
		
		mainPanel.add(txtTpCode);
		
		mainPanel.add(txtSheetCode);
		mainPanel.add(txtBgCode);
		mainPanel.add(txtSheetName);
		mainPanel.add(txtBgName);	
		mainPanel.add(txtPeriodS);
		mainPanel.add(txtPeriodE);		
		mainPanel.add(refBgSch);
		
		contentPanel.add(mainPanel, BorderLayout.CENTER);
		contentPanel.add(bottomPanel,BorderLayout.SOUTH);		
		
		refBgSch.setRefModel(new BmsTempleteModel());
		
		btnOK.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				closeOK();
			}}
		);
		
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				closeCancel();
			}
			
		});
		
		

	}
	
	/**
	 * @throws HeadlessException
	 */
	public AnalyseQueryDlg() throws HeadlessException {
		super();
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public AnalyseQueryDlg(Dialog owner) throws HeadlessException {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public AnalyseQueryDlg(Frame owner) throws HeadlessException {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws HeadlessException
	 */
	public AnalyseQueryDlg(Dialog owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws HeadlessException
	 */
	public AnalyseQueryDlg(Frame owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public AnalyseQueryDlg(Dialog owner, String title) throws HeadlessException {
		super(owner, title);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public AnalyseQueryDlg(Frame owner, String title) throws HeadlessException {
		super(owner, title);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @throws HeadlessException
	 */
	public AnalyseQueryDlg(Dialog owner, String title, boolean modal)
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
	public AnalyseQueryDlg(Frame owner, String title, boolean modal)
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
	public AnalyseQueryDlg(Dialog owner, String title, boolean modal,
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
	public AnalyseQueryDlg(Frame owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		init();
	}

	/**
	 * @param owner
	 * @throws Exception
	 */
	public AnalyseQueryDlg(Component owner) {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws Exception
	 */
	public AnalyseQueryDlg(Component owner, boolean modal) {
		super(owner, modal);
		init();
	}
}
