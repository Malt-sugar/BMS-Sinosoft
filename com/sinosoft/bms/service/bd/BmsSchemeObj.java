package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsScheme;
import com.sinosoft.utility.SSRS;

public interface BmsSchemeObj {
	
	//��ѯԤ�㷽����Ϣ
    public abstract SSRS queryBmsSchemeAll() throws Exception;
    
    //ɾ��Ԥ�㷽����Ϣ
    public abstract void delete(List inputList) throws Exception;
    
    //����Ԥ�㷽��
    public abstract void update(BmsScheme shecheme,String bgObjId) throws Exception;
    
    //����Ԥ�㷽��
    public abstract void insert(BmsScheme shecheme,String bgObjId) throws Exception;
    
    //���ݽ���Ԫ�ز�ѯ����
    public abstract SSRS queryBmsScheme(BmsScheme shecheme,String bgObjId) throws Exception;
    
    //����Ԥ�㷽��---���á�ȡ������
    public abstract void update(String bgSchId,String useType) throws Exception;
    
}
