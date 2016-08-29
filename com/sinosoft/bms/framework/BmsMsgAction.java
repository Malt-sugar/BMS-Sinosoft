/**
 *
 * Created on 2009-4-9
 * @author sunrui
 *
 */
package com.sinosoft.bms.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * @author sunrui
 *
 */
public abstract class BmsMsgAction extends Action {

	protected static Logger logger = Logger.getLogger("BmsAction");
	/**
	 * 
	 */
	public BmsMsgAction() {
	}

	/* (non-Javadoc)
	 * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		try {
			forward = bmsExecute(mapping, form, request, response);
		} catch (Exception e) {
			logger.error("BmsAction∑¢…˙“Ï≥£", e);
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/main/msg.jsp").forward(request, response);
		}
		
		return forward;
	}
	
	public abstract ActionForward bmsExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception ;

}
