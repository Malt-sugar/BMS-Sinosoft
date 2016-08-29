package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsRole;

public interface RoleObj {

	//������ɫ��Ϣ
	public abstract void insert(BmsRole bmsRole,String bgObjID,String rcID) throws Exception;
	
	//��ѯ��ɫ��Ϣ
	public abstract List queryRoleObj() throws Exception;
	
    //ɾ����ɫ��Ϣ
	public abstract void delete(List inputList) throws Exception;
	
	//�޸Ľ�ɫ��Ϣ
	public abstract void update(BmsRole bmsRole,String bgObjID,String rcID) throws Exception;
	
	BmsRole queryRoleByID(int roleid) throws Exception;
}
