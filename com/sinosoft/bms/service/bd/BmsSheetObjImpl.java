package com.sinosoft.bms.service.bd;

import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.bms.framework.DAO;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;

public class BmsSheetObjImpl implements BmsSheetObj{

	protected DAO dao = null;
	public BmsSheetObjImpl(DAO dao){
		this.dao=dao;
	}
	
	/**
	 * @ ���ݽ���������ѯ��Ԥ�������Ϣ
	 */
	public SSRS queryBmsSheetInfo(BmsSheet sheet,String bgObjId,int userid) throws Exception{
		ExeSQL exeSql;
		SSRS ssrs = new SSRS(15);		
	   
//		String sheetSql = " select st.sheetId,st.sheetCode,st.sheetTitle,st.bgObjID,"
//			            + " bc.bgObjCode,bc.bgObjName,st.addUser,bu.userName,st.addDate,"
//			            + " st.apprFlag,st.gatheredFlag,st.enabledFlag "
//			            + " from bms_sheet st "
//			            + " join bms_object bc on st.bgObjId=bc.bgObjId "
//			            + " join bms_user bu on st.addUser=bu.userid "
//			            + " where 1=1 "
//		                //+ getWhereSql("st.sheetId",String.valueOf(sheet.getSheetId()))
//		                //+ getWhereSql("st.addUser",String.valueOf(sheet.getAddUser()))
//		                + getWhereSql("st.apprFlag",String.valueOf(sheet.getApprFlag()))
//		                + getWhereSql("st.gatheredFlag",String.valueOf(sheet.getGatheredFlag()))
//		                + getWhereSql("st.enabledFlag",String.valueOf(sheet.getEnabledFlag()));
//		if(sheet.getSheetId()!=0){
//			sheetSql += " and st.sheetId='"+sheet.getSheetId()+"'";
//		}
//		if(sheet.getAddUser()!=null){
//			sheetSql += " and st.addUser='"+sheet.getAddUser()+"'";
//		}
		
		String sheetSql = "";
		sheetSql+="select st.sheetId,st.sheetCode,st.sheetTitle,st.bgObjID, ";
		sheetSql+="       bc.bgObjCode,bc.bgObjName,st.addUser,                                                       ";
		sheetSql+="       bu.userName,st.addDate, st.apprFlag,st.gatheredFlag,st.enabledFlag                          ";
		sheetSql+="from bms_sheet st,bms_object bc,bms_user bu,                                                       ";
		sheetSql+="     bms_userbgobj uo,bms_role r,bms_roleclass rc,bms_userrole ur,bms_templet tp                   ";
		sheetSql+=" where st.bgobjid=bc.bgobjid and st.adduser=bu.userid                                              ";
		sheetSql+="      and st.repflag='1' and tp.ApprRoleID=rc.rcid                                                 ";
		sheetSql+="      and st.bgobjid=uo.bgobjid and uo.userid=ur.userid and tp.tpid=st.tpid                        ";
		sheetSql+="      and uo.bgobjid=r.bgobjid and r.rcid=rc.rcid and ur.roleid=r.roleid and uo.userid="+userid+"          ";
		
		System.out.println("=====��Ԥ���SQL===="+sheetSql);
		try{
			exeSql = new ExeSQL();
			SSRS rs = exeSql.execSQL(sheetSql);
			if(rs.getMaxRow()==0)
				throw new RuntimeException("û����˵������Ϣ!");
			
			for(int i=1;i<=rs.getMaxRow();i++){
				ssrs.SetText(rs.GetText(i,1));
				ssrs.SetText(rs.GetText(i,2));
				ssrs.SetText(rs.GetText(i,3));
				ssrs.SetText(rs.GetText(i,4));
				ssrs.SetText(rs.GetText(i,5));
				ssrs.SetText(rs.GetText(i,6));
				ssrs.SetText(rs.GetText(i,7));
				ssrs.SetText(rs.GetText(i,8));
				ssrs.SetText(rs.GetText(i,9));
				ssrs.SetText(rs.GetText(i,10));
				if(rs.GetText(i,10).equals("0")){
					ssrs.SetText("�����");
				}else if(rs.GetText(i,10).equals("1")){
					ssrs.SetText("���ͨ��");
				}else{
					ssrs.SetText("��˲�ͨ��");
				}
				ssrs.SetText(rs.GetText(i,11));
				if(rs.GetText(i,11).equals("0")){
					ssrs.SetText("������");
				}else{
					ssrs.SetText("�ѻ���");
				}
				ssrs.SetText(rs.GetText(i,12));
				if(rs.GetText(i,12).equals("0")){
					ssrs.SetText("������");
				}else if(rs.GetText(i,12).equals("1")){
					ssrs.SetText("����ͨ��");
				}else{
					ssrs.SetText("������");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return ssrs;
	}
	
	/**
	 * @ ִ�����ͨ����ȡ����ˡ���˲�ͨ������
	 * @ ִ������ͨ����ȡ��������������ͨ������
	 */
	public void update(String sheetid,String operate) throws Exception{
		String updateSql="";
		if(operate.equals("passApprove")){//���ͨ��
			updateSql = " update bms_sheet set apprFlag='1' where sheetid='"+sheetid+"'";			
		}else if(operate.equals("againstApprove")){//��˲�ͨ��
			updateSql = " update bms_sheet set apprFlag='2' where sheetid='"+sheetid+"'";
		}else if(operate.equals("cancelApprove")){//ȡ�����
			updateSql = " update bms_sheet set apprFlag='0' where sheetid='"+sheetid+"'";
		}else if(operate.equals("passAgain")){//����ͨ��
			updateSql = " update bms_sheet set enabledFlag='1' where sheetid='"+sheetid+"'";
		}else if(operate.equals("againstAgain")){//������ͨ��
			updateSql = " update bms_sheet set enabledFlag='2' where sheetid='"+sheetid+"'";
		}else{//ȡ������
			updateSql = " update bms_sheet set enabledFlag='0' where sheetid='"+sheetid+"'";
		}
		System.out.println("==========="+updateSql);
		dao.excute(updateSql);
	}
	
	/**
	 * �����ֶ������ֶ�ֵȡ��Where�Ӿ�
	 *
	 * @param fieldCode String
	 * @param fieldValue String
	 * @return String
	 */
	public String getWhereSql(String fieldCode, String fieldValue) {
		if (fieldValue == null || fieldValue.trim().equals("")) {
			return "";
		} else {
			return " and " + fieldCode + "='" + fieldValue.trim() + "'";
		}
	}

}
