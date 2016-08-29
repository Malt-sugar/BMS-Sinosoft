package com.sinosoft.bms.service.bd;

import com.sinosoft.bms.framework.DAO;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;

public class BmsAlertObjImpl implements BmsAlertObj{
	
	protected DAO dao=null;
	
	public BmsAlertObjImpl(DAO dao){
		this.dao = dao;
	}

	public SSRS queryBmsAlert(String bgObjid, String dimid) throws Exception {
		// TODO Auto-generated method stub
		ExeSQL exeSql;
		SSRS ssrs = null;
		String strSql = " select bi.itemCode,bi.itemName,bd.dimCode,bd.dimName,"
			          + " bsi.itemVal,ba.normalPercent,ba.alertPercent,ba.seriousPercent "
			          + " from Bms_Alert ba "
			          + " join Bms_Item bi on bi.itemid = ba.itemid "
			          + " join Bms_SheetItem bsi on bi.itemid = bsi.itemid "
			          + " join Bms_Sheet bs on bs.sheetid = bsi.sheetid ";
		if(bgObjid!=null&&!"".equals(bgObjid)){
			strSql += " and bs.bgObjId = '"+bgObjid+"'";
		}
		strSql  += " join Bms_TpParamDim btd on btd.tpParamDimID = bs.tpParamDimID "
			     + " join Bms_Dim bd on bd.dimId = btd.dimid ";
		if(dimid!=null&&!"".equals(dimid)){
			strSql += " and bd.dimid = '"+dimid+"'";
		}
		strSql  += " where 1=1 ";
		System.out.println("======Ô¤¾¯SQL===="+strSql);
		try{
			exeSql = new ExeSQL();
			ssrs = exeSql.execSQL(strSql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ssrs;
	}

}
