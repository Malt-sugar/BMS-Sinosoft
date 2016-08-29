package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsAdjustBill;
import com.sinosoft.utility.SSRS;

public interface BmsAdjustObj {

	//根据界面条件查询预算调整信息
	public abstract SSRS queryBmsAdjustBill(BmsAdjustBill adjust,String bgObjId) throws Exception;
	
	//删除预算调整信息
	public abstract void delete(List inputList) throws Exception;
	
	//更新预算调整信息
	public abstract void update(BmsAdjustBill adjust,String bgObjId) throws Exception;
	
	//新增预算调整信息
	public abstract void insert(BmsAdjustBill adjust,String bgObjId) throws Exception;
	
	//审核通过、取消审核、审核不通过
	public abstract void update(String adjBillId,String adjustType,String userid) throws Exception;
}
