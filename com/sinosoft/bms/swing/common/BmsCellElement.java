/**
 *
 * Created on 2009-5-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import com.sinosoft.bmscell.report.CellElement;

/**
 * @author sunrui
 *
 */
public class BmsCellElement extends CellElement {

	
	/**
	 * 
	 */
	public BmsCellElement() {
	}

	/**
	 * @param i
	 * @param j
	 */
	public BmsCellElement(int i, int j) {
		super(i, j);
	}

	/**
	 * @param i
	 * @param j
	 * @param obj
	 */
	public BmsCellElement(int i, int j, Object obj) {
		super(i, j, obj);
	}

	/**
	 * @param i
	 * @param j
	 * @param k
	 * @param l
	 * @param obj
	 */
	public BmsCellElement(int i, int j, int k, int l, Object obj) {
		super(i, j, k, l, obj);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bmscell.report.CellElement#setValue(java.lang.Object)
	 */
	public void setValue(Object obj) {
		super.setValue(obj);
		
	}
	
}
