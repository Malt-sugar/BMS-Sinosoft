package com.sinosoft.bms.service.bd;

import com.sinosoft.bms.framework.DAO;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;

public class BmsSheetItemObjImpl implements BmsSheetItemObj{
	
	protected DAO dao = null;
	public BmsSheetItemObjImpl(DAO dao){
		this.dao=dao;
	}

	public SSRS querySheetItem(String bgObjId,String dimId) throws Exception {
		// TODO Auto-generated method stub
		ExeSQL exeSql;
		SSRS ssrs;
		String strSql = " select bi.itemCode,bi.itemName,bs.itemVal,bs.planValFlag,bs.byFormulaFlag "
			          + " From bms_sheetItem bs "
			          + " join bms_item bi on bs.itemId = bi.itemId "
			          + " join bms_sheet st on bs.sheetId = st.sheetId ";
		if(dimId!=null&&!"".equals(dimId.trim())){
			strSql += " and st.tpParamDimID='"+dimId+"'";
		}
		strSql += " where 1=1 ";
		if(bgObjId!=null&&!"".equals(bgObjId.trim())){
		  strSql += "  and bs.bgObjID='"+bgObjId+"'";
		}
		System.out.println("========"+strSql);
		exeSql = new ExeSQL();
		ssrs = exeSql.execSQL(strSql);
		if(ssrs.getMaxRow()==0)
			throw new RuntimeException("未找到预算表项目信息!");
		
		return ssrs;
	}

}
