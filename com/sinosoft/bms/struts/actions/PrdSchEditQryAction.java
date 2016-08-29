/**
 *
 * Created on 2009-4-16
 * @author sunrui
 *
 */
package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsPrdSch;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.PrdSch;

/**
 * @author sunrui
 *
 */
public class PrdSchEditQryAction extends BmsDataAction {

	/**
	 * 
	 */
	public PrdSchEditQryAction() {
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.framework.BmsDataAction#bmsExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward bmsExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String id = request.getParameter("id");
		PrdSch bean = (PrdSch)BeanFactory.getBean("PrdSch");
		request.setAttribute("action", ACTION_EDIT_QUERY);
		
		BmsPrdSch vo = bean.queryById(Integer.parseInt(id));
		Object [][] data = {
				{"prdSchID",new Integer(vo.getPrdSchId())},
				{"prdSchCode",vo.getPrdSchCode()},
				{"prdSchName",vo.getPrdSchName()},
				{"yearFlag",vo.getYearFlag()},
				{"halfYearFlag",vo.getHalfYearFlag()},
				{"quarterFlag",vo.getQuarterFlag()},
				{"monthFlag",vo.getMonthFlag()}
		};
		request.setAttribute("data", data);
		
		return null;
	}

}
