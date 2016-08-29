/*
 * Created on 2007-3-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sinosoft.bms.swing.templet;

/**
 * @author Ricky
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface RowColChangeListener {
	void onRowInserted(int row);
	void onColInserted(int col);
	void onRowDeleted(int row);
	void onColDeleted(int col);
}
