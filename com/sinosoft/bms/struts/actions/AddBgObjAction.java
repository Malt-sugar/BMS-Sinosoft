package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsObjectType;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.BgObj;
import com.sinosoft.bms.struts.formbeans.AddBgObjForm;

public class AddBgObjAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AddBgObjForm aotForm = (AddBgObjForm) actionform;
		BgObj bgObjBean = (BgObj) BeanFactory.getBean("BgObj");
		String bgObjTypeID = aotForm.getBgObjTypeID();
		
		//删除主体类型信息
		if(aotForm.getOperate().equals("delete")){
		   request.setAttribute("action", "DELETE");
		   List inputList = aotForm.getDelBmsObjInfo();
		   bgObjBean.delete(inputList);
		}
		
		//修改操作
		if(aotForm.getOperate().equals("update")){
		   request.setAttribute("action", "EditSave");
		   BmsObject bmsObj = new BmsObject();
		   bmsObj.setBgObjId(Integer.parseInt(aotForm.getBgObjID()));
		   bmsObj.setBgObjCode(aotForm.getBgObjCode());
		   bmsObj.setBgObjName(aotForm.getBgObjName());
		   bmsObj.setOperSysCode(aotForm.getOperSysCode());
			if(aotForm.getParentBgObj()!=null&&!"".equals(aotForm.getParentBgObj().trim())){
				  bmsObj.setParentBgObj(new Integer(aotForm.getParentBgObj().trim()));
				}
			char userFlag = 0;
			if(aotForm.getUseFlag()!=null&&!"".equals(aotForm.getUseFlag())){
				for(int i=0;i<aotForm.getUseFlag().length();i++){
					userFlag = aotForm.getUseFlag().charAt(i);
				}
			}			
			bmsObj.setUseFlag(new Character(userFlag));
			bmsObj.setOperSysCode(aotForm.getOperSysCode());
			bgObjBean.update(bmsObj,bgObjTypeID);
		}
		
		//新增主体类型信息
		if(aotForm.getOperate().equals("insert")){
			request.setAttribute("action", "NewSave");
			BmsObject bmsObj = new BmsObject();
			bmsObj.setBgObjCode(aotForm.getBgObjCode());
			bmsObj.setBgObjName(aotForm.getBgObjName());	
			if(aotForm.getParentBgObj()!=null&&!"".equals(aotForm.getParentBgObj().trim())){
			  bmsObj.setParentBgObj(new Integer(aotForm.getParentBgObj().trim()));
			}
			bmsObj.setOperSysCode(aotForm.getOperSysCode());
			char userFlag = 0;
			if(aotForm.getUseFlag()!=null&&!"".equals(aotForm.getUseFlag())){
				for(int i=0;i<aotForm.getUseFlag().length();i++){
					userFlag = aotForm.getUseFlag().charAt(i);
				}
			}			
			bmsObj.setUseFlag(new Character(userFlag));
			bgObjBean.insert(bmsObj,bgObjTypeID);
		}
		return null;
	}
}
