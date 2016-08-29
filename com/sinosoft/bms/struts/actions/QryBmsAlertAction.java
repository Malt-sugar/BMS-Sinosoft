package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.BmsAlertObj;
import com.sinosoft.bms.struts.formbeans.QryBmsAlertForm;
import com.sinosoft.utility.SSRS;

public class QryBmsAlertAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
		// TODO Auto-generated method stub
		
		QryBmsAlertForm alertForm = (QryBmsAlertForm) actionForm;
		BmsAlertObj alertObj = (BmsAlertObj) BeanFactory.getBean("BmsAlertObj");
		SSRS ssrs = alertObj.queryBmsAlert(alertForm.getBgObjID().trim(),alertForm.getDimID().trim());
		
		String resultStr = ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING,resultStr);
		return null;
	}

}
