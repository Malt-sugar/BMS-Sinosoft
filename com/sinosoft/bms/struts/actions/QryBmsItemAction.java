package com.sinosoft.bms.struts.actions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.BmsItemObj;
import com.sinosoft.utility.SSRS;

public class QryBmsItemAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		BmsItemObj itemBean = (BmsItemObj) BeanFactory.getBean("BmsItemObj");
		SSRS ssrs = itemBean.queryBmsItemObj();
		System.out.println("=========="+ssrs.getMaxRow());
		String resultStr =ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);		
		return null;
	}
}
