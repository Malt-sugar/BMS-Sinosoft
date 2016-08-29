/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.main;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sinosoft.bms.framework.DAO;

/**
 * @author sunrui
 *
 */
public class CommonServiceImpl implements CommonService {

	protected DAO dao = null;
	/**
	 * 
	 */
	public CommonServiceImpl(DAO dao) {
		this.dao=dao;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.main.CommonService#queryData(java.lang.String)
	 */
	public Object[][] queryData(String sql) throws Exception {
		List list = dao.queryWithJDBC(sql);
		Object [][] data = null;
		data = new Object[list.size()][];
		for (int i = 0; i < data.length; i++) {
			ListOrderedMap map = (ListOrderedMap)list.get(i);
			List rowList = map.asList();
			data[i] = new Object[rowList.size()];
			for (int j = 0; j < data[i].length; j++) {
				data[i][j] = map.getValue(j);
			}
			
		}
		
		return data;
	}

}
