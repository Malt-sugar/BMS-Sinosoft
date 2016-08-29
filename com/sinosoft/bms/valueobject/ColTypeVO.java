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
public class ColTypeVO implements Serializable {

	public static final int CT_COUNT = 6;
	
	public static final int CT_GENERAL = 0;
	public static final int CT_PLAN = 1;
	public static final int CT_ACTUAL = 2;
	public static final int CT_DATABASE = 3;
	public static final int CT_CELLCALC = 4;
	public static final int CT_REMARK = 5;
	
	public int colType = 0;
	
	
	public static String [] colTypeName = {"常规值","计划值","实际值",
		"数据库取数","表内取数","注释"}; 
	
	public static ColTypeVO [] vos = null;
	
	/**
	 * 
	 */
	public ColTypeVO(int colType) {
		this.colType=colType;
	}
	
	public static ColTypeVO [] getColTypes() {
		if(vos==null) {
			vos = new ColTypeVO[CT_COUNT];
			for (int i = 0; i < vos.length; i++) {
				vos[i] = new ColTypeVO(i);
			}
		}
		return vos;
	}
	
	public String toString() {
		return colTypeName[colType];
	}

	/**
	 * @return the colType
	 */
	public int getColType() {
		return colType;
	}

	/**
	 * @param colType the colType to set
	 */
	public void setColType(int colType) {
		this.colType = colType;
	}

}
