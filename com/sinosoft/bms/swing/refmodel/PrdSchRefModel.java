/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.refmodel;

import com.sinosoft.bms.swing.common.DefaultRefModel;

/**
 * @author sunrui
 *
 */
public class PrdSchRefModel extends DefaultRefModel {

	/**
	 * 
	 */
	public PrdSchRefModel() {
		setFields(new String[]{"PrdSchID","PrdSchCode","PrdSchName"});
		setTitles(new String[]{"ID","期间编码","期间名称"});
		setTableName("Bms_PrdSch");
	}

}
