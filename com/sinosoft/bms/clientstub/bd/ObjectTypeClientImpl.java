package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class ObjectTypeClientImpl implements com.sinosoft.bms.service.bd.ObjectType { 
	public String beanName = "ObjectType";

	public void delete(java.util.List arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("delete");
		param.setParamClasses(new Class[]{java.util.List.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void insert(com.sinosoft.bms.entity.BmsObjectType arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insert");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsObjectType.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void update(com.sinosoft.bms.entity.BmsObjectType arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("update");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsObjectType.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public java.util.List queryBmsObjType() throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsObjType");
		param.setParamClasses(new Class[]{});
		param.setParams(new Object[]{});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (java.util.List) result.getResult();
		} else {
			return null;
		}

	}
}
