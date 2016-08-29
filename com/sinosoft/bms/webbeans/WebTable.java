/**
 *
 * Created on 2009-4-13
 * @author sunrui
 *
 */
package com.sinosoft.bms.webbeans;

/**
 * @author sunrui
 *
 */
public class WebTable extends WebBean {

	public String [] columns = null;
	public int [] colWidth = null;
	public Object[][] data = null;
	
	/**
	 * 
	 */
	public WebTable() {
	}
	
	public WebTable(String id,String [] columns) {
		this.id=id;
		this.columns=columns;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.webbeans.WebBean#getHTML()
	 */
	public String getHTML() throws Exception {
		return null;
	}
	
	public int safeGetColWidth(int index) throws Exception {
		if(colWidth==null) return -1;
		if(index>=colWidth.length) return -1;
		return colWidth[index];
	}

	/**
	 * @return the columns
	 */
	public String[] getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	/**
	 * @return the colWidth
	 */
	public int[] getColWidth() {
		return colWidth;
	}

	/**
	 * @param colWidth the colWidth to set
	 */
	public void setColWidth(int[] colWidth) {
		this.colWidth = colWidth;
	}

	/**
	 * @return the data
	 */
	public Object[][] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object[][] data) {
		this.data = data;
	}

}
