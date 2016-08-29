package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.CitiQueryAction;
import com.sinosoft.bms.framework.ICodeConst;
import com.sinosoft.bms.service.bd.BgObj;
import com.sinosoft.utility.SSRS;

public class QryBgObjAction extends CitiQueryAction{

	public ActionForward citiExecute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse httpServletResponse) throws Exception {
		
		BgObj bgObjBean = (BgObj) BeanFactory.getBean("BgObj");
		List resultList = bgObjBean.queryBgObj();
		SSRS rs = new SSRS(8);
		if(resultList.size()<=0){
			return null;
		}
		for(int i=0;i<resultList.size();i++){
			BmsObject bmsObj = (BmsObject) resultList.get(i);
			rs.SetText(String.valueOf(bmsObj.getBgObjId()));
			rs.SetText(String.valueOf(bmsObj.getBmsObjectType().getBgObjTypeId()));
			rs.SetText(bmsObj.getBgObjCode());
			rs.SetText(bmsObj.getBgObjName());
			//rs.SetText((String.valueOf(bmsObj.getParentBgObj())==null?"":String.valueOf(bmsObj.getParentBgObj())));
			if(bmsObj.getParentBgObj()==null){
				rs.SetText(" ");
			}else{
				rs.SetText(String.valueOf(bmsObj.getParentBgObj()));
			}
			rs.SetText(bmsObj.getOperSysCode());
			if(bmsObj.getUseFlag().equals("0")){
			  rs.SetText("Î´ÆôÓÃ");	
			}else{
				rs.SetText("ÆôÓÃ");
			}
			rs.SetText(String.valueOf(bmsObj.getUseFlag()));
		}
		String resultStr =rs.encode();
		request.setAttribute(ICodeConst.ATTR_RESULT_STRING, resultStr);		
		return null;
	}
}
