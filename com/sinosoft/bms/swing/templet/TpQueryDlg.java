/**
 *
 * Created on 2009-4-22
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

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sinosoft.bms.swing.common.BmsButton;
import com.sinosoft.bms.swing.common.BmsDialog;
import com.sinosoft.bms.swing.common.RefPanel;
import com.sinosoft.bms.swing.refmodel.BgSchRefModel;

/**
 * @author sunrui
 *
 */
public class TpQueryDlg extends BmsDialog {

	
	public JPanel contentPanel = new JPanel();
	public JPanel mainPanel = new JPanel();
	public JPanel bottomPanel = new JPanel();
	public BmsButton btnOK = new BmsButton("确定");
	public BmsButton btnCancel = new BmsButton("取消");
	
	public JLabel lblTpCode = new JLabel("表样编码");
	public JLabel lblTpName = new JLabel("表样名称");
	public JLabel lblBgSch = new JLabel("所属方案");
	public JLabel lblUse = new JLabel("启用标志");
	public JLabel lblDist = new JLabel("下发标志");
	
	public JTextField txtTpCode = new JTextField();
	public JTextField txtTpName = new JTextField();
	public RefPanel refBgSch = new RefPanel();
	public JComboBox cbUse = new JComboBox();
	public JComboBox cbDist = new JComboBox();
	
	
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
		int w = 60;
		int h = 25;
		int g = 5;
		
		mainPanel.setLayout(null);
		
		lblTpCode.setBounds(x, y, w, h);
		lblTpName.setBounds(x, y+=h, w, h);
		lblBgSch.setBounds(x, y+=h, w, h);
		lblUse.setBounds(x, y+=h, w, h);
		lblDist.setBounds(x, y+=h, w, h);
		
		x = 70;
		y = 15;
		h = 20;
		w = 150;
		
		txtTpCode.setBounds(x, y, w, h);
		txtTpName.setBounds(x, y+=(h+g), w, h);
		refBgSch.setBounds(x, y+=(h+g), w, h);
		cbUse.setBounds(x, y+=(h+g), w/2, h);
		cbDist.setBounds(x, y+=(h+g), w/2, h);
		
		mainPanel.add(lblTpCode);
		mainPanel.add(lblTpName);
		mainPanel.add(lblBgSch);
		mainPanel.add(lblUse);
		mainPanel.add(lblDist);
		
		mainPanel.add(txtTpCode);
		mainPanel.add(txtTpName);
		mainPanel.add(refBgSch);
		mainPanel.add(cbUse);
		mainPanel.add(cbDist);
		
		contentPanel.add(mainPanel, BorderLayout.CENTER);
		contentPanel.add(bottomPanel,BorderLayout.SOUTH);
		
		
		refBgSch.setRefModel(new BgSchRefModel());
		cbUse.addItem("-全部-");
		cbUse.addItem("启用");
		cbUse.addItem("未启用");
		
		cbDist.addItem("-全部-");
		cbDist.addItem("已下发");
		cbDist.addItem("未下发");
		
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
	public TpQueryDlg() throws HeadlessException {
		super();
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public TpQueryDlg(Dialog owner) throws HeadlessException {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public TpQueryDlg(Frame owner) throws HeadlessException {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws HeadlessException
	 */
	public TpQueryDlg(Dialog owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws HeadlessException
	 */
	public TpQueryDlg(Frame owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public TpQueryDlg(Dialog owner, String title) throws HeadlessException {
		super(owner, title);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public TpQueryDlg(Frame owner, String title) throws HeadlessException {
		super(owner, title);
		init();
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @throws HeadlessException
	 */
	public TpQueryDlg(Dialog owner, String title, boolean modal)
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
	public TpQueryDlg(Frame owner, String title, boolean modal)
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
	public TpQueryDlg(Dialog owner, String title, boolean modal,
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
	public TpQueryDlg(Frame owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		init();
	}

	/**
	 * @param owner
	 * @throws Exception
	 */
	public TpQueryDlg(Component owner) {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws Exception
	 */
	public TpQueryDlg(Component owner, boolean modal) {
		super(owner, modal);
		init();
	}
	
	


}
