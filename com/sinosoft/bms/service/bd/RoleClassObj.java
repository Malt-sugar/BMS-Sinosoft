package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsRole;
import com.sinosoft.bms.entity.BmsRoleClass;

public interface RoleClassObj {

	//新增角色类信息
	public abstract void insert(BmsRoleClass bmsRoleClass) throws Exception;
	
	//查询角色类信息
	public abstract List queryRoleClassObj() throws Exception;
	
    //删除角色类信息
	public abstract void delete(List inputList) throws Exception;
	
	//修改角色类信息
	public abstract void update(BmsRoleClass bmsRoleClass) throws Exception;
	
	BmsRoleClass queryRoleClassByID(int rcid) throws Exception;
	
}
