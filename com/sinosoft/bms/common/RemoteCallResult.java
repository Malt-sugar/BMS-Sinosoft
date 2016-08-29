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
public class RemoteCallResult implements Serializable {

	public Object result = null;
	public Exception exception = null;
	/**
	 * 
	 */
	public RemoteCallResult() {
	}
	/**
	 * @return the result
	 */
	public Object getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(Object result) {
		this.result = result;
	}
	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}
	/**
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
	

}
