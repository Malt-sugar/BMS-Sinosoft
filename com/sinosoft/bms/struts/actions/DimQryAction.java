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
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.Dim;
import com.sinosoft.utility.SSRS;

/**
 * @author sunrui
 *
 */
public class DimQryAction extends CitiQueryAction {

	/**
	 * 
	 */
	public DimQryAction() {
	}


	/* (non-Javadoc)
	 * @see com.sinosoft.bms.framework.CitiQueryAction#citiExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward citiExecute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
		Dim bean = (Dim) BeanFactory.getBean("Dim");
		BmsDim [] dims = bean.queryDim(null);
		SSRS ssrs = new SSRS(4);
		for (int i = 0; i < dims.length; i++) {
			ssrs.SetText(""+dims[i].getDimId());
			ssrs.SetText(dims[i].getDimCode());
			ssrs.SetText(dims[i].getDimName());
			ssrs.SetText(dims[i].getMemStruct().equals("1")?"Ê÷ÐÎ":"Æ½ÐÐ");
		}
		String resultStr = ssrs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);
		
		return null;
	}

}
