/**
 *
 * Created on 2009-4-9
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.bd;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;

/**
 * @author sunrui
 *
 */
public class UserImpl implements User {

	protected DAO dao = null;
	
	
	public UserImpl(DAO dao) {
		this.dao=dao;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.User#insertUser(com.sinosoft.bms.entity.BmsUser)
	 */
	public void insertUser(BmsUser user) throws Exception {
		int id = BmsUtils.getID();
		user.setUserId(id);
		dao.insert(user);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.User#queryUserByCode(java.lang.String)
	 */
	public BmsUser queryUserByCode(String userCode) throws Exception {
		BmsUser user = null;
		List userList = dao.query("from BmsUser where userCode='"+userCode+"'");
		if(userList.size()<1) return null;
		user = (BmsUser)userList.get(0);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.User#queryUserByID(int)
	 */
	public BmsUser queryUserByID(int id) throws Exception {
		BmsUser user = null;
		List userList = dao.query("from BmsUser where userID="+id);
		if(userList.size()<1) return null;
		user = (BmsUser) userList.get(0);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.User#queryUserByObjID(int)
	 */
	public BmsUser [] queryUsersByObjID(int objid) throws Exception {
		BmsUser [] users = null;
		List list = dao.query("from BmsUser u,BmsUserBgObj o where u.userID=o.userID and o.bgObjID="+objid);
		users = new BmsUser[list.size()];
		list.toArray(users);
		return users;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.User#queryUsersByRoleID(int)
	 */
	public BmsUser[] queryUsersByRoleID(int roleid) throws Exception {
		BmsUser [] users = null;
		
		List list = dao.queryWithJDBC("select userid,usercode,username from bms_user where userid in (select userid from bms_userrole where roleid="+roleid+")");
		
		//= dao.query("from BmsUser u,BmsUserRoleId r where u.userID=r.userID and r.roleID="+roleid);
		users = new BmsUser[list.size()];
		
		for (int i = 0; i < users.length; i++) {
			ListOrderedMap map = (ListOrderedMap)list.get(i);
			users[i] = new BmsUser();
			users[i].setUserId(((Integer)map.get("userid")).intValue());
			users[i].setUserCode(map.get("usercode").toString());
			users[i].setUserName(map.get("username").toString());
		}
		
		return users;
	}



	

}
