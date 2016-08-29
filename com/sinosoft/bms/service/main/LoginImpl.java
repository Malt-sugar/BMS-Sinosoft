/**
 *
 * Created on 2009-4-7
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.main;

import java.util.*;

import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;

/**
 * @author sunrui
 *
 */
public class LoginImpl implements Login {

	protected DAO dao;
	
	/**
	 * 
	 */
	public LoginImpl(DAO dao) {
		this.dao=dao;
	}
	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.beans.ILogin#queryUser()
	 */
	public BmsUser [] queryUser() throws Exception {
		
		int id = BmsUtils.getID();
		
		List userList = dao.query("from BmsUser");
		
		return null;
	}

	
	

}
