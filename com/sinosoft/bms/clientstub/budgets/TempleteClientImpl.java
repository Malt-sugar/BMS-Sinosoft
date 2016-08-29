package com.sinosoft.bms.clientstub.budgets;
import com.sinosoft.bms.clientcommon.ClientCallRemote;
import com.sinosoft.bms.common.*;

public class TempleteClientImpl implements com.sinosoft.bms.service.budgets.Templete { 
	public String beanName = "Templete";

	public void delete(com.sinosoft.bms.entity.BmsTemplet arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("delete");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsTemplet.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public java.lang.Integer insert(com.sinosoft.bms.entity.BmsTemplet arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insert");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsTemplet.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (java.lang.Integer) result.getResult();
		} else {
			return null;
		}

	}
	public void update(com.sinosoft.bms.entity.BmsTemplet arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("update");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsTemplet.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public com.sinosoft.bms.entity.BmsTemplet[] queryAll() throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryAll");
		param.setParamClasses(new Class[]{});
		param.setParams(new Object[]{});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsTemplet[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsTemplet queryTemplete(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryTemplete");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsTemplet) result.getResult();
		} else {
			return null;
		}

	}
	public void saveFile(java.lang.String arg0,byte[] arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("saveFile");
		param.setParamClasses(new Class[]{java.lang.String.class,byte[].class});
		param.setParams(new Object[]{arg0,arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public byte[] loadFile(java.lang.String arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("loadFile");
		param.setParamClasses(new Class[]{java.lang.String.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (byte[]) result.getResult();
		} else {
			return null;
		}

	}
	public void saveTpBgObj(int arg0,com.sinosoft.bms.entity.BmsTpBgObj[] arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("saveTpBgObj");
		param.setParamClasses(new Class[]{int.class,com.sinosoft.bms.entity.BmsTpBgObj[].class});
		param.setParams(new Object[]{new Integer(arg0),arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void saveTpParamDim(int arg0,com.sinosoft.bms.entity.BmsTpParamDim[] arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("saveTpParamDim");
		param.setParamClasses(new Class[]{int.class,com.sinosoft.bms.entity.BmsTpParamDim[].class});
		param.setParams(new Object[]{new Integer(arg0),arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void saveTpRowDim(int arg0,com.sinosoft.bms.entity.BmsTpRowDim[] arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("saveTpRowDim");
		param.setParamClasses(new Class[]{int.class,com.sinosoft.bms.entity.BmsTpRowDim[].class});
		param.setParams(new Object[]{new Integer(arg0),arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void saveTpColDim(int arg0,com.sinosoft.bms.entity.BmsTpColDim[] arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("saveTpColDim");
		param.setParamClasses(new Class[]{int.class,com.sinosoft.bms.entity.BmsTpColDim[].class});
		param.setParams(new Object[]{new Integer(arg0),arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public com.sinosoft.bms.entity.BmsTpBgObj[] queryBmsTpBgObj(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsTpBgObj");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsTpBgObj[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsTpParamDim[] queryBmsTpParamDim(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsTpParamDim");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsTpParamDim[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsTpRowDim[] queryBmsRowDim(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsRowDim");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsTpRowDim[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsTpColDim[] queryBmsColDim(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsColDim");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsTpColDim[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.valueobject.SheetVO[] querySheet(com.sinosoft.bms.valueobject.SheetQueryVO arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("querySheet");
		param.setParamClasses(new Class[]{com.sinosoft.bms.valueobject.SheetQueryVO.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.valueobject.SheetVO[]) result.getResult();
		} else {
			return null;
		}

	}
	public java.lang.Integer insertSheet(com.sinosoft.bms.entity.BmsSheet arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("insertSheet");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsSheet.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (java.lang.Integer) result.getResult();
		} else {
			return null;
		}

	}
	public void updateSheet(com.sinosoft.bms.entity.BmsSheet arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("updateSheet");
		param.setParamClasses(new Class[]{com.sinosoft.bms.entity.BmsSheet.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void deleteSheet(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("deleteSheet");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void saveSheetItems(int arg0,com.sinosoft.bms.entity.BmsSheetItem[] arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("saveSheetItems");
		param.setParamClasses(new Class[]{int.class,com.sinosoft.bms.entity.BmsSheetItem[].class});
		param.setParams(new Object[]{new Integer(arg0),arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public void saveGatherSheetItems(int arg0,com.sinosoft.bms.entity.BmsSheet[] arg1) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("saveGatherSheetItems");
		param.setParamClasses(new Class[]{int.class,com.sinosoft.bms.entity.BmsSheet[].class});
		param.setParams(new Object[]{new Integer(arg0),arg1});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);

	}
	public com.sinosoft.bms.entity.BmsSheet[] queryBmsSheet(java.lang.String arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsSheet");
		param.setParamClasses(new Class[]{java.lang.String.class});
		param.setParams(new Object[]{arg0});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsSheet[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.valueobject.CellVO[] queryCellVO(com.sinosoft.bms.valueobject.TempleteVO arg0,int arg1,int arg2) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryCellVO");
		param.setParamClasses(new Class[]{com.sinosoft.bms.valueobject.TempleteVO.class,int.class,int.class});
		param.setParams(new Object[]{arg0,new Integer(arg1),new Integer(arg2)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.valueobject.CellVO[]) result.getResult();
		} else {
			return null;
		}

	}
	public com.sinosoft.bms.entity.BmsSheetItem[] queryBmsSheetItem(int arg0) throws Exception {
		RemoteCallParam param = new RemoteCallParam();
		param.setBeanName(beanName);
		param.setMethodName("queryBmsSheetItem");
		param.setParamClasses(new Class[]{int.class});
		param.setParams(new Object[]{new Integer(arg0)});
		RemoteCallResult result = ClientCallRemote.remoteCall(param);
		if(result.getResult()!=null) {
			return (com.sinosoft.bms.entity.BmsSheetItem[]) result.getResult();
		} else {
			return null;
		}

	}
}
