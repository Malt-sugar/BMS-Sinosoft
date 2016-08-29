package com.sinosoft.bms.service.budgets;

import java.util.List;

import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.framework.DAO;

public class AnalyseQueryImpl implements AnalyseQuery{

	protected DAO dao = null;	
	/**
	 * 
	 */
	public AnalyseQueryImpl(DAO dao) {
		this.dao=dao;
	}
//	public SSRS queryAnalyseWithCond(String tpId) throws Exception {		
//		// TODO Auto-generated method stub
//		ExeSQL exeSql;
//		SSRS ssrs;		
//		String strSql = " select tpID,tpCode,tpName,tpType,bgschid,prdschid from bms_templet where tpid='"+tpId+"'";
//		System.out.println("======="+strSql);
//		exeSql = new ExeSQL();
//		ssrs = exeSql.execSQL(strSql);
//		if(ssrs.getMaxRow()==0)
//			throw new RuntimeException("缺少表样信息!");
//		
//		return ssrs;
//	}
	public BmsTemplet[] queryAnalyseWithCond(String tpId) throws Exception {		
		// TODO Auto-generated method stub
		BmsTemplet[] bt=null;
		String strSql = " from BmsTemplet bt where bt.tpId="+Integer.parseInt(tpId)+"";
		System.out.println("======="+strSql);
		List list = dao.query(strSql);
		if(list.size()>0){
			bt = new BmsTemplet[list.size()];
			list.toArray(bt);
		}
		return bt;
	}
}
