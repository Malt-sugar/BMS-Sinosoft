package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsUserBgObj;
import com.sinosoft.bms.entity.BmsUserRole;
import com.sinosoft.utility.SSRS;

public interface UserRoleObj {
	
	//��ѯ�û���Ϣ
	public  abstract SSRS queryUserInfo(String userId) throws Exception;
	
	//��ѯ�û������Ľ�ɫ��Ϣ
	public abstract SSRS queryUserRoleAll() throws Exception;
	
	//�����û�������ɫ
	public abstract void insert(BmsUserRole userRole,List roleIdList) throws Exception;

	//����û�������ɫ
	public abstract void update(BmsUserRole userRole,List roleIdList) throws Exception;
	
	//ɾ���û���ɫ��Ϣ
	public abstract void delete(List inputList) throws Exception;
	
	//��ѯ�û�������������Ϣ
	public abstract SSRS queryBmsObjectAll() throws Exception;
	
	//ɾ���û�������������Ϣ
	public abstract void delete(List inputList,String alignType) throws Exception;
	
	//�����û�������Ԥ��������Ϣ
	public abstract void insert(BmsUserBgObj object,String alignType,List roleIdList) throws Exception;

	//�޸��û�������Ԥ��������Ϣ
	public abstract void update(BmsUserBgObj object,String alignType,List roleIdList) throws Exception;
}
