package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.UserRoleObj;
import com.sinosoft.bms.struts.formbeans.AddUserRoleForm;
import com.sinosoft.utility.SSRS;

public class QryUserRoleAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		AddUserRoleForm userRole = (AddUserRoleForm) actionForm;
		System.out.println("========"+userRole.getAlginType());
		SSRS ssrs=null;
		UserRoleObj userRoleBean = (UserRoleObj) BeanFactory.getBean("UserRoleObj");
		if(userRole.getAlginType().equals("role")){
			ssrs= userRoleBean.queryUserRoleAll();
		}
		if(userRole.getAlginType().equals("budget")){
			ssrs= userRoleBean.queryBmsObjectAll();
		}
		String resultStr =ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);		
		return null;
		
	}
}
