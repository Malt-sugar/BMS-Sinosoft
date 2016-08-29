package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsUserBgObj;
import com.sinosoft.bms.entity.BmsUserRole;
import com.sinosoft.utility.SSRS;

public interface UserRoleObj {
	
	//查询用户信息
	public  abstract SSRS queryUserInfo(String userId) throws Exception;
	
	//查询用户关联的角色信息
	public abstract SSRS queryUserRoleAll() throws Exception;
	
	//新增用户关联角色
	public abstract void insert(BmsUserRole userRole,List roleIdList) throws Exception;

	//需改用户关联角色
	public abstract void update(BmsUserRole userRole,List roleIdList) throws Exception;
	
	//删除用户角色信息
	public abstract void delete(List inputList) throws Exception;
	
	//查询用户关联的主体信息
	public abstract SSRS queryBmsObjectAll() throws Exception;
	
	//删除用户关联的主体信息
	public abstract void delete(List inputList,String alignType) throws Exception;
	
	//新增用户关联的预算主体信息
	public abstract void insert(BmsUserBgObj object,String alignType,List roleIdList) throws Exception;

	//修改用户关联的预算主体信息
	public abstract void update(BmsUserBgObj object,String alignType,List roleIdList) throws Exception;
}
