package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsUserBgObj;
import com.sinosoft.bms.entity.BmsUserBgObjId;
import com.sinosoft.bms.entity.BmsUserRole;
import com.sinosoft.bms.entity.BmsUserRoleId;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.UserRoleObj;
import com.sinosoft.bms.struts.formbeans.AddUserRoleForm;

public class AddUserRoleAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AddUserRoleForm aotForm = (AddUserRoleForm) actionform;		
		UserRoleObj roleObjBean = (UserRoleObj) BeanFactory.getBean("UserRoleObj");
		
		String parentRoleId = (String) request.getParameter("roleID");	
		List roleIdList = null;
		if(!"delete".equals(aotForm.getOperate())){
		   roleIdList = aotForm.getRoleList(parentRoleId);
		}
		if(aotForm.getAlginType().equals("role")){
			//删除用户关联角色信息
			if(aotForm.getOperate().equals("delete")){
			   request.setAttribute("action", "DELETE");
			   List inputList = aotForm.getDelBmsUserRole();
			   roleObjBean.delete(inputList);
			}
			
			//修改用户关联角色操作
			if(aotForm.getOperate().equals("update")){
			   request.setAttribute("action", "EditSave");
				BmsUserRole bmsRole = new BmsUserRole();
				BmsUserRoleId bmsRoleId = new BmsUserRoleId();
				//bmsRoleId.setRoleId(new Integer(aotForm.getParentRoleId()));
				bmsRoleId.setUserId(new Integer(aotForm.getUserId()));
				bmsRole.setId(bmsRoleId);
				roleObjBean.update(bmsRole,roleIdList);
			}
			
			//新增用户角色信息
			if(aotForm.getOperate().equals("insert")){
				request.setAttribute("action", "NewSave");
				BmsUserRole bmsRole = new BmsUserRole();
				BmsUserRoleId bmsRoleId = new BmsUserRoleId();
				//bmsRoleId.setRoleId(new Integer(aotForm.getParentRoleId()));
				bmsRoleId.setUserId(new Integer(aotForm.getUserId()));
				bmsRole.setId(bmsRoleId);
				roleObjBean.insert(bmsRole,roleIdList);
			}
		}
		if(aotForm.getAlginType().equals("budget")){
			//删除预算主体信息
			if(aotForm.getOperate().equals("delete")){
			   request.setAttribute("action", "DELETE");
			   List inputList = aotForm.getDelBmsUserBgObj();
			   roleObjBean.delete(inputList,"budget");
			}
			
			//修改操作
			if(aotForm.getOperate().equals("update")){
			   request.setAttribute("action", "EditSave");
				BmsUserBgObj object = new BmsUserBgObj();
				BmsUserBgObjId objectId = new BmsUserBgObjId();
				//objectId.setBgObjId(new Integer(aotForm.getParentBgObjId()));
				objectId.setUserId(new Integer(aotForm.getUserId()));
				object.setId(objectId);
				roleObjBean.update(object,"budget",roleIdList);
			}
			
			//新增用户角色信息
			if(aotForm.getOperate().equals("insert")){
				request.setAttribute("action", "NewSave");
				BmsUserBgObj object = new BmsUserBgObj();
				BmsUserBgObjId objectId = new BmsUserBgObjId();
				//objectId.setBgObjId(new Integer(aotForm.getParentBgObjId()));
				objectId.setUserId(new Integer(aotForm.getUserId()));
				object.setId(objectId);
				roleObjBean.insert(object,"budget",roleIdList);
			}
			
		}
		return null;
	}
}
