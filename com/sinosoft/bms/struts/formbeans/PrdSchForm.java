/**
 *
 * Created on 2009-4-13
 * @author sunrui
 *
 */
package com.sinosoft.bms.struts.formbeans;

import org.apache.struts.action.ActionForm;

/**
 * @author sunrui
 *
 */
public class PrdSchForm extends ActionForm {

	public String prdSchID = null;
	public String prdSchCode = null;
	public String prdSchName = null;
	public String yearFlag = null;
	public String halfYearFlag = null;
	public String quarterFlag = null;
	public String monthFlag = null;
	public String actionType = null;
	
	/**
	 * 
	 */
	public PrdSchForm() {
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
	 * @return the halfYearFlag
	 */
	public String getHalfYearFlag() {
		return halfYearFlag;
	}

	/**
	 * @param halfYearFlag the halfYearFlag to set
	 */
	public void setHalfYearFlag(String halfYearFlag) {
		this.halfYearFlag = halfYearFlag;
	}

	/**
	 * @return the monthFlag
	 */
	public String getMonthFlag() {
		return monthFlag;
	}

	/**
	 * @param monthFlag the monthFlag to set
	 */
	public void setMonthFlag(String monthFlag) {
		this.monthFlag = monthFlag;
	}

	/**
	 * @return the prdSchCode
	 */
	public String getPrdSchCode() {
		return prdSchCode;
	}

	/**
	 * @param prdSchCode the prdSchCode to set
	 */
	public void setPrdSchCode(String prdSchCode) {
		this.prdSchCode = prdSchCode;
	}

	/**
	 * @return the prdSchID
	 */
	public String getPrdSchID() {
		return prdSchID;
	}

	/**
	 * @param prdSchID the prdSchID to set
	 */
	public void setPrdSchID(String prdSchID) {
		this.prdSchID = prdSchID;
	}

	/**
	 * @return the prdSchName
	 */
	public String getPrdSchName() {
		return prdSchName;
	}

	/**
	 * @param prdSchName the prdSchName to set
	 */
	public void setPrdSchName(String prdSchName) {
		this.prdSchName = prdSchName;
	}

	/**
	 * @return the quarterFlag
	 */
	public String getQuarterFlag() {
		return quarterFlag;
	}

	/**
	 * @param quarterFlag the quarterFlag to set
	 */
	public void setQuarterFlag(String quarterFlag) {
		this.quarterFlag = quarterFlag;
	}

	/**
	 * @return the yearFlag
	 */
	public String getYearFlag() {
		return yearFlag;
	}

	/**
	 * @param yearFlag the yearFlag to set
	 */
	public void setYearFlag(String yearFlag) {
		this.yearFlag = yearFlag;
	}

	

}
