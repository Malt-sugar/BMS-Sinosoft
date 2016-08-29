package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsAdjustBill;
import com.sinosoft.utility.SSRS;

public interface BmsAdjustObj {

	//���ݽ���������ѯԤ�������Ϣ
	public abstract SSRS queryBmsAdjustBill(BmsAdjustBill adjust,String bgObjId) throws Exception;
	
	//ɾ��Ԥ�������Ϣ
	public abstract void delete(List inputList) throws Exception;
	
	//����Ԥ�������Ϣ
	public abstract void update(BmsAdjustBill adjust,String bgObjId) throws Exception;
	
	//����Ԥ�������Ϣ
	public abstract void insert(BmsAdjustBill adjust,String bgObjId) throws Exception;
	
	//���ͨ����ȡ����ˡ���˲�ͨ��
	public abstract void update(String adjBillId,String adjustType,String userid) throws Exception;
}
