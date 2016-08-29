/**
 *
 * Created on 2009-4-9
 * @author sunrui
 *
 */
package com.sinosoft.bms.struts.formbeans;

import org.apache.struts.action.ActionForm;

/**
 * @author sunrui
 *
 */
public class LoginForm extends ActionForm {

	protected String usercode = null;
	protected String password = null;
	
	/**
	 * 
	 */
	public LoginForm() {
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the usercode
	 */
	public String getUsercode() {
		return usercode;
	}

	/**
	 * @param usercode the usercode to set
	 */
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

}
