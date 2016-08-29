package com.sinosoft.bms.struts.actions;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsRole;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.RoleObj;
import com.sinosoft.bms.struts.formbeans.AddRoleObjForm;

public class AddRoleObjAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AddRoleObjForm aotForm = (AddRoleObjForm) actionform;
		RoleObj roleObjBean = (RoleObj) BeanFactory.getBean("RoleObj");
		String bgObjID = aotForm.getBgObjID();
		String rcID = aotForm.getRcID();
		
		//删除角色信息
		if(aotForm.getOperate().equals("delete")){
		   request.setAttribute("action", "DELETE");
		   List inputList = aotForm.getDelBmsRoleInfo();
		   roleObjBean.delete(inputList);
		}
		
		//修改操作
		if(aotForm.getOperate().equals("update")){
		   request.setAttribute("action", "EditSave");
		   BmsRole roleObj = new BmsRole();	
		   roleObj.setRoleId(Integer.parseInt(aotForm.getRoleID()));		   
		   roleObj.setRoleCode(aotForm.getRoleCode());
		   roleObj.setRoleName(aotForm.getRoleName());		
		   roleObj.setUseFlag(aotForm.getUseFlag());			
		   roleObjBean.update(roleObj,bgObjID,rcID);
		}
		
		//新增角色信息
		if(aotForm.getOperate().equals("insert")){
			request.setAttribute("action", "NewSave");
			BmsRole roleObj = new BmsRole();	
			roleObj.setRoleCode(aotForm.getRoleCode());
			roleObj.setRoleName(aotForm.getRoleName());		
			roleObj.setUseFlag(aotForm.getUseFlag());			
			roleObjBean.insert(roleObj,bgObjID,rcID);
		}
		return null;
	}
}
