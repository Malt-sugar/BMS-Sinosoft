package com.sinosoft.bms.clientstub.main;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class CommonServiceClientImpl implements com.sinosoft.bms.service.main.CommonService { 
	public String beanName = "CommonService";

	public java.lang.Object[][] queryData(java.lang.String arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryData");
		param.setParamClasses(new Class[]{java.lang.String.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (java.lang.Object[][]) result.getResult();
		} else {
			return null;
		}

	}
}
