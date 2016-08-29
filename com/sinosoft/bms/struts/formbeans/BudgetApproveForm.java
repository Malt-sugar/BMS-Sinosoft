package com.sinosoft.bms.struts.formbeans;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsSheet;

public class BudgetApproveForm extends ActionForm{
	
	private String sheetID;
	private String bgObjID;
	private String userId;
	private String apprFlag;
	private String gatheredFlag;
	private String enabledFlag;
	private String repFlag;
	private String operate;	
 	
 	/**
	 * @ 获取界面元素
	 * @return
	 */
	public BmsSheet getQueryCondit(){
		BmsSheet sheet = new BmsSheet();
		if(this.getSheetID()!=null&&!"".equals(this.getSheetID().trim())){
		  sheet.setSheetId(Integer.parseInt(this.getSheetID().trim()));
		}
		if(this.getUserId()!=null&&!"".equals(this.getUserId().trim())){
		  sheet.setAddUser(new Integer(this.getUserId().trim()));
		}
		char aflag=0;
		char gflag=0;
		char eflag=0;
		char rflag=0;
		if(this.getApprFlag()!=null&&!"".equals(this.getApprFlag().trim())){
			for(int i=0;i<this.getApprFlag().trim().length();i++){
				aflag=this.getApprFlag().trim().charAt(i);
			}
		}
		if(this.getGatheredFlag()!=null&&!"".equals(this.getGatheredFlag().trim())){
			for(int i=0;i<this.getGatheredFlag().trim().length();i++){
				gflag=this.getGatheredFlag().trim().charAt(i);
			}
		}
		if(this.getEnabledFlag()!=null&&!"".equals(this.getEnabledFlag().trim())){
			for(int i=0;i<this.getEnabledFlag().trim().length();i++){
				eflag=this.getEnabledFlag().trim().charAt(i);
			}
		}
		if(this.getRepFlag()!=null&&!"".equals(this.getRepFlag().trim())){
			for(int i=0;i<this.getRepFlag().trim().length();i++){
				rflag=this.getRepFlag().trim().charAt(i);
			}
		}
		sheet.setApprFlag(new Character(aflag));		
		sheet.setGatheredFlag(new Character(gflag));
		sheet.setEnabledFlag(new Character(eflag));
		sheet.setRepFlag(new Character(rflag));
		return sheet;
	}
	
	/**
	 * @ 获取预算表ID
	 * @return
	 */
	
	
	public String getApprFlag() {
		return apprFlag;
	}
	public void setApprFlag(String apprFlag) {
		this.apprFlag = apprFlag;
	}
	public String getBgObjID() {
		return bgObjID;
	}
	public void setBgObjID(String bgObjID) {
		this.bgObjID = bgObjID;
	}
	public String getEnabledFlag() {
		return enabledFlag;
	}
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	public String getGatheredFlag() {
		return gatheredFlag;
	}
	public void setGatheredFlag(String gatheredFlag) {
		this.gatheredFlag = gatheredFlag;
	}
	public String getRepFlag() {
		return repFlag;
	}
	public void setRepFlag(String repFlag) {
		this.repFlag = repFlag;
	}
	public String getSheetID() {
		return sheetID;
	}
	public void setSheetID(String sheetID) {
		this.sheetID = sheetID;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

}
