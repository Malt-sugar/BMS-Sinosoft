package com.sinosoft.bms.framework;

public interface ICodeConst {
	//Ʊ������
	String RECEIPT_TYPE_GENERAL = "0";  //ͨ��֧Ʊ
	String RECEIPT_TYPE_CASH = "1";	   //�ֽ�֧Ʊ
	String RECEIPT_TYPE_TRANSFER = "2"; //ת��֧Ʊ
	String RECEIPT_TYPE_TELE = "3";     //��㵥
	
	//ʹ��״̬
	String USE_STATE_UNUSE = "0";       //δʹ��
	String USE_STATE_PRINTED = "1";     //�Ѵ�ӡ
	String USE_STATE_REPRINT = "2";     //�ش�ӡ
	String USE_STATE_REPEAL = "3";      //������
	String USE_STATE_DELETED = "4";     //��ɾ��
	String USE_STATE_CONFIRM = "5";     //�ص�ȷ��
	
	//����session�б����attribute����
	String ATTR_PRINT_TYPE = "type";				//��ӡ���ͣ�������ӡ���ϲ���ӡ��
	String ATTR_PAY_WAY = "payWay";					//���ʽ��֧Ʊ����㵥��
	String ATTR_CURR_RECEIPT = "currReceipt";		//��ǰ��ӡ��Ʊ��
	String ATTR_PRINT_APPLIES = "printApplies";
	String ATTR_USER_INFO = "userInfo";
	String ATTR_BILL_PRINT_TYPE="billPrintType";   //��ӡ���͵�ԭ��
	
	//����request�б����attribute������
	String ATTR_RESULT_MESSAGE = "rMess";
	String ATTR_RESULT_FLAG = "rFlag";
	String ATTR_RESULT_STRING = "resultStr";
	String ATTR_SQL_STRING = "sqlStr";
	
	//�����˺���Ϣ�ʻ���������
//	String ACCOUNT_ATTR_FOUR = "4"; //������㸶
//	String ACCOUNT_ATTR_THREE = "3";//����֧Ʊ��
//	String ACCOUNT_ATTR_FIVE = "5";//����OUT��
	String ACCOUNT_ATTR_SIX = "3";//�������
}