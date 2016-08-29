package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.utility.SSRS;

public interface BmsItemObj {

	//新增角色类信息
	public abstract void insert(BmsItem bmsItem) throws Exception;
	
	//查询预算项目信息
	public abstract SSRS queryBmsItemObj() throws Exception;
	
	public abstract List queryParentItemInfo(String itemId) throws Exception;
	
    //删除角色类信息
	public abstract void delete(List inputList) throws Exception;
	
	//修改预算项目信息
	public abstract void update(BmsItem bmsItem) throws Exception;
	
	BmsItem [] queryAllItems() throws Exception;
}
