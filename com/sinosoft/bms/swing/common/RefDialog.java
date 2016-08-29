/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * @author sunrui
 *
 */
public class RefDialog extends JDialog {

	
	public JPanel contentPanel = new JPanel();
	public BmsButton btnOk = new BmsButton("确定");
	public BmsButton btnCancel = new BmsButton("取消");
	public BmsButton btnRefresh = new BmsButton("刷新数据");
	
	public JPanel topPanel = new JPanel();
	public JPanel rightPanel = new JPanel();
	public JPanel bottomPanel = new JPanel();
	public JPanel mainPanel = new JPanel();
	
	public JLabel lblLocate = new JLabel("定位");
	public JTextField txtLocate = new JTextField();
	public JScrollPane mainScrollPane = new JScrollPane();
	public JTable mainTable = new JTable();
	
	public RefModel refModel = null;
	
	
	public static final int DLG_OK = 1;
	public static final int DLG_CANCEL = 0;
	
	public int dialogResult = DLG_CANCEL;
	
	public RefPanel refPanel = null;
	
	
	public RefDialog(RefPanel refPanel) {
		super(JOptionPane.getFrameForComponent(refPanel),true);
		this.refPanel = refPanel;
		init();
	}

	public void init() {
		setTitle("代码选择");
		contentPanel.setLayout(new BorderLayout());
		
		contentPanel.add(rightPanel,BorderLayout.EAST);
		contentPanel.add(topPanel, BorderLayout.NORTH);
		contentPanel.add(bottomPanel,BorderLayout.SOUTH);
		contentPanel.add(mainPanel,BorderLayout.CENTER);
		
		topPanel.setPreferredSize(new Dimension(30,30));
		rightPanel.setPreferredSize(new Dimension(100,30));
		bottomPanel.setPreferredSize(new Dimension(30,30));
		
		
		FlowLayout vFlowLayout = new FlowLayout();
		vFlowLayout.setAlignment(FlowLayout.LEFT);
		vFlowLayout.setHgap(2);
		vFlowLayout.setVgap(2);
		
		lblLocate.setPreferredSize(new Dimension(50,20));
		txtLocate.setPreferredSize(new Dimension(120,20));
		
		topPanel.setLayout(vFlowLayout);
		topPanel.add(lblLocate);
		topPanel.add(txtLocate);
		
		rightPanel.setLayout(null);
		btnRefresh.setBounds(5, 0, 90, 25);
		btnRefresh.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				refreshData();
			}}
		);
		rightPanel.add(btnRefresh);
		
		FlowLayout bottomLayout = new FlowLayout();
		bottomLayout.setAlignment(FlowLayout.CENTER);
		bottomLayout.setHgap(2);
		bottomLayout.setVgap(2);
		bottomPanel.setLayout(bottomLayout);
		bottomPanel.add(btnOk);
		bottomPanel.add(btnCancel);
		
		btnOk.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				setDialogResult(DLG_OK);
				int index = mainTable.getSelectedRow();
				if(index==-1) {
					ClientUtils.showErrMsg(RefDialog.this, "请选择一行数据");
					return;
				}
				setVisible(false);
			}
			
		});
		
		btnCancel.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				setDialogResult(DLG_CANCEL);
				setVisible(false);
			}
			
		});
		
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(mainScrollPane, BorderLayout.CENTER);
		mainScrollPane.setViewportView(mainTable);
		
		
		setContentPane(contentPanel);
		this.setSize(400, 300);
		
		
		
	}
	
	public void refreshData() {
		try {
			String [] header = {"编码","名称"};
			refPanel.data = getRefModel().getRefData();
			Object [][] data = refPanel.data;
			if(data==null) data = new Object[0][];
			Object [][] showData = new Object[data.length][];
			for (int i = 0; i < showData.length; i++) {
				showData[i] = new Object[2];
				showData[i][0] = data[i][1];
				showData[i][1] = data[i][2];
			}
			
			ReadOnlyTableModel tm = new ReadOnlyTableModel(showData,header);
			mainTable.setModel(tm);
			//mainTable.getTableHeader().setPreferredSize(new Dimension(1,23));
		} catch (Exception e) {
			e.printStackTrace();
			ClientUtils.showErrMsg(this, e.getMessage());
		}
	}

	/**
	 * @return the refModel
	 */
	public RefModel getRefModel() {
		return refModel;
	}

	/**
	 * @param refModel the refModel to set
	 */
	public void setRefModel(RefModel refModel) {
		this.refModel = refModel;
	}

	/**
	 * @return the dialogResult
	 */
	public int getDialogResult() {
		return dialogResult;
	}

	/**
	 * @param dialogResult the dialogResult to set
	 */
	public void setDialogResult(int dialogResult) {
		this.dialogResult = dialogResult;
	}

	public Object [] getSelData() {
		int index = mainTable.getSelectedRow();
		if(index==-1) return null;
		return refPanel.data[index];
	}
	

}
