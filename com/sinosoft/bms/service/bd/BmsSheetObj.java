package com.sinosoft.bms.service.bd;

import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.utility.SSRS;

public interface BmsSheetObj {
	
	//根据界面条件查询出预算审核信息
	public abstract SSRS queryBmsSheetInfo(BmsSheet sheet,String bgObjId,int userid) throws Exception;
	
	//执行审核通过、取消审核、审核不通过操作
	public abstract void update(String sheetid,String operate) throws Exception;

}
