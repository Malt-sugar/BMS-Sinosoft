/**
 *
 * Created on 2009-4-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

/**
 * @author sunrui
 *
 */
public class ClientEnv {

	protected static ClientEnv instance = null;
	
	protected String userCode = null;
	protected int userID = 0;
	
	protected MainApplet mainApplet = null;
	/**
	 * 
	 */
	public ClientEnv() {
		super();
	}
	
	public static ClientEnv getInstance() {
		if(instance==null) {
			instance = new ClientEnv();
		}
		return instance;
	}

	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * @return the mainApplet
	 */
	public MainApplet getMainApplet() {
		return mainApplet;
	}

	/**
	 * @param mainApplet the mainApplet to set
	 */
	public void setMainApplet(MainApplet mainApplet) {
		this.mainApplet = mainApplet;
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


}
