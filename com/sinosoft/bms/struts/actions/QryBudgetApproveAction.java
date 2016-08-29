package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.BmsSheetObj;
import com.sinosoft.bms.struts.formbeans.BudgetApproveForm;
import com.sinosoft.utility.SSRS;

public class QryBudgetApproveAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		BudgetApproveForm approveForm = (BudgetApproveForm) actionForm;
		BmsSheetObj sheetObj = (BmsSheetObj) BeanFactory.getBean("BmsSheetObj");	
		
		String bgObjID = approveForm.getBgObjID();
		
		HttpSession session =  request.getSession();
		BmsUser user = (BmsUser)session.getAttribute("BmsUser");
		
		SSRS ssrs = sheetObj.queryBmsSheetInfo(approveForm.getQueryCondit(),bgObjID,user.getUserId());
		
		String resultStr = ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);
		return null;
	}
}
