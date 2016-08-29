package com.sinosoft.bms.struts.formbeans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsAdjustBill;

public class AddBmsAdjustForm extends ActionForm{
	
	private String adjBillID;
	private String adjBillCode;
	private String bgObjID;
	private String adjReason;
	private String applyUser;
	private String applyDate;
	private String adjAmt;
	private String apprFlag;
	private String userid;
	private String adjustid;
	
	private String operate;
	private String adjustType;

    private String[] hideGridNo;
    private String[] hideGrid1;
    
    /**
     * @获取界面查询条件
     * @return
     */
    public BmsAdjustBill getBmsAdjustBill(){
    	BmsAdjustBill adjust = new BmsAdjustBill();
    	if(this.getAdjBillCode()!=null&&!"".equals(this.getAdjBillCode().trim())){
    		adjust.setAdjBillCode(this.getAdjBillCode().trim());
    	}
    	if(this.getAdjReason()!=null&&!"".equals(this.getAdjReason().trim())){
    		adjust.setAdjReason(this.getAdjReason().trim());
    	}
    	if(this.getApplyUser()!=null&&!"".equals(this.getApplyUser().trim())){
    		adjust.setApplyUser(new Integer(this.getApplyUser().trim()));
    	}
    	if(this.getApplyDate()!=null&&!"".equals(this.getApplyDate().trim())){
    		adjust.setApplyDate(this.getApplyDate().trim());
    	}
    	if(this.getAdjAmt()!=null&&!"".equals(this.getAdjAmt().trim())){
    		adjust.setAdjAmt(new BigDecimal(this.getAdjAmt().trim()));
    	}
    	if(this.getApprFlag()!=null&&!"".equals(this.getApprFlag().trim())){
    		char appflag=0;
    		for(int i=0;i<this.getApprFlag().trim().length();i++){
    			appflag=this.getApprFlag().trim().charAt(i);
    		}
    		adjust.setApprFlag(new Character(appflag));
    	}
    	
    	return adjust;
    }
    
    /**
     * @ 删除预算调整
     * @return
     */
    public List getDelBmsAdjustBill(){
    	List inputList = new ArrayList();
    	int len;
    	if(hideGridNo==null){
    		len=0;
    	}else{
    		len=hideGridNo.length;
    	}
    	for(int i=0;i<len;i++){
    		BmsAdjustBill bmsBill = new BmsAdjustBill();
    		bmsBill.setAdjBillId(Integer.parseInt(hideGrid1[i]));
    		inputList.add(bmsBill);
    	}
		return inputList;    	
    }
    
	public String getAdjAmt() {
		return adjAmt;
	}
	public void setAdjAmt(String adjAmt) {
		this.adjAmt = adjAmt;
	}
	public String getAdjBillCode() {
		return adjBillCode;
	}
	public void setAdjBillCode(String adjBillCode) {
		this.adjBillCode = adjBillCode;
	}
	public String getAdjBillID() {
		return adjBillID;
	}
	public void setAdjBillID(String adjBillID) {
		this.adjBillID = adjBillID;
	}
	public String getAdjReason() {
		return adjReason;
	}
	public void setAdjReason(String adjReason) {
		this.adjReason = adjReason;
	}
	public String getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}
	public String getApplyUser() {
		return applyUser;
	}
	public void setApplyUser(String applyUser) {
		this.applyUser = applyUser;
	}
	public String getBgObjID() {
		return bgObjID;
	}
	public void setBgObjID(String bgObjID) {
		this.bgObjID = bgObjID;
	}
	public String[] getHideGrid1() {
		return hideGrid1;
	}
	public void setHideGrid1(String[] hideGrid1) {
		this.hideGrid1 = hideGrid1;
	}
	public String[] getHideGridNo() {
		return hideGridNo;
	}
	public void setHideGridNo(String[] hideGridNo) {
		this.hideGridNo = hideGridNo;
	}
	public String getApprFlag() {
		return apprFlag;
	}
	public void setApprFlag(String apprFlag) {
		this.apprFlag = apprFlag;
	}

	public String getAdjustType() {
		return adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAdjustid() {
		return adjustid;
	}

	public void setAdjustid(String adjustid) {
		this.adjustid = adjustid;
	}
}
