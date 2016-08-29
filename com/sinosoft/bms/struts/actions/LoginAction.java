/**
 *
 * Created on 2009-4-9
 * @author sunrui
 * ��¼����
 * 
 */
package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsMsgAction;
import com.sinosoft.bms.framework.EncryptNew;
import com.sinosoft.bms.service.bd.User;
import com.sinosoft.bms.struts.formbeans.LoginForm;

/**
 * @author sunrui
 *
 */
public class LoginAction extends BmsMsgAction {

	/**
	 * 
	 */
	public LoginAction() {
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.framework.BmsAction#bmsExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward bmsExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.debug("�����¼Action");
		LoginForm loginForm = (LoginForm)form;
		String usercode = loginForm.getUsercode();
		String password = loginForm.getPassword();
		if(usercode==null||usercode.trim().length()==0) throw new Exception("�û�������Ϊ��");
		if(password==null||password.trim().length()==0) throw new Exception ("���벻��Ϊ��");
		
		String pwd = EncryptNew.EnCode(password);
		
		User userBean = (User)BeanFactory.getBean("User");
		BmsUser user = userBean.queryUserByCode(usercode);
		if(user==null) throw new Exception ("�û�������");
		if(!pwd.equals(user.getPassWord())) {
			throw new Exception ("���벻��ȷ");
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("BmsUser", user);
		
		return mapping.findForward("success");
	}

}
