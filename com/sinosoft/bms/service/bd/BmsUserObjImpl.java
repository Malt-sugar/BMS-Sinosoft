package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;
import com.sinosoft.bms.framework.EncryptNew;
import com.sinosoft.utility.SSRS;

public class BmsUserObjImpl implements BmsUserObj{

	protected DAO dao = null;
	/**
	 * 
	 */
	public BmsUserObjImpl(DAO dao) {
		this.dao=dao;
	}
	/**
	 *@throws Exception 
	 * @ 用户新增操作 
	 */
	public void insert(BmsUser bmsUser,String bgObjId) throws Exception{	
		int userId = BmsUtils.getID();
		bmsUser.setUserId(userId);
		
		String hsql =" from BmsObject bo where bo.id.bgObjId='"+bgObjId+"'";
		List list = dao.query(hsql);
		if(list==null)
			throw new RuntimeException("未找到预算主体信息!");
		
		bmsUser.setBmsObject((BmsObject) list.get(0));
		
		String pwd = EncryptNew.EnCode(bmsUser.getPassWord());
		bmsUser.setPassWord(pwd);
		dao.insert(bmsUser);
	}
   
	/**
	 * @ 查询用户信息
	 */
	public SSRS queryUserInfo(String userCode,String userName,String bgObjId,
			String useFlag,String email,String phone)throws Exception{
		SSRS ssrs = new SSRS(11);
		
		String strSql1 = "from BmsUser bmsUser where 1=1";
		if(userCode!=null&&!"".equals(userCode)){
			strSql1 += " and bmsUser.userCode='"+userCode+"'";
		}
		if(userName!=null&&!"".equals(userName)){
			strSql1 += " and bmsUser.userName like '%"+userName+"%'";
		}
		if(bgObjId!=null&&!"".equals(bgObjId)){
			strSql1 += " and bmsUser.bgObjId='"+bgObjId+"'";
		}
		if(useFlag!=null&&!"".equals(useFlag)){
			strSql1 += " and bmsUser.useFlag='"+useFlag+"'";
		}
		if(email!=null&&!"".equals(email)){
			strSql1 += " and bmsUser.email='"+email+"'";
		}
		if(phone!=null&&!"".equals(phone)){
			strSql1 += " and bmsUser.phone='"+userCode+"'";
		}
		
		List list = dao.query(strSql1);
		for(int i=0;list.size()>0&&i<list.size();i++){
		   BmsUser bu = (BmsUser) list.get(i);
		   List rsList = null;
		   if(bu.getBmsObject()!=null) {
			   String strSql2 = " from BmsObject bo where bo.id.bgObjId='"+bu.getBmsObject().getBgObjId()+"'";
			   rsList = dao.query(strSql2);
			   ssrs.SetText(String.valueOf(bu.getUserId()));
		   } else {
			   ssrs.SetText("");
		   }
		   ssrs.SetText(bu.getUserCode());
		   ssrs.SetText(bu.getUserName());
		   ssrs.SetText(bu.getPassWord());
		   //取所属主体相关
		   if(bu.getBmsObject()!=null) {
			   ssrs.SetText(String.valueOf(bu.getBmsObject().getBgObjId()));		  
			   for(int j=0;j<rsList.size();j++){
				   BmsObject bobj = (BmsObject) rsList.get(j);
				   ssrs.SetText(bobj.getBgObjCode());
				   ssrs.SetText(bobj.getBgObjName());
			   }
		   } else {
			   ssrs.SetText("");
			   ssrs.SetText("");
			   ssrs.SetText("");
		   }
		   if(bu.getUseFlag().equals("0")){
			   ssrs.SetText("未启用");
		   }else{
			   ssrs.SetText("启用");
		   }		  
		   ssrs.SetText(bu.getUseFlag().toString());
		   ssrs.SetText(bu.getEmail());
		   ssrs.SetText(bu.getPhone());
		}
		return ssrs;		
	}
	
	/**
	 * @ 删除用户信息
	 */
	public void delete(List inputList) throws Exception{
		if(inputList==null)
			throw new RuntimeException("数据信息异常!");
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsUser bmsUser = (BmsUser) inputList.get(i);
			String userId = String.valueOf(bmsUser.getUserId());
			String strSql = "delete from Bms_User where userId='"+userId+"'";
			dao.excute(strSql);
		}
	}

	/**
	 *@throws Exception 
	 * @ 用户修改操作 
	 */
	public void update(BmsUser bmsUser,String bgObjId) throws Exception{
		String hsql =" from BmsObject bo where bo.id.bgObjId='"+bgObjId+"'";
		List list = dao.query(hsql);
		if(list==null)
			throw new RuntimeException("未找到预算主体信息!");
		
		bmsUser.setBmsObject((BmsObject) list.get(0));
		
		String pwd = EncryptNew.EnCode(bmsUser.getPassWord());
		bmsUser.setPassWord(pwd);

		dao.update(bmsUser);
	}
}
