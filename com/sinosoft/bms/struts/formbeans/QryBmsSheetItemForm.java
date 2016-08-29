package com.sinosoft.bms.struts.formbeans;

import org.apache.struts.action.ActionForm;

public class QryBmsSheetItemForm extends ActionForm{
	
	private String bgObjID;
	private String dimID;
	
		
	public String getBgObjID() {
		return bgObjID;
	}
	public void setBgObjID(String bgObjID) {
		this.bgObjID = bgObjID;
	}
	public String getDimID() {
		return dimID;
	}
	public void setDimID(String dimID) {
		this.dimID = dimID;
	}

}
