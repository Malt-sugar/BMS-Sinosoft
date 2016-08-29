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
		setTitles(new String[]{"ID","角色类编码","角色类名称"});
		setTableName("Bms_RoleClass");
	}

}
