package com.sinosoft.bms.service.bd;

import com.sinosoft.utility.SSRS;

public interface BmsAlertObj {

	//²éÑ¯Ô¤¾¯·½°¸
	public abstract SSRS queryBmsAlert(String bgObjid,String dimid) throws Exception;
}
