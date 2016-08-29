package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.BmsSheetObj;
import com.sinosoft.bms.struts.formbeans.BudgetApproveForm;

public class BudgetApproveAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BudgetApproveForm approveFrom =(BudgetApproveForm) actionform;
		BmsSheetObj sheetObj = (BmsSheetObj) BeanFactory.getBean("BmsSheetObj");
		request.setAttribute("action", "DELETE");
		sheetObj.update(approveFrom.getSheetID(),approveFrom.getOperate());
		
		return null;
	}
}
