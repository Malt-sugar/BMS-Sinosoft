package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class BmsAlertObjClientImpl implements com.sinosoft.bms.service.bd.BmsAlertObj { 
	public String beanName = "BmsAlertObj";

	public com.sinosoft.utility.SSRS queryBmsAlert(java.lang.String arg0,java.lang.String arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsAlert");
		param.setParamClasses(new Class[]{java.lang.String.class,java.lang.String.class});
		param.setParams(new Object[]{arg0,arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.utility.SSRS) result.getResult();
		} else {
			return null;
		}

	}
}
