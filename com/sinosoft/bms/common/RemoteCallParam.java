/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.common;

import java.io.Serializable;

/**
 * @author sunrui
 *
 */
public class RemoteCallParam implements Serializable {

	public String beanName = null;
	public Object [] params = null;
	public String methodName = null;
	public Class [] paramClasses = null;
	
	/**
	 * 
	 */
	public RemoteCallParam() {
	}

	/**
	 * @return the beanName
	 */
	public String getBeanName() {
		return beanName;
	}

	/**
	 * @param beanName the beanName to set
	 */
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Object[] params) {
		this.params = params;
	}

	/**
	 * @return the paramClasses
	 */
	public Class[] getParamClasses() {
		return paramClasses;
	}

	/**
	 * @param paramClasses the paramClasses to set
	 */
	public void setParamClasses(Class[] paramClasses) {
		this.paramClasses = paramClasses;
	}

}
