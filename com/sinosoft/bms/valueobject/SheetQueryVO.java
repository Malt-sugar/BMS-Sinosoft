/**
 *
 * Created on 2009-5-18
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import java.io.Serializable;


/**
 * @author sunrui
 *
 */
public class SheetQueryVO implements Serializable {

	public int inputStatus = 0;
	public int repStatus = 0;
	public int userID = 0;
	
	public int gatherFlag = 0;
	/**
	 * 
	 */
	public SheetQueryVO() {
		
	}

	/**
	 * @return the inputStatus
	 */
	public int getInputStatus() {
		return inputStatus;
	}

	/**
	 * @param inputStatus the inputStatus to set
	 */
	public void setInputStatus(int inputStatus) {
		this.inputStatus = inputStatus;
	}

	/**
	 * @return the repStatus
	 */
	public int getRepStatus() {
		return repStatus;
	}

	/**
	 * @param repStatus the repStatus to set
	 */
	public void setRepStatus(int repStatus) {
		this.repStatus = repStatus;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the gatherFlag
	 */
	public int getGatherFlag() {
		return gatherFlag;
	}

	/**
	 * @param gatherFlag the gatherFlag to set
	 */
	public void setGatherFlag(int gatherFlag) {
		this.gatherFlag = gatherFlag;
	}

}
