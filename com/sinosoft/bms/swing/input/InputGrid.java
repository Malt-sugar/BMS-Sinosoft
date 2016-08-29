/**
 *
 * Created on 2009-5-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.input;

import com.sinosoft.bmscell.report.gui.Grid;

/**
 * @author sunrui
 *
 */
public class InputGrid extends Grid {

	public boolean [] colEditable = null; 
	public int editStartRow = 0;
	public int editEndRow = 0;
	
	/**
	 * 
	 */
	public InputGrid() {
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bmscell.report.gui.Grid#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int col, int row) {
		if(colEditable!=null && col<colEditable.length) {
			if(row>editStartRow && row<=editEndRow) {
				return colEditable[col];
			}
		}
		return false;
	}

	/**
	 * @return the colEditable
	 */
	public boolean[] getColEditable() {
		return colEditable;
	}

	/**
	 * @param colEditable the colEditable to set
	 */
	public void setColEditable(boolean[] colEditable) {
		this.colEditable = colEditable;
	}

	/**
	 * @return the editEndRow
	 */
	public int getEditEndRow() {
		return editEndRow;
	}

	/**
	 * @param editEndRow the editEndRow to set
	 */
	public void setEditEndRow(int editEndRow) {
		this.editEndRow = editEndRow;
	}

	/**
	 * @return the editStartRow
	 */
	public int getEditStartRow() {
		return editStartRow;
	}

	/**
	 * @param editStartRow the editStartRow to set
	 */
	public void setEditStartRow(int editStartRow) {
		this.editStartRow = editStartRow;
	}

}
