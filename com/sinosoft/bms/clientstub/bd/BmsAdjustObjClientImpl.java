package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class BmsAdjustObjClientImpl implements com.sinosoft.bms.service.bd.BmsAdjustObj { 
	public String beanName = "BmsAdjustObj";

	public void delete(java.util.List arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("delete");
		param.setParamClasses(new Class[]{java.util.List.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void insert(com.sinosoft.bms.entity.BmsAdjustBill arg0,java.lang.String arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insert");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsAdjustBill.class,java.lang.String.class});
		param.setParams(new Object[]{arg0,arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void update(com.sinosoft.bms.entity.BmsAdjustBill arg0,java.lang.String arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("update");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsAdjustBill.class,java.lang.String.class});
		param.setParams(new Object[]{arg0,arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void update(java.lang.String arg0,java.lang.String arg1,java.lang.String arg2) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("update");
		param.setParamClasses(new Class[]{java.lang.String.class,java.lang.String.class,java.lang.String.class});
		param.setParams(new Object[]{arg0,arg1,arg2});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public com.sinosoft.utility.SSRS queryBmsAdjustBill(com.sinosoft.bms.entity.BmsAdjustBill arg0,java.lang.String arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsAdjustBill");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsAdjustBill.class,java.lang.String.class});
		param.setParams(new Object[]{arg0,arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.utility.SSRS) result.getResult();
		} else {
			return null;
		}

	}
}
