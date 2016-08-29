/**
 *
 * Created on 2009-4-9
 * @author sunrui
 *
 */
package com.sinosoft.bms.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.DodsStarter;

/**
 * @author sunrui
 *
 */
public class BmsInitServlet extends HttpServlet {

	protected static Logger logger = Logger.getLogger(BmsInitServlet.class);
	
	/**
	 * 
	 */
	public BmsInitServlet() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		logger.info("初始化BMS系统...");
		DodsStarter.startup();
		new BeanFactory();
	}

}
