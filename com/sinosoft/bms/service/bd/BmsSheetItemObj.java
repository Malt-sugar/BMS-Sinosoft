package com.sinosoft.bms.service.bd;

import com.sinosoft.utility.SSRS;

public interface BmsSheetItemObj {
	
	//查询预算表项目信息
	public abstract SSRS querySheetItem(String bgObjId,String dimId) throws Exception;

}
