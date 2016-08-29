package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class RoleObjClientImpl implements com.sinosoft.bms.service.bd.RoleObj { 
	public String beanName = "RoleObj";

	public void delete(java.util.List arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("delete");
		param.setParamClasses(new Class[]{java.util.List.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void insert(com.sinosoft.bms.entity.BmsRole arg0,java.lang.String arg1,java.lang.String arg2) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insert");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsRole.class,java.lang.String.class,java.lang.String.class});
		param.setParams(new Object[]{arg0,arg1,arg2});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void update(com.sinosoft.bms.entity.BmsRole arg0,java.lang.String arg1,java.lang.String arg2) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("update");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsRole.class,java.lang.String.class,java.lang.String.class});
		param.setParams(new Object[]{arg0,arg1,arg2});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public java.util.List queryRoleObj() throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryRoleObj");
		param.setParamClasses(new Class[]{});
		param.setParams(new Object[]{});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (java.util.List) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsRole queryRoleByID(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryRoleByID");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsRole) result.getResult();
		} else {
			return null;
		}

	}
}
