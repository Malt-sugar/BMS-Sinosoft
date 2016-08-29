package com.sinosoft.bms.struts.actions;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sinosoft.bms.entity.BmsAdjustBill;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsDataAction;
import com.sinosoft.bms.service.bd.BmsAdjustObj;
import com.sinosoft.bms.struts.formbeans.AddBmsAdjustForm;

public class AddBmsAdjustAction extends BmsDataAction{

	public ActionForward bmsExecute(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		AddBmsAdjustForm aotForm = (AddBmsAdjustForm) actionform;
		BmsAdjustObj billBean = (BmsAdjustObj) BeanFactory.getBean("BmsAdjustObj");
		String bgObjId = aotForm.getBgObjID();
		
		
		//ɾ��Ԥ�������Ϣ
		if(aotForm.getOperate().equals("delete")){
		   request.setAttribute("action", "DELETE");
		   List inputList = aotForm.getDelBmsAdjustBill();
		   billBean.delete(inputList);
		}
		
		//�޸�Ԥ�㷽������
		if(aotForm.getOperate().equals("update")){
		   request.setAttribute("action", "EditSave");
		   BmsAdjustBill adjust = new BmsAdjustBill();	
		   adjust.setAdjBillId(Integer.parseInt(aotForm.getAdjBillID()));
		   adjust.setAdjBillCode(aotForm.getAdjBillCode());
		   adjust.setAdjReason(aotForm.getAdjReason());
		   adjust.setApplyUser(new Integer(aotForm.getApplyUser()));
		   adjust.setApplyDate(aotForm.getApplyDate());
		   adjust.setAdjAmt(new BigDecimal(aotForm.getAdjAmt()));
		   billBean.update(adjust,bgObjId);
		}
		
		//����Ԥ����Ŀ��Ϣ
		if(aotForm.getOperate().equals("insert")){
			request.setAttribute("action", "NewSave");
		    BmsAdjustBill adjust = new BmsAdjustBill();			   
		    adjust.setAdjBillCode(aotForm.getAdjBillCode());
		    adjust.setAdjReason(aotForm.getAdjReason());
		    adjust.setApplyUser(new Integer(aotForm.getApplyUser()));
		    adjust.setApplyDate(aotForm.getApplyDate());
		    adjust.setAdjAmt(new BigDecimal(aotForm.getAdjAmt()));
		    billBean.insert(adjust,bgObjId);
		}
		
		//���ͨ����ȡ����ˡ���˲�ͨ��
		if(aotForm.getOperate().equals("approve")){
			  request.setAttribute("action", "DELETE");
			  billBean.update(aotForm.getAdjustid(),aotForm.getAdjustType(),aotForm.getUserid());
		}
		return null;
	}
}
