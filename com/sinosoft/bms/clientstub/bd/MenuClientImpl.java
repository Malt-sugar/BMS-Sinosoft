package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class MenuClientImpl implements com.sinosoft.bms.service.bd.Menu { 
	public String beanName = "Menu";

	public com.sinosoft.bms.entity.BmsMenu[] queryMenu() throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryMenu");
		param.setParamClasses(new Class[]{});
		param.setParams(new Object[]{});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsMenu[]) result.getResult();
		} else {
			return null;
		}

	}
}
