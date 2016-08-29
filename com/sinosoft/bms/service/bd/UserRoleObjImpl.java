package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsRole;
import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.entity.BmsUserBgObj;
import com.sinosoft.bms.entity.BmsUserBgObjId;
import com.sinosoft.bms.entity.BmsUserRole;
import com.sinosoft.bms.entity.BmsUserRoleId;
import com.sinosoft.bms.framework.DAO;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;

public class UserRoleObjImpl implements UserRoleObj{

	protected DAO dao = null;
	/**
	 * 
	 */
	public UserRoleObjImpl(DAO dao) {
		this.dao=dao;
	}
	
	/**
	 * @��ѯ�û���Ϣ
	 */
	public SSRS queryUserInfo(String userId){	
		ExeSQL exeSql;
		SSRS ssrs = null;
		try{
			String strSql =" select userCode,userName from bms_user where userid='"+userId+"'";
			exeSql = new ExeSQL();
			ssrs =exeSql.execSQL(strSql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return ssrs;
	}
	
	/**
	 * @��ѯ�û������Ľ�ɫ��Ϣ
	 */
	public SSRS queryUserRoleAll(){		
		SSRS ssrs = new SSRS(6);
		String hsql = " from BmsUserRole br where 1=1";
		List list = dao.query(hsql);
		for(int i=0;list.size()>0&&i<list.size();i++){
			BmsUserRole userRole = (BmsUserRole) list.get(i);
			BmsRole role = (BmsRole) dao.get(BmsRole.class.getName(),userRole.getId().getRoleId());
			if(role==null)
				throw new RuntimeException("δ�ҵ���ɫ��Ϣ!");
			BmsUser user = (BmsUser) dao.get(BmsUser.class.getName(),userRole.getId().getUserId());
			if(user==null)
				throw new RuntimeException("δ�ҵ��û���Ϣ!");
			
			System.out.println("=========="+user.getUserCode());
			ssrs.SetText(String.valueOf(user.getUserId()));
			ssrs.SetText(user.getUserCode());
			ssrs.SetText(user.getUserName());
			ssrs.SetText(String.valueOf(role.getRoleId()));
			ssrs.SetText(role.getRoleCode());
			ssrs.SetText(role.getRoleName());
		}
		return ssrs;
	}
	
	/**
	 * @ �����û�������ɫ��Ϣ
	 */
	public void insert(BmsUserRole userRole,List roleIdList) throws Exception{
		if(roleIdList==null)
			throw new RuntimeException("δ�ҵ������Ľ�ɫ��Ϣ");
		
		Integer userId = userRole.getId().getUserId();
		System.out.println("============="+userId);
		for(int i=0;i<roleIdList.size();i++){
			BmsUserRole role = new BmsUserRole();
			BmsUserRoleId roleId = new BmsUserRoleId();
			System.out.println("============="+roleIdList.get(i).toString());
			roleId.setRoleId(new Integer(roleIdList.get(i).toString()));
			roleId.setUserId(userId);
			role.setId(roleId);
			dao.insert(role);
		}		
	}
	
	/**
	 * @ �޸��û�������ɫ��Ϣ
	 */
	public void update(BmsUserRole userRole,List roleIdList) throws Exception{
		if(roleIdList==null)
			throw new RuntimeException("δ�ҵ������Ľ�ɫ��Ϣ");
		
		String userId = userRole.getId().getUserId().toString();
		System.out.println("============="+userId);
		for(int i=0;i<roleIdList.size();i++){
//		   String updateSql = " update bms_userRole set roleid='"+userRole.getId().getRoleId()+"'"
//		                    + " where userid='"+userRole.getId().getUserId()+"'";
			String deleteSql = " delete from bms_userRole where userId='"+userId+"'";
			dao.excute(deleteSql);
			
			dao.flush();
			
			BmsUserRole role = new BmsUserRole();
			BmsUserRoleId roleId = new BmsUserRoleId();
			System.out.println("============="+roleIdList.get(i).toString());
			roleId.setRoleId(new Integer(roleIdList.get(i).toString()));
			roleId.setUserId(new Integer(userId));
			role.setId(roleId);
			dao.insert(role);
		   //dao.excute(updateSql);
		}
	}
	
	/**
	 * @ ɾ���û�������ɫ��Ϣ
	 */
	public void delete(List inputList) throws Exception{
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsUserRole userRole = (BmsUserRole) inputList.get(i);			
		    String delSql =" delete from bms_userRole where roleid='"+userRole.getId().getRoleId()+"'"
		                  +" and userid='"+userRole.getId().getUserId()+"'";
		    dao.excute(delSql);
		}
	}

	/**
	 * @��ѯ�û�������������Ϣ
	 */
	public SSRS queryBmsObjectAll() throws Exception{		
		SSRS ssrs = new SSRS(6);
		String hsql = " from BmsUserBgObj br where 1=1";
		List list = dao.query(hsql);
		for(int i=0;list.size()>0&&i<list.size();i++){
			BmsUserBgObj userObj = (BmsUserBgObj) list.get(i);
			BmsObject obj = (BmsObject) dao.get(BmsObject.class.getName(),userObj.getId().getBgObjId());
			if(obj==null)
				throw new RuntimeException("δ�ҵ�Ԥ��������Ϣ!");
			
			BmsUser user = (BmsUser) dao.get(BmsUser.class.getName(),userObj.getId().getUserId());
			if(user==null)
				throw new RuntimeException("δ�ҵ��û���Ϣ!");
			
			
			ssrs.SetText(String.valueOf(user.getUserId()));
			ssrs.SetText(user.getUserCode());
			ssrs.SetText(user.getUserName());
			ssrs.SetText(String.valueOf(obj.getBgObjId()));
			ssrs.SetText(obj.getBgObjCode());
			ssrs.SetText(obj.getBgObjName());
		}
		return ssrs;
	}
	
	/**
	 *@ ɾ���û�������������Ϣ 
	 */
	public void delete(List inputList,String alignType) throws Exception{
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsUserBgObj bgObj = (BmsUserBgObj) inputList.get(i);			
		    String delSql =" delete from bms_userbgobj where bgObjid='"+bgObj.getId().getBgObjId()+"'"
		                  +" and userid='"+bgObj.getId().getUserId()+"'";
		    dao.excute(delSql);
		}		
	}
	
