package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObject;

public interface BgObj {

	//����Ԥ��������Ϣ
	public abstract void insert(BmsObject bmsObject,String bgObjTypeId) throws Exception;
	
	//��ѯԤ��������Ϣ
	public abstract List queryBgObj() throws Exception;
	
    //ɾ��Ԥ��������Ϣ
	public abstract void delete(List inputList) throws Exception;
	
	//�޸�Ԥ��������Ϣ
	public abstract void update(BmsObject bmsObject,String bgObjTypeID) throws Exception;
	
	BmsObject queryObjByID(int id) throws Exception;
}
