package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class BmsItemObjClientImpl implements com.sinosoft.bms.service.bd.BmsItemObj { 
	public String beanName = "BmsItemObj";

	public void delete(java.util.List arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("delete");
		param.setParamClasses(new Class[]{java.util.List.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void insert(com.sinosoft.bms.entity.BmsItem arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insert");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsItem.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void update(com.sinosoft.bms.entity.BmsItem arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("update");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsItem.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public com.sinosoft.utility.SSRS queryBmsItemObj() throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsItemObj");
		param.setParamClasses(new Class[]{});
		param.setParams(new Object[]{});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.utility.SSRS) result.getResult();
		} else {
			return null;
		}

	}
	public java.util.List queryParentItemInfo(java.lang.String arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryParentItemInfo");
		param.setParamClasses(new Class[]{java.lang.String.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (java.util.List) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsItem[] queryAllItems() throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryAllItems");
		param.setParamClasses(new Class[]{});
		param.setParams(new Object[]{});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsItem[]) result.getResult();
		} else {
			return null;
		}

	}
}