	/**
	 * @ �����û�������Ԥ��������Ϣ
	 */
	public void insert(BmsUserBgObj object,String alignType,List roleIdList) throws Exception{		
		if("budget".equals(alignType)){
			if(roleIdList==null)
				throw new RuntimeException("δ�ҵ�������Ϣ!");
			
			for(int i=0;i<roleIdList.size();i++){
				BmsUserBgObj obj = new BmsUserBgObj();
				BmsUserBgObjId objId = new BmsUserBgObjId();
				objId.setBgObjId(new Integer(roleIdList.get(i).toString()));
				objId.setUserId(object.getId().getUserId());
				obj.setId(objId);
				dao.create(obj);				
			}			
		}
	}
	
	/**
	 * @ �޸��û�����Ԥ��������Ϣ
	 */
	public void update(BmsUserBgObj object,String alignType,List roleIdList) throws Exception{
		
		if(roleIdList==null)
			throw new RuntimeException("δ�ҵ�������Ϣ!");
		
		for(int i=0;i<roleIdList.size();i++){
			String delSql = " delete from bms_userbgobj where userid='"+object.getId().getUserId()+"'";
			dao.excute(delSql);
			
			dao.flush();
			
			BmsUserBgObj obj = new BmsUserBgObj();
			BmsUserBgObjId objId = new BmsUserBgObjId();
			objId.setBgObjId(new Integer(roleIdList.get(i).toString()));
			objId.setUserId(object.getId().getUserId());
			obj.setId(objId);
			dao.create(obj);
		}		
	}
	
	
}
