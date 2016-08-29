package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObjectType;

public interface ObjectType {

	//新增主体类型信息
	public abstract void insert(BmsObjectType bmsObjectType) throws Exception;
	
	//查询主体类型信息
	public abstract List queryBmsObjType() throws Exception;
	
    //删除主体类型信息
	public abstract void delete(List inputList) throws Exception;
	
	//修改主体类型信息
	public abstract void update(BmsObjectType bmsObjectType) throws Exception;
}
