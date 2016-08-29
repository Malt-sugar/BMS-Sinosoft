package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class BmsSheetObjClientImpl implements com.sinosoft.bms.service.bd.BmsSheetObj { 
	public String beanName = "BmsSheetObj";

	public void update(java.lang.String arg0,java.lang.String arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("update");
		param.setParamClasses(new Class[]{java.lang.String.class,java.lang.String.class});
		param.setParams(new Object[]{arg0,arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public com.sinosoft.utility.SSRS queryBmsSheetInfo(com.sinosoft.bms.entity.BmsSheet arg0,java.lang.String arg1,int arg2) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsSheetInfo");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsSheet.class,java.lang.String.class,int.class});
		param.setParams(new Object[]{arg0,arg1,new Integer(arg2)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.utility.SSRS) result.getResult();
		} else {
			return null;
		}

	}
}
