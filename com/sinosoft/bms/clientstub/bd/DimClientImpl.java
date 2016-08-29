package com.sinosoft.bms.clientstub.bd;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class DimClientImpl implements com.sinosoft.bms.service.bd.Dim { 
	public String beanName = "Dim";

	public com.sinosoft.bms.entity.BmsDim[] queryDim(java.lang.String arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryDim");
		param.setParamClasses(new Class[]{java.lang.String.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsDim[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsDim queryDimByID(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryDimByID");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsDim) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsDimMember[] queryDimMem(java.lang.String arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryDimMem");
		param.setParamClasses(new Class[]{java.lang.String.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsDimMember[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsDimMember queryMemberByID(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryMemberByID");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsDimMember) result.getResult();
		} else {
			return null;
		}

	}
	public void insertDim(com.sinosoft.bms.entity.BmsDim arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insertDim");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsDim.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void updateDim(com.sinosoft.bms.entity.BmsDim arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("updateDim");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsDim.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void insertDimMember(com.sinosoft.bms.entity.BmsDimMember arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insertDimMember");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsDimMember.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void updateDimMember(com.sinosoft.bms.entity.BmsDimMember arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("updateDimMember");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsDimMember.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
}
