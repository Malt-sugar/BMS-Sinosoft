/**
 *
 * Created on 2009-5-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import java.io.Serializable;

/**
 * @author sunrui
 *
 */
public class CellVO implements Serializable {

	public int row = 0;
	public int col = 0;
	public int itemID = 0;
	public int colDimID = 0;
	public BmsDouble value = new BmsDouble();
	
	
	/**
	 * 
	 */
	public CellVO() {
	}


	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}


	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}


	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}


	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}


	/**
	 * @return the value
	 */
	public BmsDouble getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(BmsDouble value) {
		this.value = value;
	}


	/**
	 * @return the colDimID
	 */
	public int getColDimID() {
		return colDimID;
	}


	/**
	 * @param colDimID the colDimID to set
	 */
	public void setColDimID(int colDimID) {
		this.colDimID = colDimID;
	}


	/**
	 * @return the itemID
	 */
	public int getItemID() {
		return itemID;
	}


	/**
	 * @param itemID the itemID to set
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

}
