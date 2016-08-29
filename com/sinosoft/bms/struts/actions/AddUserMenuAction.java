package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.UserMenuObj;
import com.sinosoft.bms.struts.formbeans.AddUserMenuForm;

public class AddUserMenuAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AddUserMenuForm _form = (AddUserMenuForm) actionform;
		UserMenuObj _obj = (UserMenuObj) BeanFactory.getBean("UserMenuObj");
		
		String _menuId = request.getParameter("menuid");
		request.setAttribute("action","NewSave");
		_obj.update(_form.getUserMenuCode(_menuId));
		return null;
	}
}
