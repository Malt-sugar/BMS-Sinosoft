package com.sinosoft.bms.framework;

public interface ICodeConst {
	//票据类型
	String RECEIPT_TYPE_GENERAL = "0";  //通用支票
	String RECEIPT_TYPE_CASH = "1";	   //现金支票
	String RECEIPT_TYPE_TRANSFER = "2"; //转帐支票
	String RECEIPT_TYPE_TELE = "3";     //电汇单
	
	//使用状态
	String USE_STATE_UNUSE = "0";       //未使用
	String USE_STATE_PRINTED = "1";     //已打印
	String USE_STATE_REPRINT = "2";     //重打印
	String USE_STATE_REPEAL = "3";      //已作废
	String USE_STATE_DELETED = "4";     //已删除
	String USE_STATE_CONFIRM = "5";     //回单确认
	
	//所有session中保存的attribute名称
	String ATTR_PRINT_TYPE = "type";				//打印类型（连续打印、合并打印）
	String ATTR_PAY_WAY = "payWay";					//付款方式（支票、电汇单）
	String ATTR_CURR_RECEIPT = "currReceipt";		//当前打印的票据
	String ATTR_PRINT_APPLIES = "printApplies";
	String ATTR_USER_INFO = "userInfo";
	String ATTR_BILL_PRINT_TYPE="billPrintType";   //打印类型的原因
	
	//所有request中保存的attribute的名称
	String ATTR_RESULT_MESSAGE = "rMess";
	String ATTR_RESULT_FLAG = "rFlag";
	String ATTR_RESULT_STRING = "resultStr";
	String ATTR_SQL_STRING = "sqlStr";
	
	//银行账号信息帐户性质设置
//	String ACCOUNT_ATTR_FOUR = "4"; //保单电汇付
//	String ACCOUNT_ATTR_THREE = "3";//保单支票付
//	String ACCOUNT_ATTR_FIVE = "5";//保单OUT付
	String ACCOUNT_ATTR_SIX = "3";//保单付款付
}