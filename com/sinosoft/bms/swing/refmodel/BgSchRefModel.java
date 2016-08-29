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
public class BgSchRefModel extends DefaultRefModel {

	/**
	 * 
	 */
	public BgSchRefModel() {
		setFields(new String[]{"BgSchID","BgSchCode","BgSchName"});
		setTitles(new String[]{"ID","方案编码","方案名称"});
		setTableName("Bms_Scheme");
	}

}
