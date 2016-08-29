package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsRole;

public interface RoleObj {

	//新增角色信息
	public abstract void insert(BmsRole bmsRole,String bgObjID,String rcID) throws Exception;
	
	//查询角色信息
	public abstract List queryRoleObj() throws Exception;
	
    //删除角色信息
	public abstract void delete(List inputList) throws Exception;
	
	//修改角色信息
	public abstract void update(BmsRole bmsRole,String bgObjID,String rcID) throws Exception;
	
	BmsRole queryRoleByID(int roleid) throws Exception;
}
