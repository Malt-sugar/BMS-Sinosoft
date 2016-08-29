/**
 *
 * Created on 2009-5-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.framework;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.collections.map.ListOrderedMap;

/**
 * @author sunrui
 *
 */
public class ListMapGetter implements Serializable {

	public ListOrderedMap map = null;
	/**
	 * 
	 */
	public ListMapGetter(ListOrderedMap map) {
		this.map=map;
	}
	
	public int getInt(Object key) throws Exception {
		Object obj = map.get(key);
		if(obj!=null && obj instanceof Integer) {
			return ((Integer)obj).intValue();
		}
		return 0;
	}
	
	public BigDecimal getBigDecimal(Object key) throws Exception {
		BigDecimal val = new BigDecimal(0.0d);
		Object obj = map.get(key);
		if(obj!=null && obj instanceof BigDecimal) {
			val = (BigDecimal)obj;
		}
		return val;
	}
	
	public String getString(Object key) throws Exception {
		Object obj = map.get(key);
		if(obj!=null && obj instanceof String) {
			return (String)obj;
		}
		
		return null;
	}

}
