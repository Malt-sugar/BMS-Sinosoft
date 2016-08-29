/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import com.sinosoft.bms.clientcommon.ClientBeanFactory;
import com.sinosoft.bms.service.main.CommonService;

/**
 * @author sunrui
 *
 */
public class DefaultRefModel implements RefModel {

	public String [] fields = null;
	public String [] titles = null;
	public String tableName = null;
	public String whereSQL = null;
	public String sql = null;
	
	/**
	 * 
	 */
	public DefaultRefModel() {
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.RefModel#getRefData()
	 */
	public Object[][] getRefData() throws Exception {
		String sql = this.sql;
		if(sql==null) {
			sql = "select "+fields[0]+","+fields[1]+","+fields[2]+" from "+tableName;
			if(whereSQL!=null) {
				sql+=" where "+whereSQL;
			}
			sql+=" order by "+fields[1];
		}
		CommonService csbean = (CommonService) ClientBeanFactory.getBean("CommonService");
		Object [][] data = csbean.queryData(sql);
		return data;
	}

	/**
	 * @return the fields
	 */
	public String[] getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(String[] fields) {
		this.fields = fields;
	}

	/**
	 * @return the sql
	 */
	public String getSql() {
		return sql;
	}

	/**
	 * @param sql the sql to set
	 */
	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableName the tableName to set
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the titles
	 */
	public String[] getTitles() {
		return titles;
	}

	/**
	 * @param titles the titles to set
	 */
	public void setTitles(String[] titles) {
		this.titles = titles;
	}

	/**
	 * @return the whereSQL
	 */
	public String getWhereSQL() {
		return whereSQL;
	}

	/**
	 * @param whereSQL the whereSQL to set
	 */
	public void setWhereSQL(String whereSQL) {
		this.whereSQL = whereSQL;
	}
	

}
