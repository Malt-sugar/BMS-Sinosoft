/**
 *
 * Created on 2009-4-13
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
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.service.bd.PrdSch;
import com.sinosoft.bms.struts.formbeans.PrdSchForm;

/**
 * @author sunrui
 *
 */
public class PrdSchSaveAction extends BmsDataAction {

	/**
	 * 
	 */
	public PrdSchSaveAction() {
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.framework.BmsDataAction#bmsExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward bmsExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		PrdSchForm prdschForm = (PrdSchForm) form;
		BmsPrdSch bmsPrdSch = new BmsPrdSch();
		String actionType = prdschForm.getActionType();
		PrdSch prdSchBean = (PrdSch) BeanFactory.getBean("PrdSch");
		
		bmsPrdSch.setPrdSchCode(prdschForm.getPrdSchCode());
		bmsPrdSch.setPrdSchName(prdschForm.getPrdSchName());
		bmsPrdSch.setYearFlag(BmsUtils.StringToBoolchar(prdschForm.getYearFlag()));
		bmsPrdSch.setHalfYearFlag(BmsUtils.StringToBoolchar(prdschForm.getHalfYearFlag()));
		bmsPrdSch.setQuarterFlag(BmsUtils.StringToBoolchar(prdschForm.getQuarterFlag()));
		bmsPrdSch.setMonthFlag(BmsUtils.StringToBoolchar(prdschForm.getMonthFlag()));
		
		if(actionType.equalsIgnoreCase("newSave")) {
			prdSchBean.insert(bmsPrdSch);
			request.setAttribute("action", ACTION_NEW_SAVE);
		} else if(actionType.equalsIgnoreCase("editSave")) {
			bmsPrdSch.setPrdSchId(Integer.parseInt(prdschForm.getPrdSchID()));
			prdSchBean.update(bmsPrdSch);
			request.setAttribute("action", ACTION_EDIT_SAVE);
		} else {
			throw new Exception("PrdSchSaveAction指定的操作类型不支持");
		}
		
		
		
		
		
		return null;
	}

}
