/**
 *
 * Created on 2009-4-16
 * @author sunrui
 *
 */
package com.sinosoft.bms.struts.formbeans;

import org.apache.struts.action.ActionForm;

/**
 * @author sunrui
 *
 */
public class CommonUpdateForm extends ActionForm {

	public String updateName = null;
	public String afterMethod = null;
	public String param0 = null;
	public String param1 = null;
	public String param2 = null;
	public String param3 = null;
	public String param4 = null;
	
	
	/**
	 * 
	 */
	public CommonUpdateForm() {
	}


	/**
	 * @return the afterMethod
	 */
	public String getAfterMethod() {
		return afterMethod;
	}


	/**
	 * @param afterMethod the afterMethod to set
	 */
	public void setAfterMethod(String afterMethod) {
		this.afterMethod = afterMethod;
	}


	/**
	 * @return the param0
	 */
	public String getParam0() {
		return param0;
	}


	/**
	 * @param param0 the param0 to set
	 */
	public void setParam0(String param0) {
		this.param0 = param0;
	}


	/**
	 * @return the param1
	 */
	public String getParam1() {
		return param1;
	}


	/**
	 * @param param1 the param1 to set
	 */
	public void setParam1(String param1) {
		this.param1 = param1;
	}


	/**
	 * @return the param2
	 */
	public String getParam2() {
		return param2;
	}


	/**
	 * @param param2 the param2 to set
	 */
	public void setParam2(String param2) {
		this.param2 = param2;
	}


	/**
	 * @return the param3
	 */
	public String getParam3() {
		return param3;
	}


	/**
	 * @param param3 the param3 to set
	 */
	public void setParam3(String param3) {
		this.param3 = param3;
	}


	/**
	 * @return the param4
	 */
	public String getParam4() {
		return param4;
	}


	/**
	 * @param param4 the param4 to set
	 */
	public void setParam4(String param4) {
		this.param4 = param4;
	}


	/**
	 * @return the updateName
	 */
	public String getUpdateName() {
		return updateName;
	}


	/**
	 * @param updateName the updateName to set
	 */
	public void setUpdateName(String updateName) {
		this.updateName = updateName;
	}



}
