package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsObjectType;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.ObjectType;
import com.sinosoft.utility.SSRS;

public class QryBjObjTypeAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		ObjectType objTypeBean = (ObjectType) BeanFactory.getBean("ObjectType");
		List list = objTypeBean.queryBmsObjType();
		SSRS rs = new SSRS(3);
		for(int i=0;i<list.size();i++){
			BmsObjectType bmsType = (BmsObjectType) list.get(i);
			rs.SetText(String.valueOf(bmsType.getBgObjTypeId()));
			rs.SetText(bmsType.getBjObjTypeCode());
		    rs.SetText(bmsType.getBjObjTypeName());			
		}
		String resultStr =rs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);		
		return null;
	}
}
