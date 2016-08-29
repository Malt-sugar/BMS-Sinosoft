package com.sinosoft.bms.service.bd;

import java.util.List;

import org.apache.log4j.Logger;

import com.sinosoft.bms.entity.BmsObjectType;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;

public class BmsObjectTypeImpl implements ObjectType{

	protected static Logger logger = Logger.getLogger(PrdSchImpl.class);
	protected DAO dao = null;
	/**
	 * 
	 */
	public BmsObjectTypeImpl(DAO dao) {
		this.dao=dao;
	}
	/**
	 *@throws Exception 
	 * @ ���������������� 
	 */
	public void insert(BmsObjectType bmsObjectType) throws Exception{
		bmsObjectType.setBgObjTypeId(BmsUtils.getID());
		dao.insert(bmsObjectType);
	}
   
	/**
	 * @ ��ѯ����������Ϣ
	 */
	public List queryBmsObjType(){		
		String hsql = "from BmsObjectType order by bjObjTypeCode";
		List list = dao.query(hsql);
		return list;		
	}
	
	/**
	 * @ ɾ������������Ϣ
	 */
	public void delete(List inputList){
		if(inputList==null)
			throw new RuntimeException("������Ϣ�쳣!");
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsObjectType bmsObj = (BmsObjectType) inputList.get(i);
			String bgObjTypeId = String.valueOf(bmsObj.getBgObjTypeId());
			String strSql = "delete from bms_objecttype where bgObjTypeID='"+bgObjTypeId+"'";
			dao.excute(strSql);
		}
	}

	/**
	 *@throws Exception 
	 * @ ���������޸Ĳ��� 
	 */
	public void update(BmsObjectType bmsObjectType){
		dao.update(bmsObjectType);
	}
}
