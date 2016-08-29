package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.utility.SSRS;

public interface BmsUserObj {

	//�����û���Ϣ
	public abstract void insert(BmsUser bmsUser,String bmsObjId) throws Exception;
	
	//��ѯ�û���Ϣ
	public abstract SSRS queryUserInfo(String userCode,String userName,String bgObjId,
			String useFlag,String email,String phone) throws Exception;
	
    //ɾ���û���Ϣ
	public abstract void delete(List inputList) throws Exception;
	
	//�޸��û���Ϣ
	public abstract void update(BmsUser bmsUser,String bgObjId) throws Exception;
}
