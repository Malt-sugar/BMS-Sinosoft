package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.BmsUserObj;
import com.sinosoft.bms.struts.formbeans.AddBmsUserForm;
import com.sinosoft.utility.SSRS;

public class QryBmsUserAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		AddBmsUserForm userObj = (AddBmsUserForm) actionForm;
		BmsUserObj userBean = (BmsUserObj) BeanFactory.getBean("BmsUserObj");
		String  userCode =userObj.getUserCode();
		String  userName =userObj.getUserName();		
		String  bgObjId  =userObj.getBgObjId();
		String  useFlag  =userObj.getUseFlag();
		String  email    =userObj.getEmail();
		String  phone    =userObj.getPhone();		
		
		
		SSRS ssrs = userBean.queryUserInfo(userCode,userName,bgObjId,useFlag,email,phone);
		
		String resultStr =ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);		
		return null;
	}
}
