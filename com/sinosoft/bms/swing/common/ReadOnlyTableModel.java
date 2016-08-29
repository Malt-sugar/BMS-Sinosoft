/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

/**
 * @author sunrui
 *
 */
public class ReadOnlyTableModel extends DefaultTableModel {

	/**
	 * 
	 */
	public ReadOnlyTableModel() {
	}

	/**
	 * @param rowCount
	 * @param columnCount
	 */
	public ReadOnlyTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	/**
	 * @param columnNames
	 * @param rowCount
	 */
	public ReadOnlyTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	/**
	 * @param columnNames
	 * @param rowCount
	 */
	public ReadOnlyTableModel(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	/**
	 * @param data
	 * @param columnNames
	 */
	public ReadOnlyTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	/**
	 * @param data
	 * @param columnNames
	 */
	public ReadOnlyTableModel(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int column) {
		return false;
	}

}
