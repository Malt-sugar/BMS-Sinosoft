package com.sinosoft.bms.clientstub.main;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class LoginClientImpl implements com.sinosoft.bms.service.main.Login { 
	public String beanName = "Login";

	public com.sinosoft.bms.entity.BmsUser[] queryUser() throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryUser");
		param.setParamClasses(new Class[]{});
		param.setParams(new Object[]{});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsUser[]) result.getResult();
		} else {
			return null;
		}

	}
}
