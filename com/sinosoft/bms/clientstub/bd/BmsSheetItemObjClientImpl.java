package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class BmsSheetItemObjClientImpl implements com.sinosoft.bms.service.bd.BmsSheetItemObj { 
	public String beanName = "BmsSheetItemObj";

	public com.sinosoft.utility.SSRS querySheetItem(java.lang.String arg0,java.lang.String arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("querySheetItem");
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
