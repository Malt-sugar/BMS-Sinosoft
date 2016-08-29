package com.sinosoft.bms.clientstub.budgets;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class AnalyseQueryClientImpl implements com.sinosoft.bms.service.budgets.AnalyseQuery { 
	public String beanName = "AnalyseQuery";

	public com.sinosoft.bms.entity.BmsTemplet[] queryAnalyseWithCond(java.lang.String arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryAnalyseWithCond");
		param.setParamClasses(new Class[]{java.lang.String.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsTemplet[]) result.getResult();
		} else {
			return null;
		}

	}
}
