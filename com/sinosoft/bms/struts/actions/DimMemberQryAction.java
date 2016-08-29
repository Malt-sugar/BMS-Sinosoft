/**
 *
 * Created on 2009-4-17
 * @author sunrui
 *
 */
package com.sinosoft.bms.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsDim;
import com.sinosoft.bms.entity.BmsDimMember;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.Dim;
import com.sinosoft.utility.SSRS;

/**
 * @author sunrui
 *
 */
public class DimMemberQryAction extends CitiQueryAction {

	/**
	 * 
	 */
	public DimMemberQryAction() {
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.framework.CitiQueryAction#citiExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		Dim bean = (Dim) BeanFactory.getBean("Dim");
		BmsDimMember [] dms = bean.queryDimMem(null);
		SSRS ssrs = new SSRS(6);
		
		for (int i = 0; i < dms.length; i++) {
			BmsDimMember parentDm = bean.queryMemberByID(dms[i].getParentDimMem().intValue());
			ssrs.SetText(""+dms[i].getDimMemId());
			ssrs.SetText(dms[i].getDimMemCode());
			ssrs.SetText(dms[i].getDimMemName());
			ssrs.SetText(dms[i].getOperSysCode());
			ssrs.SetText(parentDm==null?"":parentDm.getDimMemName());
			ssrs.SetText(""+dms[i].getBmsDim().getDimId());
		}
		String resultStr = ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);
		
		return null;
	}

}
