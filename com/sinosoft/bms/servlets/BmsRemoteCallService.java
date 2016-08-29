/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sinosoft.bms.common.RemoteCallParam;
import com.sinosoft.bms.common.RemoteCallResult;
import com.sinosoft.bms.framework.BeanFactory;

/**
 * @author sunrui
 *
 */
public class BmsRemoteCallService extends HttpServlet {

	protected Logger logger = Logger.getLogger(BmsRemoteCallService.class);

	/**
	 * 
	 */
	public BmsRemoteCallService() {
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RemoteCallResult callResult = new RemoteCallResult();
		try {
			
			String contentLength = request.getHeader("content-length");
			int nContentLen = Integer.parseInt(contentLength);
			byte [] buff = new byte[nContentLen];
			InputStream reqIn = request.getInputStream();
			int len = 0;
			int off = 0;
			int left = nContentLen;
			
			while(true) {
				if(left<=0) break;
				len = reqIn.read(buff,off,left);
				if(len==-1) break;
				off+=len;
				left-=len;
			}
			
			
			ObjectInputStream objIn = new ObjectInputStream(new ByteArrayInputStream(buff));
			RemoteCallParam param = (RemoteCallParam)objIn.readObject();
			
			Object objBean = BeanFactory.getBean(param.getBeanName());
			Method method = objBean.getClass().getMethod(param.getMethodName(), param.getParamClasses());
			Object result = method.invoke(objBean, param.getParams());
			callResult.setResult(result);
			
		} catch (Exception e) {
			logger.error("出错",e);
			if(e!=null && e instanceof InvocationTargetException) {
				InvocationTargetException ex = (InvocationTargetException) e;
				try {
					callResult.setException((Exception)ex.getTargetException());
				} catch (Exception ex1) {
					callResult.setException(ex);
				}
			} else {
				callResult.setException(e);
			}
		}
		
		try {
			ObjectOutputStream objOut = new ObjectOutputStream(response.getOutputStream());
			objOut.writeObject(callResult);
			
		} catch (Exception e) {
			logger.error("序列化时出错",e);
			throw new ServletException(e.getMessage());
		}
		
	}

}
