/**
 *
 * Created on 2009-5-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.refmodel;

import com.sinosoft.bms.swing.common.DefaultRefModel;

/**
 * @author sunrui
 *
 */
public class DimMemberRefModel extends DefaultRefModel {

	/**
	 * 
	 */
	public DimMemberRefModel() {
		setFields(new String[]{"DimMemID","DimMemCode","DimMemName"});
		setTitles(new String[]{"ID","±àÂë","Ãû³Æ"});
		setTableName("BMS_DimMember");
	}

}
