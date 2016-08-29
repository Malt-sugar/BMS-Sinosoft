package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsRoleClass;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;

public class RoleClassObjImpl implements RoleClassObj{

	protected DAO dao = null;
	/**
	 * 
	 */
	public RoleClassObjImpl(DAO dao) {
		this.dao=dao;
	}
	/**
	 *@throws Exception 
	 * @ 角色类新增操作 
	 */
	public void insert(BmsRoleClass bmsRoleClass) throws Exception{	
		int rcid = BmsUtils.getID();
		bmsRoleClass.setRcid(rcid);		
		dao.insert(bmsRoleClass);
	}
   
	/**
	 * @ 查询角色类信息
	 */
	public List queryRoleClassObj(){		
		String hsql = "from BmsRoleClass order by rcId";
		List list = dao.query(hsql);		
		return list;		
	}
	
	/**
	 * @ 删除角色类信息
	 */
	public void delete(List inputList) throws Exception{
		if(inputList==null)
			throw new RuntimeException("数据信息异常!");
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsRoleClass bmsRoleClass = (BmsRoleClass) inputList.get(i);
			String rcId = String.valueOf(bmsRoleClass.getRcid());
			String strSql = "delete from Bms_RoleClass where rcid='"+rcId+"'";
			dao.excute(strSql);
		}
	}

	/**
	 *@throws Exception 
	 * @ 角色类修改操作 
	 */
	public void update(BmsRoleClass bmsRoleClass) throws Exception{		
		dao.update(bmsRoleClass);
	}
	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.RoleClassObj#queryRoleClassByID(int)
	 */
	public BmsRoleClass queryRoleClassByID(int rcid) throws Exception {
		List list = dao.query("from BmsRoleClass where rcid="+rcid);
		if(list.size()<1) return null;
		
		return (BmsRoleClass)list.get(0);
	}
}
