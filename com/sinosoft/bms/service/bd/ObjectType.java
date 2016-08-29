package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObjectType;

public interface ObjectType {

	//��������������Ϣ
	public abstract void insert(BmsObjectType bmsObjectType) throws Exception;
	
	//��ѯ����������Ϣ
	public abstract List queryBmsObjType() throws Exception;
	
    //ɾ������������Ϣ
	public abstract void delete(List inputList) throws Exception;
	
	//�޸�����������Ϣ
	public abstract void update(BmsObjectType bmsObjectType) throws Exception;
}
