/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.clientcommon;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.sinosoft.bms.common.RemoteCallParam;
import com.sinosoft.bms.common.RemoteCallResult;

/**
 * @author sunrui
 *
 */
public class ClientCallRemote {

	public static String host = "127.0.0.1";
	public static int port = 9090;
	public static String contextPath = "bms";
	public static String path = "remotecall";
	public static String jsessionid = null;
	
	protected static Logger logger = Logger.getLogger(ClientCallRemote.class);
	
	/**
	 * 
	 */
	public ClientCallRemote() {
	}

	public static RemoteCallResult remoteCall(RemoteCallParam callParam) throws Exception {
			
		long t_start = System.currentTimeMillis();
		//logger.debug("RemoteCall:"+callParam.getBeanName()+"."+callParam.getMethodName());
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream objOut = new ObjectOutputStream(bout);
		objOut.writeObject(callParam);
		byte [] reqBytes = bout.toByteArray();
		
		
		HttpClient hc = new HttpClient();
		
		
		
		String postUrl = "http://"+host+":"+port+"/"+contextPath+"/"+path;
		//System.out.println(postUrl);
		PostMethod postMethod = new PostMethod(postUrl);
				
		ByteArrayRequestEntity reqEntity = new ByteArrayRequestEntity(reqBytes);
		postMethod.setRequestEntity(reqEntity);
		
		
		
		int statusCode = hc.executeMethod(postMethod);
		
		byte [] responseBody = null;
		
		if(statusCode==HttpStatus.SC_OK) {
			responseBody = postMethod.getResponseBody();
		} else {
			throw new Exception("Http请求错误，返回码："+statusCode);
		}
		
		
		ByteArrayInputStream bin = new ByteArrayInputStream(responseBody);
		ObjectInputStream objIn = new ObjectInputStream(bin);
		Object objResult = objIn.readObject();
		
		long t_end = System.currentTimeMillis();
		logger.debug("RemoteCall:"+callParam.getBeanName()+"."+callParam.getMethodName()+" time:"+(t_end-t_start)+"ms");
		if(objResult==null) {
			throw new Exception("远程调用返回数据不正确");
		} else {
			RemoteCallResult callResult = (RemoteCallResult)objResult;
			if(callResult.getException()!=null) {
				throw callResult.getException();
			}
			return callResult;
		}
		
	}
}
