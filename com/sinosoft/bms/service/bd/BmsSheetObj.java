package com.sinosoft.bms.service.bd;

import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.utility.SSRS;

public interface BmsSheetObj {
	
	//���ݽ���������ѯ��Ԥ�������Ϣ
	public abstract SSRS queryBmsSheetInfo(BmsSheet sheet,String bgObjId,int userid) throws Exception;
	
	//ִ�����ͨ����ȡ����ˡ���˲�ͨ������
	public abstract void update(String sheetid,String operate) throws Exception;

}
