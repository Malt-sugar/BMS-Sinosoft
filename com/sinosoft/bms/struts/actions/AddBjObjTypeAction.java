package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsObjectType;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.ObjectType;
import com.sinosoft.bms.struts.formbeans.AddBjObjTypeForm;

public class AddBjObjTypeAction extends BmsDataAction{
	public AddBjObjTypeAction(){
		
	}
	
	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AddBjObjTypeForm aotForm = (AddBjObjTypeForm) actionform;
		ObjectType objTypeBean = (ObjectType) BeanFactory.getBean("ObjectType");
		
		//删除主体类型信息
		if(aotForm.getOperate().equals("delete")){
		   request.setAttribute("action", "DELETE");
		   List inputList = aotForm.getDelBmsObjType();
		   objTypeBean.delete(inputList);
		}
		
		//修改操作
		if(aotForm.getOperate().equals("update")){
		   request.setAttribute("action", "EditSave");
		   BmsObjectType bot = new BmsObjectType();
		   bot.setBgObjTypeId(Integer.parseInt(aotForm.getBjObjTypeId()));
		   bot.setBjObjTypeCode(aotForm.getBjObjTypeCode());
		   bot.setBjObjTypeName(aotForm.getBjObjTypeName());
		   objTypeBean.update(bot);		   
		}
		
		//新增主体类型信息
		if(aotForm.getOperate().equals("insert")){
			request.setAttribute("action", "NewSave");
			BmsObjectType bot = new BmsObjectType();
			bot.setBjObjTypeCode(aotForm.getBjObjTypeCode());
			bot.setBjObjTypeName(aotForm.getBjObjTypeName());
			objTypeBean.insert(bot);
		}
		return null;
	}

}
