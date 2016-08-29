/**
 *
 * Created on 2009-4-9
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.bd;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsUser;

/**
 * @author sunrui
 *
 */
public interface User {
	BmsUser queryUserByCode(String userCode) throws Exception;
	void insertUser(BmsUser user) throws Exception;
	BmsUser queryUserByID(int id) throws Exception;
	BmsUser [] queryUsersByObjID(int objid) throws Exception;
	BmsUser [] queryUsersByRoleID(int roleid) throws Exception;
}
