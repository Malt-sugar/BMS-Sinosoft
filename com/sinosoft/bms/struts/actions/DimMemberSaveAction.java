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

import com.sinosoft.bms.entity.BmsDimMember;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.Dim;
import com.sinosoft.bms.struts.formbeans.DimMemberForm;

/**
 * @author sunrui
 *
 */
public class DimMemberSaveAction extends BmsDataAction {

	/**
	 * 
	 */
	public DimMemberSaveAction() {
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
			BmsDimMember vo = bean.queryMemberByID(Integer.parseInt(id));
			
			Object [][] data = {
					{"dimMemID",new Integer(vo.getDimMemId())},
					{"dimMemCode",vo.getDimMemCode()},
					{"dimMemName",vo.getDimMemName()},
					{"operSysCode",vo.getOperSysCode()},
					{"parentDimMem",vo.getParentDimMem()},
					{"dimID",new Integer(vo.getBmsDim().getDimId())}
			};
			
			request.setAttribute("data", data);
			
			return null;
		}
		
		BmsDimMember dm = new BmsDimMember();
		
		DimMemberForm myform = (DimMemberForm) form;
		
		dm.setDimMemCode(myform.getDimMemCode());
		dm.setDimMemName(myform.getDimMemName());
		dm.setOperSysCode(myform.getOperSysCode());
		dm.setParentDimMem(myform.getParentDimMem()==null?null:(new Integer(myform.getParentDimMem())));
		dm.setBmsDim(bean.queryDimByID(Integer.parseInt(myform.getDimID())));
		
		if(actionType.equalsIgnoreCase("newSave")) {
			bean.insertDimMember(dm);
			request.setAttribute("action", ACTION_NEW_SAVE);
		} else if(actionType.equalsIgnoreCase("editSave")) {
			dm.setDimMemId(Integer.parseInt(myform.getDimMemID()));
			request.setAttribute("action", ACTION_EDIT_SAVE);
			bean.updateDimMember(dm);
		} else {
			throw new Exception("DimSaveAction指定的操作类型不支持");
		}
		
		return null;
	}

}
