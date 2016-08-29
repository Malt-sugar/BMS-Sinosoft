package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.BmsSheetItemObj;
import com.sinosoft.bms.struts.formbeans.QryBmsSheetItemForm;
import com.sinosoft.utility.SSRS;

public class QryBmsSheetItemAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping, ActionForm actionForm,
			HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
		// TODO Auto-generated method stub
		
		QryBmsSheetItemForm itemForm = (QryBmsSheetItemForm) actionForm;
		BmsSheetItemObj sheetBean = (BmsSheetItemObj) BeanFactory.getBean("BmsSheetItemObj");
		System.out.println("=====");
		SSRS ssrs =sheetBean.querySheetItem(itemForm.getBgObjID(),itemForm.getDimID());
		
		String resultStr =ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);			
		
		return null;
	}

}
