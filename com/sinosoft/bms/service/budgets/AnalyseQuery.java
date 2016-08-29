package com.sinosoft.bms.service.budgets;

import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.utility.SSRS;

public interface AnalyseQuery {

	public abstract BmsTemplet[] queryAnalyseWithCond(String tpId) throws Exception;
}
