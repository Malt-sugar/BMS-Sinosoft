/**
 *
 * Created on 2009-4-9
 * @author sunrui
 *
 */
package com.sinosoft.bms.filters;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * @author sunrui
 *
 */
public class CheckSessionFilter implements Filter {

	protected static Logger logger = Logger.getLogger(CheckSessionFilter.class);
	protected static HashMap hmExPage = null;
	protected static Object objMutex = new Object();
	protected FilterConfig config = null;
	/**
	 * 
	 */
	public CheckSessionFilter() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}
	
	public void loadExPageMap() throws Exception {
		synchronized (objMutex) {
			if(hmExPage==null) {
				logger.debug("加载排除页面信息");
				hmExPage = new HashMap();
				String strExPageCount = config.getInitParameter("ExPageCount");
				if(strExPageCount!=null) {
					int exPageCount = Integer.parseInt(strExPageCount);
					for (int i = 0; i < exPageCount; i++) {
						String exPageUrl = config.getInitParameter("ExPage"+i);
						hmExPage.put(exPageUrl, null);
					}
				}
			}
		}
	}
	
	public boolean isExcludePath(String path) throws Exception {
		loadExPageMap();
		return hmExPage.containsKey(path);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		boolean allowed = false;
		
		try {
			if(request instanceof HttpServletRequest) {
				HttpServletRequest httpRequest = (HttpServletRequest) request;
				String path = httpRequest.getServletPath();
				if(!isExcludePath(path)) {
					HttpSession session = httpRequest.getSession();
					if(session!=null) {
						Object objUser = session.getAttribute("BmsUser");
						if(objUser!=null) {
							allowed = true;
						}
					}
				} else {
					allowed=true;
				}
			}
			
			if(allowed) {
				chain.doFilter(request, response);
			} else {
				request.getRequestDispatcher("/timeout.jsp").forward(request, response);
			}
		} catch (Exception e) {
			logger.error("CheckSession过滤器出错", e);
			throw new ServletException(e.getMessage());
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

}
