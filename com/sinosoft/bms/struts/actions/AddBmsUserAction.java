package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsUser;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.BmsUserObj;
import com.sinosoft.bms.struts.formbeans.AddBmsUserForm;

public class AddBmsUserAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AddBmsUserForm aotForm = (AddBmsUserForm) actionform;
		BmsUserObj userObjBean = (BmsUserObj) BeanFactory.getBean("BmsUserObj");
		String bgObjId = aotForm.getBgObjId();
		
		//ɾ����ɫ����Ϣ
		if(aotForm.getOperate().equals("delete")){
		   request.setAttribute("action", "DELETE");
		   List inputList = aotForm.getDelBmsUserObjInfo();
		   userObjBean.delete(inputList);
		}
		
		//�޸Ľ�ɫ�����
		if(aotForm.getOperate().equals("update")){
		   request.setAttribute("action", "EditSave");
		   BmsUser userObj = new BmsUser();	
		   userObj.setUserId(Integer.parseInt(aotForm.getUserId()));		   
		   userObj.setUserCode(aotForm.getUserCode());
		   userObj.setUserName(aotForm.getUserName());	
		   userObj.setPassWord(aotForm.getPassword());		 
		   for(int i=0;i<aotForm.getUseFlag().length();i++){
			   userObj.setUseFlag(new Character(aotForm.getUseFlag().charAt(i)));
		   }
		   userObj.setEmail(aotForm.getEmail());
		   userObj.setPhone(aotForm.getPhone());
		   userObjBean.update(userObj,bgObjId);
		}
		
		//������ɫ����Ϣ
		if(aotForm.getOperate().equals("insert")){
			request.setAttribute("action", "NewSave");
			   BmsUser userObj = new BmsUser();				  		   
			   userObj.setUserCode(aotForm.getUserCode());
			   userObj.setUserName(aotForm.getUserName());	
			   userObj.setPassWord(aotForm.getPassword());			   
			   for(int i=0;i<aotForm.getUseFlag().length();i++){
				   userObj.setUseFlag(new Character(aotForm.getUseFlag().charAt(i)));
			   }
			   userObj.setEmail(aotForm.getEmail());
			   userObj.setPhone(aotForm.getPhone());
			   userObjBean.insert(userObj,bgObjId);
		}
		return null;
	}
}
