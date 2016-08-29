package com.sinosoft.bms.swing.refmodel;

import com.sinosoft.bms.swing.common.DefaultRefModel;

public class BmsTempleteModel extends DefaultRefModel{

	/**
	 * 
	 */
	public BmsTempleteModel() {
		setFields(new String[]{"TpID","TpCode","TpName"});
		setTitles(new String[]{"ID","表样编码","表样名称"});
		setTableName("Bms_Templet");
	}
}
