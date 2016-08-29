/**
 *
 * Created on 2009-5-13
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import java.io.Serializable;

/**
 * @author sunrui
 *
 */
public class ColParentVO implements Serializable {

	public int index = -1;
	public String colCode = null;
		
	public ColParentVO() {
		
	}
	public ColParentVO(int index,String colCode) {
		this.index=index;
		this.colCode = colCode;
	}
	
	public boolean isNull() {
		return colCode==null;
	}
	
	public String toString() {
		return colCode==null?"":colCode;
	}
	/**
	 * @return the colCode
	 */
	public String getColCode() {
		return colCode;
	}
	/**
	 * @param colCode the colCode to set
	 */
	public void setColCode(String colCode) {
		this.colCode = colCode;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	

}
