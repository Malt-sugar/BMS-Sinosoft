package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.utility.SSRS;

public interface BmsUserObj {

	//新增用户信息
	public abstract void insert(BmsUser bmsUser,String bmsObjId) throws Exception;
	
	//查询用户信息
	public abstract SSRS queryUserInfo(String userCode,String userName,String bgObjId,
			String useFlag,String email,String phone) throws Exception;
	
    //删除用户信息
	public abstract void delete(List inputList) throws Exception;
	
	//修改用户信息
	public abstract void update(BmsUser bmsUser,String bgObjId) throws Exception;
}
