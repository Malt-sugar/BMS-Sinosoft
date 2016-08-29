package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsScheme;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.BmsSchemeObj;
import com.sinosoft.bms.struts.formbeans.AddBudgetSchemeFrom;

public class AddBudgetSchemeAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AddBudgetSchemeFrom aotForm = (AddBudgetSchemeFrom) actionform;
		BmsSchemeObj schemeBean = (BmsSchemeObj) BeanFactory.getBean("BmsSchemeObj");
		String bgObjId = aotForm.getBgObjID();
		
		
		//删除预算方案信息
		if(aotForm.getOperate().equals("delete")){
		   request.setAttribute("action", "DELETE");
		   List inputList = aotForm.getDelBmsObjInfo();
		   schemeBean.delete(inputList);
		}
		
		//修改预算方案操作
		if(aotForm.getOperate().equals("update")){
		   request.setAttribute("action", "EditSave");
		   BmsScheme bs = new BmsScheme();	
		   bs.setBgSchId(Integer.parseInt(aotForm.getBgSchID()));
		   bs.setBgSchCode(aotForm.getBgSchCode());
		   bs.setBgSchName(aotForm.getBgSchName());
		   bs.setStartDate(aotForm.getStartDate());
		   bs.setEndDat(aotForm.getEndDat());
		   char flag=0;
		   if(aotForm.getUseFlag()!=null&&!"".equals(aotForm.getUseFlag().trim())){
			   for(int i=0;i<aotForm.getUseFlag().length();i++){
				   flag = aotForm.getUseFlag().charAt(i);
			   }
		   }
		   bs.setUseFlag(new Character(flag));
		   schemeBean.update(bs,bgObjId);
		}
		
		//新增预算项目信息
		if(aotForm.getOperate().equals("insert")){
			request.setAttribute("action", "NewSave");
			   BmsScheme bs = new BmsScheme();				
			   bs.setBgSchCode(aotForm.getBgSchCode());
			   bs.setBgSchName(aotForm.getBgSchName());
			   bs.setStartDate(aotForm.getStartDate());
			   bs.setEndDat(aotForm.getEndDat());
			   char flag=0;
			   if(aotForm.getUseFlag()!=null&&!"".equals(aotForm.getUseFlag().trim())){
				   for(int i=0;i<aotForm.getUseFlag().length();i++){
					   flag = aotForm.getUseFlag().charAt(i);
				   }
			   }
			   bs.setUseFlag(new Character(flag));
			   schemeBean.insert(bs,bgObjId);
		}
		
		//启用、取消启用
		if(aotForm.getOperate().equals("openUse")){
			  request.setAttribute("action", "DELETE");
			  schemeBean.update(String.valueOf(aotForm.getBgSchID()),aotForm.getUseType());
		}
		return null;
	}
}
