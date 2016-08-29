/**
 *
 * Created on 2009-4-9
 * @author sunrui
 *
 */
package com.sinosoft.bms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author sunrui
 *
 */
public class SetCharacterEncodingFilter implements Filter {

	protected FilterConfig config = null;
	/**
	 * 
	 */
	public SetCharacterEncodingFilter() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		String encoding = config.getInitParameter("encoding");
		String ignore = config.getInitParameter("ignore");
		
		if(ignore!=null && ignore.equalsIgnoreCase("true")) {
			
		} else {
			if(encoding!=null) {
				request.setCharacterEncoding(encoding);
			}
		}
		
		
		chain.doFilter(request, response);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		config = filterConfig;
	}

}
