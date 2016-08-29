/**
 *
 * Created on 2009-4-24
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
public class ColEditableTableModel extends DefaultTableModel {

	public boolean editabled = false;
	public boolean [] colEditableds = null; 
	
	/**
	 * 
	 */
	public ColEditableTableModel() {
	}

	/**
	 * @param rowCount
	 * @param columnCount
	 */
	public ColEditableTableModel(int rowCount, int columnCount) {
		super(rowCount, columnCount);
	}

	/**
	 * @param columnNames
	 * @param rowCount
	 */
	public ColEditableTableModel(Object[] columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	/**
	 * @param data
	 * @param columnNames
	 */
	public ColEditableTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	/**
	 * @param columnNames
	 * @param rowCount
	 */
	public ColEditableTableModel(Vector columnNames, int rowCount) {
		super(columnNames, rowCount);
	}

	/**
	 * @param data
	 * @param columnNames
	 */
	public ColEditableTableModel(Vector data, Vector columnNames) {
		super(data, columnNames);
	}

	/**
	 * @return the colEditableds
	 */
	public boolean[] getColEditableds() {
		return colEditableds;
	}

	/**
	 * @param colEditableds the colEditableds to set
	 */
	public void setColEditableds(boolean[] colEditableds) {
		this.colEditableds = colEditableds;
	}

	/**
	 * @return the editabled
	 */
	public boolean isEditabled() {
		return editabled;
	}

	/**
	 * @param editabled the editabled to set
	 */
	public void setEditabled(boolean editabled) {
		this.editabled = editabled;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.DefaultTableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int row, int column) {
		
		if(editabled) {
			if(colEditableds!=null && column<colEditableds.length) {
				return colEditableds[column];
			}
		}
		
		return false;
	}

}
