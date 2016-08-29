package com.sinosoft.bms.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.BmsItemObj;
import com.sinosoft.bms.struts.formbeans.AddBmsItemForm;

public class AddBmsItemAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AddBmsItemForm aotForm = (AddBmsItemForm) actionform;
		BmsItemObj itemBean = (BmsItemObj) BeanFactory.getBean("BmsItemObj");
		
		
		//删除预算项目信息
		if(aotForm.getOperate().equals("delete")){
		   request.setAttribute("action", "DELETE");
		   List inputList = aotForm.getDelBmsItemInfo();
		   itemBean.delete(inputList);
		}
		
		//修改预算项目操作
		if(aotForm.getOperate().equals("update")){
		   request.setAttribute("action", "EditSave");
		   BmsItem item = new BmsItem();	
		   item.setItemId(Integer.parseInt(aotForm.getItemID()));		   
		   item.setItemCode(aotForm.getItemCode());
		   item.setItemName(aotForm.getItemName());		
		    if(aotForm.getParentItem()!=null&&!"".equals(aotForm.getParentItem().trim())){
			      item.setParentItem(new Integer(aotForm.getParentItem().trim()));
			    }
		   item.setItemType(aotForm.getItemType());
		   item.setSubjCode(aotForm.getSubjCode());
		   item.setFormula(aotForm.getFormula());
		   itemBean.update(item);
		}
		
		//新增预算项目信息
		if(aotForm.getOperate().equals("insert")){
			request.setAttribute("action", "NewSave");
			BmsItem item = new BmsItem();	
		    item.setItemCode(aotForm.getItemCode());
		    item.setItemName(aotForm.getItemName());	
		    if(aotForm.getParentItem()!=null&&!"".equals(aotForm.getParentItem().trim())){
		      item.setParentItem(new Integer(aotForm.getParentItem().trim()));
		    }
		    item.setItemType(aotForm.getItemType());
		    item.setSubjCode(aotForm.getSubjCode());
		    item.setFormula(aotForm.getFormula());
		    itemBean.insert(item);
		}
		return null;
	}

}
