package com.sinosoft.bms.service.bd;

import com.sinosoft.utility.SSRS;

public interface BmsSheetItemObj {
	
	//��ѯԤ�����Ŀ��Ϣ
	public abstract SSRS querySheetItem(String bgObjId,String dimId) throws Exception;

}
