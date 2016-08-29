package com.sinosoft.bms.service.bd;

import java.util.List;


import com.sinosoft.bms.entity.BmsAdjustBill;
import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;
import com.sinosoft.utility.DateUtil;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;

public class BmsAdjustObjImpl implements BmsAdjustObj{
	
	protected DAO dao = null;
	//private JdbcTemplate jdbcTemplate;

	public BmsAdjustObjImpl(DAO dao) {
		this.dao=dao;
	}

	/**
	 * @ 根据条件查询预算调整信息
	 */
	public SSRS queryBmsAdjustBill(BmsAdjustBill adjust, String bgObjId) throws Exception {
		// TODO Auto-generated method stub
		ExeSQL exeSql;
		SSRS ssrs = new SSRS(15);
		String strSql = " select bb.adjBillID,bb.adjBillCode,bb.bgObjId,bo.bgObjCode,bo.bgObjName,"
			          + " bb.applyUser,bu.userName,bb.applyDate,bb.apprUser,bu1.userName,bb.apprDate,"
			          + " bb.apprFlag,bb.adjAmt,bb.adjReason "
			          + " from bms_adjustbill bb "
			          + " join bms_object bo on bb.bgObjId=bo.bgObjId "
			          + " join bms_user bu on bu.userid=bb.applyUser "
			          + " left join bms_user bu1 on bu1.userid=bb.apprUser "
			          + " where 1=1 "
			          + getWhereSql("bb.adjBillCode",adjust.getAdjBillCode())
			          + getWhereSql("bb.bgObjId",bgObjId.trim())
			          + getWhereSql("bb.adjReason",adjust.getAdjReason())
			          + getWhereSql("bb.applyUser",(adjust.getApplyUser()==null?"":String.valueOf(adjust.getApplyUser())))
			          + getWhereSql("bb.applyDate>",adjust.getApplyDate())
			          + getWhereSql("bb.adjAmt",(adjust.getAdjAmt()==null?"":adjust.getAdjAmt().toString()))
			          + getWhereSql("bb.apprFlag",(adjust.getApprFlag()==null?"":adjust.getApprFlag().toString()))
			          ;
		try{
			exeSql = new ExeSQL();
			SSRS rs = exeSql.execSQL(strSql);
			//if(rs.getMaxRow()==0)
				//throw new RuntimeException("没有符合条件的数据!");
			
			for(int i=1;rs.getMaxRow()>0&&i<=rs.getMaxRow();i++){
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
				ssrs.SetText(rs.GetText(i,11));
				ssrs.SetText(rs.GetText(i,12));
				if(rs.GetText(i,12).equals("0")){
					ssrs.SetText("待审核");
				}else if(rs.GetText(i,12).equals("1")){
					ssrs.SetText("审核通过");
				}else{
					ssrs.SetText("审核不通过");
				}
				ssrs.SetText(rs.GetText(i,13));
				ssrs.SetText(rs.GetText(i,14));
			}
		}catch(Exception ex){
			ex.printStackTrace();
			throw new RuntimeException("查询异常!");
		}
		
		return ssrs;
	}

	/**
	 * @ 删除预算调整信息
	 */
	public void delete(List inputList) throws Exception {
		// TODO Auto-generated method stub
		if(inputList==null)
			throw new RuntimeException("未找到预算调整信息!");
		
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsAdjustBill adjustBill = (BmsAdjustBill) inputList.get(i);
			dao.delete(adjustBill);
		}
		
	}

	/**
	 * @ 更新预算调整信息
	 */
	public void update(BmsAdjustBill adjust, String bgObjId) throws Exception {
		// TODO Auto-generated method stub
		String hsql = " from BmsObject bo where bo.id.bgObjId='"+bgObjId+"'";
		List list = dao.query(hsql);
		if(list==null)
			throw new RuntimeException("未找到预算主体信息!");
		
		adjust.setBmsObject((BmsObject) list.get(0));
		dao.update(adjust);
	}

	/**
	 * @ 新增预算调整信息
	 */
	public void insert(BmsAdjustBill adjust, String bgObjId) throws Exception {
		// TODO Auto-generated method stub
		String hsql = " from BmsObject bo where bo.id.bgObjId='"+bgObjId+"'";
		List list = dao.query(hsql);
		if(list==null)
			throw new RuntimeException("未找到预算主体信息!");
		
		adjust.setBmsObject((BmsObject) list.get(0));		
		adjust.setAdjBillId(BmsUtils.getID());
		String flag = "0";		
		for(int i=0;i<flag.length();i++){
			adjust.setApprFlag(new Character(flag.charAt(i)));
			adjust.setEnableFlag(new Character(flag.charAt(i)));
			adjust.setHoldFlag(new Character(flag.charAt(i)));
		}
		dao.create(adjust);		
	}

	/**
	 * @ 审核通过、取消审核、审核不通过
	 */
	public void update(String adjBillId, String adjustType,String userid) throws Exception {
		// TODO Auto-generated method stub
		String hsql = " from BmsAdjustBill  bb where bb.id.adjBillId='"+adjBillId+"'";
		//DataSource datasource = (DataSource) BeanFactory.getBean("dataSource");
		try{
			//jdbcTemplate = new JdbcTemplate(datasource);
			List list = dao.query(hsql);
			if(list==null)
				throw new RuntimeException("未找到预算调整信息!");
			for(int i=0;list.size()>0&&i<list.size();i++){
				BmsAdjustBill adjustBill = (BmsAdjustBill) list.get(i);		
				adjustBill.setApprUser(new Integer(userid));//审核人
				adjustBill.setApprDate(DateUtil.simpleDateStr(DateUtil.getNowDate()));//审核日期	
								
				if(adjustType.equals("passApp")){//审核通过
					String flag = "1";
					for(int j=0;j<flag.length();j++){
					  adjustBill.setApprFlag(new Character(flag.charAt(j)));
					}
				}else if(adjustType.equals("againstApp")){//审核不通过
					String flag = "2";
					for(int j=0;j<flag.length();j++){
					  adjustBill.setApprFlag(new Character(flag.charAt(j)));
					}
				}else{//取消审核
					String flag = "0";
					for(int j=0;j<flag.length();j++){
					  adjustBill.setApprFlag(new Character(flag.charAt(j)));
					}
				}		
				dao.update(adjustBill);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * 根据字段名和字段值取得Where子句
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
