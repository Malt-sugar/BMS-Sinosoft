package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsRoleClass;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.RoleClassObj;
import com.sinosoft.bms.struts.formbeans.AddRoleClassObjForm;

public class AddRoleClassObjAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("==xxxxxxxxxx--");
		AddRoleClassObjForm aotForm = (AddRoleClassObjForm) actionform;
		RoleClassObj roleClassObjBean = (RoleClassObj) BeanFactory.getBean("RoleClassObj");
		
		//删除角色类信息
		if(aotForm.getOperate().equals("delete")){
		   request.setAttribute("action", "DELETE");
		   List inputList = aotForm.getDelBmsRoleInfo();
		   roleClassObjBean.delete(inputList);
		}
		
		//修改角色类操作
		if(aotForm.getOperate().equals("update")){
		   request.setAttribute("action", "EditSave");
		   BmsRoleClass roleClassObj = new BmsRoleClass();	
		   roleClassObj.setRcid(Integer.parseInt(aotForm.getRcid()));		   
		   roleClassObj.setRccode(aotForm.getRcCode());
		   roleClassObj.setRcname(aotForm.getRcName());	
		   roleClassObjBean.update(roleClassObj);
		}
		
		//新增角色类信息
		if(aotForm.getOperate().equals("insert")){
			request.setAttribute("action", "NewSave");
			BmsRoleClass roleClassObj = new BmsRoleClass();	
			System.out.println("==xxxxxxxxxx--"+aotForm.getRcCode());
			System.out.println("==xxxxxxxxxx--"+aotForm.getRcName());
			roleClassObj.setRccode(aotForm.getRcCode());
			roleClassObj.setRcname(aotForm.getRcName());
			roleClassObjBean.insert(roleClassObj);
		}
		return null;
	}
}
