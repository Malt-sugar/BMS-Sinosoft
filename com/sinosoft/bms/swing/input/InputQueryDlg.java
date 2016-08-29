/**
 *
 * Created on 2009-5-14
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.input;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sinosoft.bms.swing.common.BmsButton;
import com.sinosoft.bms.swing.common.BmsDialog;
import com.sinosoft.bms.swing.common.ClientUtils;
import com.sinosoft.bms.swing.common.RefPanel;
import com.sinosoft.bms.swing.refmodel.BmsTempleteModel;

/**
 * @author sunrui
 *
 */
public class InputQueryDlg extends BmsDialog {

	public JPanel contentPanel = new JPanel();
	public JPanel bottomPanel = new JPanel();
	public JPanel mainPanel = new JPanel();
	public BmsButton btnOk = new BmsButton("确定");
	public BmsButton btnCancel = new BmsButton("取消");
	
	
	public JLabel lblTemplete = new JLabel("预算表样");
	public JLabel lblInputStatus = new JLabel("填写状态");
	public JLabel lblRepStatus = new JLabel("上报状态");
	
	
	public RefPanel refTemplete = new RefPanel();
	public JComboBox cbInputStatus = new JComboBox();
	public JComboBox cbRepStatus = new JComboBox();
	
	
	
	/**
	 * @throws HeadlessException
	 */
	public InputQueryDlg() throws HeadlessException {
		super();
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public InputQueryDlg(Dialog owner) throws HeadlessException {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public InputQueryDlg(Frame owner) throws HeadlessException {
		super(owner);
		init();
	}


	/**
	 * @param owner
	 */
	public InputQueryDlg(Component owner) {
		super(owner);
		init();
	}

	/**
	 * @param owner
	 * @param modal
	 */
	public InputQueryDlg(Component owner, boolean modal) {
		super(owner, modal);
		init();
	}
	
	public void init() {
		try {
			
			this.setSize(300,200);
			this.setTitle("查询条件");
			
			setContentPane(contentPanel);
			contentPanel.setLayout(new BorderLayout());
			
			contentPanel.add(bottomPanel,BorderLayout.SOUTH);
			contentPanel.add(mainPanel,BorderLayout.CENTER);
			
			bottomPanel.setPreferredSize(new Dimension(30,30));
			bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,2,2));
			bottomPanel.add(btnOk);
			bottomPanel.add(btnCancel);
			
			mainPanel.setLayout(null);
			
			int x = 10;
			int y = 10;
			int w = 50;
			int h = 25;
			int g = 5;
			
			lblTemplete.setBounds(x, y, w, h);
			lblInputStatus.setBounds(x, y+=h, w, h);
			lblRepStatus.setBounds(x, y+=h, w, h);
			
			x=x+w;
			y=13;
			h=20;
			w=150;
			
			refTemplete.setBounds(x, y, w, h);
			cbInputStatus.setBounds(x, y+=h+g, w/2, h);
			cbRepStatus.setBounds(x, y+=h+g, w/2, h);
			
			//初始化控件数据
			refTemplete.setRefModel(new BmsTempleteModel());
			
			cbInputStatus.addItem("-全部-");
			cbInputStatus.addItem("已填写");
			cbInputStatus.addItem("未填写");
			
			cbRepStatus.addItem("-全部-");
			cbRepStatus.addItem("已上报");
			cbRepStatus.addItem("未上报");

			mainPanel.add(lblTemplete);
			mainPanel.add(lblInputStatus);
			mainPanel.add(lblRepStatus);
			
			mainPanel.add(refTemplete);
			mainPanel.add(cbInputStatus);
			mainPanel.add(cbRepStatus);
			
			btnOk.addActionListener(new ActionListener() {
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
		closeOK();
	}
	
	public void onCancel() {
		closeCancel();
	}

}
