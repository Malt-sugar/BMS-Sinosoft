/**
 *
 * Created on 2009-5-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.formula;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;

import com.sinosoft.bms.framework.DAO;
import com.sinosoft.bms.valueobject.BmsDouble;
import com.sinosoft.common.BeanFactory;

/**
 * @author sunrui
 *
 */
public class DataGetter implements Serializable {

	public int bgObjID = 0;
	public int dimMemID = 0;
	public int itemID = 0;
	public DAO dao = null;
	
	/**
	 * 
	 */
	public DataGetter() {
	}
	
	public double getActVal(String period) throws Exception {
		
		String sql = "select _val from bmsdw_data where _bgobjID="+bgObjID+" and _dimmemid="+dimMemID+
			" and _itemid="+itemID+" and _period='"+period+"'";
		List list = dao.queryWithJDBC(sql);
		double result = 0.0d;
		if(list.size()>0) {
			ListOrderedMap map = (ListOrderedMap)list.get(0);
			Object obj = map.getValue(0);
			BmsDouble val = new BmsDouble(obj==null?"0":obj.toString());
			result = val.doubleValue();
		}
		return result;
	}

}
