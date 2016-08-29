package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsObjectType;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;

public class BgObjImpl implements BgObj{

	protected DAO dao = null;
	/**
	 * 
	 */
	public BgObjImpl(DAO dao) {
		this.dao=dao;
	}
	/**
	 *@throws Exception 
	 * @ Ԥ�������������� 
	 */
	public void insert(BmsObject bmsObject,String bgObjTypeId) throws Exception{		
		String hsql = " from BmsObjectType bt where bt.id.bgObjTypeId='"+bgObjTypeId+"'";
		List list = dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("δ�ҵ�����������Ϣ!");
		
		int bgObjId = BmsUtils.getID();
		bmsObject.setBgObjId(bgObjId);
		bmsObject.setBmsObjectType((BmsObjectType) list.get(0));
		dao.insert(bmsObject);
	}
   
	/**
	 * @ ��ѯ����������Ϣ
	 */
	public List queryBgObj(){		
		String hsql = "from BmsObject order by bgObjCode";
		List list = dao.query(hsql);		
		return list;		
	}
	
	/**
	 * @ ɾ������������Ϣ
	 */
	public void delete(List inputList) throws Exception{
		if(inputList==null)
			throw new RuntimeException("������Ϣ�쳣!");
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsObject bmsObj = (BmsObject) inputList.get(i);
			String bgObjId = String.valueOf(bmsObj.getBgObjId());
			String strSql = "delete from Bms_Object where bgObjID='"+bgObjId+"'";
			dao.excute(strSql);
		}
	}

	/**
	 *@throws Exception 
	 * @ ���������޸Ĳ��� 
	 */
	public void update(BmsObject bmsObject,String bgObjTypeID) throws Exception{
		String hsql = " from BmsObjectType bt where bt.id.bgObjTypeId='"+bgObjTypeID+"'";
		List list = dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("δ�ҵ�����������Ϣ!");
		
		bmsObject.setBmsObjectType((BmsObjectType) list.get(0));
		dao.update(bmsObject);
	}
	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.BgObj#queryObjByID(int)
	 */
	public BmsObject queryObjByID(int id) throws Exception {
		BmsObject obj = null;
		List list = dao.query("from BmsObject where BgObjID="+id);
		if(list.size()<1) return null;
		obj = (BmsObject)list.get(0);
		return obj;
	}
}
