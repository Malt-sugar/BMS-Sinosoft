/**
 *
 * Created on 2009-5-22
 * @author sunrui
 *
 */
package com.sinosoft.bms.funcs;

import java.util.List;
import java.util.Stack;

import org.apache.commons.collections.map.ListOrderedMap;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import com.sinosoft.bms.framework.DAO;
import com.sinosoft.bms.valueobject.BmsDouble;

/**
 * @author sunrui
 *
 */
public class FuncGetActVal extends PostfixMathCommand {

	public int bgObjID = 0;
	public int dimMemID = 0;
	public int itemID = 0;
	public DAO dao = null;
	
	/**
	 * 
	 */
	public FuncGetActVal() {
		numberOfParameters=1;
	}

	/* (non-Javadoc)
	 * @see org.nfunk.jep.function.PostfixMathCommand#run(java.util.Stack)
	 */
	public void run(Stack st) throws ParseException {
		checkStack(st);
		
		String period = (String)st.pop();
		
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
		st.push(new Double(result));
	}

	/**
	 * @return the bgObjID
	 */
	public int getBgObjID() {
		return bgObjID;
	}

	/**
	 * @param bgObjID the bgObjID to set
	 */
	public void setBgObjID(int bgObjID) {
		this.bgObjID = bgObjID;
	}

	/**
	 * @return the dao
	 */
	public DAO getDao() {
		return dao;
	}

	/**
	 * @param dao the dao to set
	 */
	public void setDao(DAO dao) {
		this.dao = dao;
	}

	/**
	 * @return the dimMemID
	 */
	public int getDimMemID() {
		return dimMemID;
	}

	/**
	 * @param dimMemID the dimMemID to set
	 */
	public void setDimMemID(int dimMemID) {
		this.dimMemID = dimMemID;
	}

	/**
	 * @return the itemID
	 */
	public int getItemID() {
		return itemID;
	}

	/**
	 * @param itemID the itemID to set
	 */
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

}
