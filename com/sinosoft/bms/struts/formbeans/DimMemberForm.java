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
public class DimMemberForm extends ActionForm {

	public String dimMemID = null;
	public String dimMemCode = null;
	public String dimMemName = null;
	public String operSysCode = null;
	public String parentDimMem = null;
	public String dimID = null;
	public String actionType = null;
	
	/**
	 * 
	 */
	public DimMemberForm() {
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
	 * @return the dimMemCode
	 */
	public String getDimMemCode() {
		return dimMemCode;
	}

	/**
	 * @param dimMemCode the dimMemCode to set
	 */
	public void setDimMemCode(String dimMemCode) {
		this.dimMemCode = dimMemCode;
	}

	/**
	 * @return the dimMemID
	 */
	public String getDimMemID() {
		return dimMemID;
	}

	/**
	 * @param dimMemID the dimMemID to set
	 */
	public void setDimMemID(String dimMemID) {
		this.dimMemID = dimMemID;
	}

	/**
	 * @return the dimMemName
	 */
	public String getDimMemName() {
		return dimMemName;
	}

	/**
	 * @param dimMemName the dimMemName to set
	 */
	public void setDimMemName(String dimMemName) {
		this.dimMemName = dimMemName;
	}

	/**
	 * @return the operSysCode
	 */
	public String getOperSysCode() {
		return operSysCode;
	}

	/**
	 * @param operSysCode the operSysCode to set
	 */
	public void setOperSysCode(String operSysCode) {
		this.operSysCode = operSysCode;
	}

	/**
	 * @return the parentDimMem
	 */
	public String getParentDimMem() {
		return parentDimMem;
	}

	/**
	 * @param parentDimMem the parentDimMem to set
	 */
	public void setParentDimMem(String parentDimMem) {
		this.parentDimMem = parentDimMem;
	}

}
