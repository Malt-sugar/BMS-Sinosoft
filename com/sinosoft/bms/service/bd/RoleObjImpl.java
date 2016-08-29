package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsObjectType;
import com.sinosoft.bms.entity.BmsRole;
import com.sinosoft.bms.entity.BmsRoleClass;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;

public class RoleObjImpl implements RoleObj{

	protected DAO dao = null;
	/**
	 * 
	 */
	public RoleObjImpl(DAO dao) {
		this.dao=dao;
	}
	/**
	 *@throws Exception 
	 * @ 预算主体新增操作 
	 */
	public void insert(BmsRole bmsRole,String bgObjID,String rcID) throws Exception{		
		String hsql = "";
		List list;
		hsql =" from BmsObject bt where bt.id.bgObjId='"+bgObjID+"'";
		list= dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("未找到预算主体信息!");
		
		bmsRole.setBmsObject((BmsObject) list.get(0));
		
		hsql = " from BmsRoleClass bc where bc.id.rcid='"+rcID+"'";
		list = dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("未找到角色类信息!");
		
		bmsRole.setBmsRoleClass((BmsRoleClass) list.get(0));
		
		int roleId = BmsUtils.getID();
		bmsRole.setRoleId(roleId);
		
		dao.insert(bmsRole);
	}
   
	/**
	 * @ 查询角色信息
	 */
	public List queryRoleObj(){		
		String hsql = "from BmsRole order by RoleCode";
		List list = dao.query(hsql);		
		return list;		
	}
	
	/**
	 * @ 删除角色信息
	 */
	public void delete(List inputList) throws Exception{
		if(inputList==null)
			throw new RuntimeException("数据信息异常!");
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsRole bmsRole = (BmsRole) inputList.get(i);
			String roleId = String.valueOf(bmsRole.getRoleId());
			String strSql = "delete from bms_role where roleid='"+roleId+"'";
			dao.excute(strSql);
		}
	}

	/**
	 *@throws Exception 
	 * @ 主体类型修改操作 
	 */
	public void update(BmsRole bmsRole,String bgObjID,String rcID) throws Exception{
		String hsql = "";
		List list;
		hsql =" from BmsObject bt where bt.id.bgObjId='"+bgObjID+"'";
		list= dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("未找到预算主体信息!");
		
		bmsRole.setBmsObject((BmsObject) list.get(0));
		
		hsql = " from BmsRoleClass bc where bc.id.rcid='"+rcID+"'";
		list = dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("未找到角色类信息!");
		
		bmsRole.setBmsRoleClass((BmsRoleClass) list.get(0));	
		
		dao.update(bmsRole);
	}
	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.RoleObj#queryRoleByID(int)
	 */
	public BmsRole queryRoleByID(int roleid) throws Exception {
		BmsRole role = null;
		List list = dao.query("from BmsRole where RoleID="+roleid);
		if(list.size()<1) return null;
		role = (BmsRole)list.get(0);
		return role;
	}
}
