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
public class BgObjRefModel extends DefaultRefModel {

	/**
	 * 
	 */
	public BgObjRefModel() {
		setFields(new String[]{"BgObjID","BgObjCode","BgObjName"});
		setTitles(new String[]{"ID","�������","��������"});
		setTableName("BMS_Object");
	}

}
