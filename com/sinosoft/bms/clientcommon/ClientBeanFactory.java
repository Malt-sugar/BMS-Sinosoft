package com.sinosoft.bms.clientcommon;
public class ClientBeanFactory {
public static Object getBean(String beanName) throws Exception {
if(false) {
	return null;


} else if(beanName.equals("BgObj")) {
	return new com.sinosoft.bms.clientstub.bd.BgObjClientImpl();
} else if(beanName.equals("BmsAdjustObj")) {
	return new com.sinosoft.bms.clientstub.bd.BmsAdjustObjClientImpl();
} else if(beanName.equals("BmsAlertObj")) {
	return new com.sinosoft.bms.clientstub.bd.BmsAlertObjClientImpl();
} else if(beanName.equals("BmsItemObj")) {
	return new com.sinosoft.bms.clientstub.bd.BmsItemObjClientImpl();
} else if(beanName.equals("BmsSchemeObj")) {
	return new com.sinosoft.bms.clientstub.bd.BmsSchemeObjClientImpl();
} else if(beanName.equals("BmsSheetItemObj")) {
	return new com.sinosoft.bms.clientstub.bd.BmsSheetItemObjClientImpl();
} else if(beanName.equals("BmsSheetObj")) {
	return new com.sinosoft.bms.clientstub.bd.BmsSheetObjClientImpl();
} else if(beanName.equals("BmsUserObj")) {
	return new com.sinosoft.bms.clientstub.bd.BmsUserObjClientImpl();
} else if(beanName.equals("Dim")) {
	return new com.sinosoft.bms.clientstub.bd.DimClientImpl();
} else if(beanName.equals("Menu")) {
	return new com.sinosoft.bms.clientstub.bd.MenuClientImpl();
} else if(beanName.equals("ObjectType")) {
	return new com.sinosoft.bms.clientstub.bd.ObjectTypeClientImpl();
} else if(beanName.equals("PrdSch")) {
	return new com.sinosoft.bms.clientstub.bd.PrdSchClientImpl();
} else if(beanName.equals("RoleClassObj")) {
	return new com.sinosoft.bms.clientstub.bd.RoleClassObjClientImpl();
} else if(beanName.equals("RoleObj")) {
	return new com.sinosoft.bms.clientstub.bd.RoleObjClientImpl();
} else if(beanName.equals("User")) {
	return new com.sinosoft.bms.clientstub.bd.UserClientImpl();
} else if(beanName.equals("UserMenuObj")) {
	return new com.sinosoft.bms.clientstub.bd.UserMenuObjClientImpl();
} else if(beanName.equals("UserRoleObj")) {
	return new com.sinosoft.bms.clientstub.bd.UserRoleObjClientImpl();
} else if(beanName.equals("CommonService")) {
	return new com.sinosoft.bms.clientstub.main.CommonServiceClientImpl();
} else if(beanName.equals("Login")) {
	return new com.sinosoft.bms.clientstub.main.LoginClientImpl();
} else if(beanName.equals("AnalyseQuery")) {
	return new com.sinosoft.bms.clientstub.budgets.AnalyseQueryClientImpl();
} else if(beanName.equals("Templete")) {
	return new com.sinosoft.bms.clientstub.budgets.TempleteClientImpl();
} else {
	throw new Exception("Client Bean not found");
}
}
}
