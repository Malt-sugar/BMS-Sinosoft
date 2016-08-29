package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class UserRoleObjClientImpl implements com.sinosoft.bms.service.bd.UserRoleObj { 
	public String beanName = "UserRoleObj";

	public void delete(java.util.List arg0,java.lang.String arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("delete");
		param.setParamClasses(new Class[]{java.util.List.class,java.lang.String.class});
		param.setParams(new Object[]{arg0,arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void delete(java.util.List arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("delete");
		param.setParamClasses(new Class[]{java.util.List.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void insert(com.sinosoft.bms.entity.BmsUserRole arg0,java.util.List arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insert");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsUserRole.class,java.util.List.class});
		param.setParams(new Object[]{arg0,arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void insert(com.sinosoft.bms.entity.BmsUserBgObj arg0,java.lang.String arg1,java.util.List arg2) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insert");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsUserBgObj.class,java.lang.String.class,java.util.List.class});
		param.setParams(new Object[]{arg0,arg1,arg2});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void update(com.sinosoft.bms.entity.BmsUserBgObj arg0,java.lang.String arg1,java.util.List arg2) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("update");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsUserBgObj.class,java.lang.String.class,java.util.List.class});
		param.setParams(new Object[]{arg0,arg1,arg2});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void update(com.sinosoft.bms.entity.BmsUserRole arg0,java.util.List arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("update");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsUserRole.class,java.util.List.class});
		param.setParams(new Object[]{arg0,arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public com.sinosoft.utility.SSRS queryUserInfo(java.lang.String arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryUserInfo");
		param.setParamClasses(new Class[]{java.lang.String.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.utility.SSRS) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.utility.SSRS queryUserRoleAll() throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryUserRoleAll");
		param.setParamClasses(new Class[]{});
		param.setParams(new Object[]{});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.utility.SSRS) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.utility.SSRS queryBmsObjectAll() throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsObjectAll");
		param.setParamClasses(new Class[]{});
		param.setParams(new Object[]{});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.utility.SSRS) result.getResult();
		} else {
			return null;
		}

	}
}
