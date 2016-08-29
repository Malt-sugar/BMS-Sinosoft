package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsObjectType;
import com.sinosoft.bms.entity.BmsRole;
import com.sinosoft.bms.entity.BmsRoleClass;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;

public class RoleObjImpl implements RoleObj{

	protected DAO dao = null;
	/**
	 * 
	 */
	public RoleObjImpl(DAO dao) {
		this.dao=dao;
	}
	/**
	 *@throws Exception 
	 * @ Ԥ�������������� 
	 */
	public void insert(BmsRole bmsRole,String bgObjID,String rcID) throws Exception{		
		String hsql = "";
		List list;
		hsql =" from BmsObject bt where bt.id.bgObjId='"+bgObjID+"'";
		list= dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("δ�ҵ�Ԥ��������Ϣ!");
		
		bmsRole.setBmsObject((BmsObject) list.get(0));
		
		hsql = " from BmsRoleClass bc where bc.id.rcid='"+rcID+"'";
		list = dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("δ�ҵ���ɫ����Ϣ!");
		
		bmsRole.setBmsRoleClass((BmsRoleClass) list.get(0));
		
		int roleId = BmsUtils.getID();
		bmsRole.setRoleId(roleId);
		
		dao.insert(bmsRole);
	}
   
	/**
	 * @ ��ѯ��ɫ��Ϣ
	 */
	public List queryRoleObj(){		
		String hsql = "from BmsRole order by RoleCode";
		List list = dao.query(hsql);		
		return list;		
	}
	
	/**
	 * @ ɾ����ɫ��Ϣ
	 */
	public void delete(List inputList) throws Exception{
		if(inputList==null)
			throw new RuntimeException("������Ϣ�쳣!");
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsRole bmsRole = (BmsRole) inputList.get(i);
			String roleId = String.valueOf(bmsRole.getRoleId());
			String strSql = "delete from bms_role where roleid='"+roleId+"'";
			dao.excute(strSql);
		}
	}

	/**
	 *@throws Exception 
	 * @ ���������޸Ĳ��� 
	 */
	public void update(BmsRole bmsRole,String bgObjID,String rcID) throws Exception{
		String hsql = "";
		List list;
		hsql =" from BmsObject bt where bt.id.bgObjId='"+bgObjID+"'";
		list= dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("δ�ҵ�Ԥ��������Ϣ!");
		
		bmsRole.setBmsObject((BmsObject) list.get(0));
		
		hsql = " from BmsRoleClass bc where bc.id.rcid='"+rcID+"'";
		list = dao.query(hsql);
		if(list.size()==0)
			throw new RuntimeException("δ�ҵ���ɫ����Ϣ!");
		
		bmsRole.setBmsRoleClass((BmsRoleClass) list.get(0));	
		
		dao.update(bmsRole);
	}
	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.RoleObj#queryRoleByID(int)
	 */
	public BmsRole queryRoleByID(int roleid) throws Exception {
		BmsRole role = null;
		List list = dao.query("from BmsRole where RoleID="+roleid);
		if(list.size()<1) return null;
		role = (BmsRole)list.get(0);
		return role;
	}
}
