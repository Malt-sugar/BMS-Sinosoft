/**
 *
 * Created on 2009-4-13
 * @author sunrui
 *
 */
package com.sinosoft.bms.webbeans;

import java.io.*;

/**
 * @author sunrui
 *
 */
public abstract class WebBean {

	public String id = null;
	public Writer out = null;
	/**
	 * 
	 */
	public WebBean() {
	}

	public abstract String getHTML() throws Exception;

	/**
	 * @return the out
	 */
	public Writer getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(Writer out) {
		this.out = out;
	}
	
	public void writeHTML() throws Exception {
		String html = getHTML();
		PrintWriter pw = new PrintWriter(out);
		pw.print(html);
	}
	
	
}
