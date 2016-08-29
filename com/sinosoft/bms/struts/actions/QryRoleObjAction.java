package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsRole;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.RoleObj;
import com.sinosoft.utility.SSRS;

public class QryRoleObjAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		RoleObj roleObjBean = (RoleObj) BeanFactory.getBean("RoleObj");
		List resultList = roleObjBean.queryRoleObj();
		SSRS rs = new SSRS(7);
		if(resultList.size()<=0){
			return null;
		}
		for(int i=0;i<resultList.size();i++){
			BmsRole bmsRole = (BmsRole) resultList.get(i);
			rs.SetText(String.valueOf(bmsRole.getRoleId()));
			rs.SetText(bmsRole.getRoleCode());
			rs.SetText(bmsRole.getRoleName());
			rs.SetText(String.valueOf(bmsRole.getBmsObject().getBgObjId()));
			rs.SetText(String.valueOf(bmsRole.getBmsRoleClass().getRcid()));			
			if(bmsRole.getUseFlag().equals("0")){
			  rs.SetText("Î´ÆôÓÃ");	
			}else{
				rs.SetText("ÆôÓÃ");
			}
			rs.SetText(String.valueOf(bmsRole.getUseFlag()));
		}
		String resultStr =rs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);		
		return null;
	}
}
