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
public class RoleClassRefModel extends DefaultRefModel {

	/**
	 * 
	 */
	public RoleClassRefModel() {
		setFields(new String[]{"RCID","RCCode","RCName"});
		setTitles(new String[]{"ID","��ɫ�����","��ɫ������"});
		setTableName("Bms_RoleClass");
	}

}
