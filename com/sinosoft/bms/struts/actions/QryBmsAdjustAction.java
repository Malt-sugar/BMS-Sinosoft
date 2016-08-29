package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.BmsAdjustObj;
import com.sinosoft.bms.struts.formbeans.AddBmsAdjustForm;
import com.sinosoft.utility.SSRS;

public class QryBmsAdjustAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		AddBmsAdjustForm adjustForm = (AddBmsAdjustForm) actionForm;
		BmsAdjustObj billBean = (BmsAdjustObj) BeanFactory.getBean("BmsAdjustObj");
		SSRS ssrs  = billBean.queryBmsAdjustBill(adjustForm.getBmsAdjustBill(),adjustForm.getBgObjID());
		
		String resultStr = ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);
		return null;
	}
}
