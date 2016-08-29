package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsRole;
import com.sinosoft.bms.entity.BmsRoleClass;

public interface RoleClassObj {

	//������ɫ����Ϣ
	public abstract void insert(BmsRoleClass bmsRoleClass) throws Exception;
	
	//��ѯ��ɫ����Ϣ
	public abstract List queryRoleClassObj() throws Exception;
	
    //ɾ����ɫ����Ϣ
	public abstract void delete(List inputList) throws Exception;
	
	//�޸Ľ�ɫ����Ϣ
	public abstract void update(BmsRoleClass bmsRoleClass) throws Exception;
	
	BmsRoleClass queryRoleClassByID(int rcid) throws Exception;
	
}
