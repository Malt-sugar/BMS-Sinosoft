/**
 *
 * Created on 2009-4-15
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
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.PrdSch;
import com.sinosoft.utility.SSRS;

/**
 * @author sunrui
 *
 */
public class PrdSchQryAction extends CitiQueryAction {

	/**
	 * 
	 */
	public PrdSchQryAction() {
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.framework.CitiQueryAction#citiExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		PrdSch prdSchBean = (PrdSch) BeanFactory.getBean("PrdSch");
		BmsPrdSch [] prdschs = prdSchBean.queryAll();
		SSRS ssrs = new SSRS(7);
		for (int i = 0; i < prdschs.length; i++) {
			ssrs.SetText(""+prdschs[i].getPrdSchId());
			ssrs.SetText(prdschs[i].getPrdSchCode());
			ssrs.SetText(prdschs[i].getPrdSchName());
			ssrs.SetText(BmsUtils.BoolCharToChn(prdschs[i].getYearFlag()));
			ssrs.SetText(BmsUtils.BoolCharToChn(prdschs[i].getHalfYearFlag()));
			ssrs.SetText(BmsUtils.BoolCharToChn(prdschs[i].getQuarterFlag()));
			ssrs.SetText(BmsUtils.BoolCharToChn(prdschs[i].getMonthFlag()));
		}
		String resultStr = ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);
		
		return null;
	}

}
