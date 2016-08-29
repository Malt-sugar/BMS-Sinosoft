package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.utility.SSRS;

public interface BmsItemObj {

	//������ɫ����Ϣ
	public abstract void insert(BmsItem bmsItem) throws Exception;
	
	//��ѯԤ����Ŀ��Ϣ
	public abstract SSRS queryBmsItemObj() throws Exception;
	
	public abstract List queryParentItemInfo(String itemId) throws Exception;
	
    //ɾ����ɫ����Ϣ
	public abstract void delete(List inputList) throws Exception;
	
	//�޸�Ԥ����Ŀ��Ϣ
	public abstract void update(BmsItem bmsItem) throws Exception;
	
	BmsItem [] queryAllItems() throws Exception;
}
