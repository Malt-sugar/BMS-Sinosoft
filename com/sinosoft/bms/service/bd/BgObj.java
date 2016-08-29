package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObject;

public interface BgObj {

	//新增预算主体信息
	public abstract void insert(BmsObject bmsObject,String bgObjTypeId) throws Exception;
	
	//查询预算主体信息
	public abstract List queryBgObj() throws Exception;
	
    //删除预算主体信息
	public abstract void delete(List inputList) throws Exception;
	
	//修改预算主体信息
	public abstract void update(BmsObject bmsObject,String bgObjTypeID) throws Exception;
	
	BmsObject queryObjByID(int id) throws Exception;
}
