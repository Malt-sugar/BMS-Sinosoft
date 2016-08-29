package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class UserClientImpl implements com.sinosoft.bms.service.bd.User { 
	public String beanName = "User";

	public com.sinosoft.bms.entity.BmsUser queryUserByCode(java.lang.String arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryUserByCode");
		param.setParamClasses(new Class[]{java.lang.String.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsUser) result.getResult();
		} else {
			return null;
		}

	}
	public void insertUser(com.sinosoft.bms.entity.BmsUser arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insertUser");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsUser.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public com.sinosoft.bms.entity.BmsUser queryUserByID(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryUserByID");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsUser) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsUser[] queryUsersByObjID(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryUsersByObjID");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsUser[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsUser[] queryUsersByRoleID(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryUsersByRoleID");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsUser[]) result.getResult();
		} else {
			return null;
		}

	}
}
