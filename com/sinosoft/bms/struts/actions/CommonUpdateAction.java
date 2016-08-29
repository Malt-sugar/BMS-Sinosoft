/**
 *
 * Created on 2009-4-16
 * @author sunrui
 *
 */
package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.DAO;
import com.sinosoft.bms.struts.formbeans.CommonUpdateForm;

/**
 * @author sunrui
 * 该类用于执行update类型的操作，包括删除操作
 * 
 */
public class CommonUpdateAction extends Action {

	/**
	 * 
	 */
	public CommonUpdateAction() {
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String jscontent = "";
		CommonUpdateForm myform = null;
		try {
			myform = (CommonUpdateForm) form;
			doCommonUpdate(myform);
			jscontent = "parent."+myform.getAfterMethod()+"(true,'');";
		} catch (Exception e) {
			e.printStackTrace();
			jscontent = "parent."+myform.getAfterMethod()+"(false,'"+e.getMessage()+"');";
		}
		request.setAttribute("jscontent", jscontent);
		request.getRequestDispatcher("/main/doscript.jsp").forward(request, response);
		return null;
	}
	
	public void doCommonUpdate(CommonUpdateForm form) throws Exception {
		String updateName = form.getUpdateName();
		String param0 = form.getParam0();
		String param1 = form.getParam1();
		String param2 = form.getParam2();
		String param3 = form.getParam3();
		String param4 = form.getParam4();
		DAO dao = (DAO)BeanFactory.getBean("dao");
		
		if(updateName.equalsIgnoreCase("DeletePrdSch")) {
			dao.excute("delete from bms_prdsch where PrdSchID in ("+param0+")");
		}
		
		if(updateName.equalsIgnoreCase("DeleteDim")) {
			dao.excute("delete from bms_dim where DimID in ("+param0+")");
		}
		
		
	}
	
	

}
