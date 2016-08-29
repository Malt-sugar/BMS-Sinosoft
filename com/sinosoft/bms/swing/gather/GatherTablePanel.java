/**
 *
 * Created on 2009-4-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.gather;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.sinosoft.bms.swing.common.ReadOnlyTableModel;


/**
 * @author sunrui
 *
 */
public class GatherTablePanel extends JPanel {

	public JScrollPane mainSp = new JScrollPane();
	public JTable mainTable = new JTable();
	public static String [] header = {"汇总表编码","汇总表名称","上报状态","批复状态"};
	public Object [][] data = null;
	/**
	 * 
	 */
	public GatherTablePanel() {
		init();
	}

	public void init() {
		this.setLayout(new BorderLayout());
		this.add(mainSp);
		mainSp.setViewportView(mainTable);
		
		ReadOnlyTableModel tm = new ReadOnlyTableModel(new Object[0][],header);
		mainTable.setModel(tm);
		mainTable.getTableHeader().setPreferredSize(new Dimension(1,23));
	}
	
	public void setData(Object [][] data) throws Exception {
		this.data=data;
		ReadOnlyTableModel tm = new ReadOnlyTableModel(data,header);
		mainTable.setModel(tm);
		//mainTable.getTableHeader().setPreferredSize(new Dimension(1,23));
	}


}
