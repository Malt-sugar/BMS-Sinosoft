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
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.Dim;
import com.sinosoft.bms.struts.formbeans.DimForm;

/**
 * @author sunrui
 *
 */
public class DimSaveAction extends BmsDataAction {

	/**
	 * 
	 */
	public DimSaveAction() {
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.framework.BmsDataAction#bmsExecute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public ActionForward bmsExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String actionType = request.getParameter("actionType");
		Dim bean = (Dim)BeanFactory.getBean("Dim");
		
		if(actionType.equalsIgnoreCase("editQuery")) {
			String id = request.getParameter("id");
			request.setAttribute("action", ACTION_EDIT_QUERY);
			BmsDim vo = bean.queryDimByID(Integer.parseInt(id));
			
			Object [][] data = {
					{"dimID",new Integer(vo.getDimId())},
					{"dimCode",vo.getDimCode()},
					{"dimName",vo.getDimName()},
					{"memStruct",vo.getMemStruct()}
			};
			
			request.setAttribute("data", data);
			
			return null;
		}
		
		BmsDim dim = new BmsDim();
		DimForm myform = (DimForm) form;
		dim.setDimCode(myform.getDimCode());
		dim.setDimName(myform.getDimName());
		dim.setMemStruct(myform.getMemStruct());
		
		if(actionType.equalsIgnoreCase("newSave")) {
			bean.insertDim(dim);
			request.setAttribute("action", ACTION_NEW_SAVE);
		} else if(actionType.equalsIgnoreCase("editSave")) {
			dim.setDimId(Integer.parseInt(myform.getDimID()));
			request.setAttribute("action", ACTION_EDIT_SAVE);
			bean.updateDim(dim);
		} else {
			throw new Exception("DimSaveAction指定的操作类型不支持");
		}
		
		return null;
	}

}
