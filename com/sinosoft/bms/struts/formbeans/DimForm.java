/**
 *
 * Created on 2009-4-17
 * @author sunrui
 *
 */
package com.sinosoft.bms.struts.formbeans;

import org.apache.struts.action.ActionForm;

/**
 * @author sunrui
 *
 */
public class DimForm extends ActionForm {

	public String dimID = null;
	public String dimCode = null;
	public String dimName = null;
	public String isSysDefine = null;
	public String memStruct = null;
	
	public String actionType = null;
	
	/**
	 * 
	 */
	public DimForm() {
	}

	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}

	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	/**
	 * @return the dimCode
	 */
	public String getDimCode() {
		return dimCode;
	}

	/**
	 * @param dimCode the dimCode to set
	 */
	public void setDimCode(String dimCode) {
		this.dimCode = dimCode;
	}

	/**
	 * @return the dimID
	 */
	public String getDimID() {
		return dimID;
	}

	/**
	 * @param dimID the dimID to set
	 */
	public void setDimID(String dimID) {
		this.dimID = dimID;
	}

	/**
	 * @return the dimName
	 */
	public String getDimName() {
		return dimName;
	}

	/**
	 * @param dimName the dimName to set
	 */
	public void setDimName(String dimName) {
		this.dimName = dimName;
	}

	/**
	 * @return the isSysDefine
	 */
	public String getIsSysDefine() {
		return isSysDefine;
	}

	/**
	 * @param isSysDefine the isSysDefine to set
	 */
	public void setIsSysDefine(String isSysDefine) {
		this.isSysDefine = isSysDefine;
	}

	/**
	 * @return the memStruct
	 */
	public String getMemStruct() {
		return memStruct;
	}

	/**
	 * @param memStruct the memStruct to set
	 */
	public void setMemStruct(String memStruct) {
		this.memStruct = memStruct;
	}

}
