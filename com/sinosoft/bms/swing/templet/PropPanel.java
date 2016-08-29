/**
 *
 * Created on 2009-5-6
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sinosoft.bmscell.report.core.ReportUtils;

/**
 * @author sunrui
 *
 */
public class PropPanel extends JPanel {

	public JLabel lblPostion = new JLabel("起始位置");
	public JTextField txtStartCol = new JTextField();
	public JTextField txtStartRow = new JTextField();
	
	public JLabel lblCol = new JLabel("列");
	public JLabel lblRow = new JLabel("行");
	
	public JLabel lblRowColHelp = new JLabel("（请按如下格式填写，例：B行3列）");
	
	
	/**
	 * 
	 */
	public PropPanel() {
		super();
		init();
	}


	public void init() {
		this.setLayout(null);
		
		lblPostion.setBounds(10, 10, 50, 30);
		txtStartCol.setBounds(60, 16, 30,20);
		txtStartRow.setBounds(110, 16, 30,20);
		lblCol.setBounds(95, 10, 20, 30);
		lblRow.setBounds(145, 10, 20, 30);
		lblRowColHelp.setBounds(165,10,200,30);
		
		this.add(lblPostion);
		this.add(txtStartRow);
		this.add(txtStartCol);
		this.add(lblRow);
		this.add(lblCol);
		this.add(lblRowColHelp);
		
		
		
	}
	
	public void setCurrPostion(int col,int row) throws Exception {
		
		String strCol = ReportUtils.convertIntToABC(col+1);
		txtStartCol.setText(strCol);
		txtStartRow.setText(""+(row+1));
		
	}
	
	public int getRow() throws Exception {
		int row = Integer.parseInt(txtStartRow.getText());
		if(row<1) throw new Exception("行数值不能小于1");
		return row-1;
	}
	
	public int getCol() throws Exception {
		int col = ReportUtils.convertABCToInt(txtStartCol.getText());
		if(col<1) throw new Exception("列数值不能小于1");
		return col-1;
	}

}
