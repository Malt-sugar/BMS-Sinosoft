package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsRoleClass;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.RoleClassObj;
import com.sinosoft.utility.SSRS;

public class QryRoleClassObjAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		RoleClassObj objClassBean = (RoleClassObj) BeanFactory.getBean("RoleClassObj");
		List list = objClassBean.queryRoleClassObj();
		SSRS rs = new SSRS(3);
		for(int i=0;i<list.size();i++){
			BmsRoleClass roleClass = (BmsRoleClass) list.get(i);
			rs.SetText(String.valueOf(roleClass.getRcid()));
			rs.SetText(roleClass.getRccode());
		    rs.SetText(roleClass.getRcname());			
		}
		String resultStr =rs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);		
		return null;
	}
}
