/**
 *
 * Created on 2009-4-7
 * @author sunrui
 *
 */
package com.sinosoft.bms.framework;


import java.awt.Color;
import java.awt.Font;

import org.apache.log4j.Logger;
import org.jfree.chart.*;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author sunrui
 *
 */
public class BmsUtils {

	protected static Logger logger = Logger.getLogger(BmsUtils.class);
	
	/**
	 * 
	 */
	public BmsUtils() {
	}
	
	public static int getID(final String tableName,final int count) throws Exception {
		int id = 0;
		final DAO dao = (DAO)BeanFactory.getBean("dao");
		
		
		DataSourceTransactionManager dstm = new DataSourceTransactionManager(dao.getDataSource());
		TransactionTemplate tt = new TransactionTemplate(dstm);
		
		
		Object ret = tt.execute(new TransactionCallback(){

			public Object doInTransaction(TransactionStatus status) {
				//TODO 代码需要修改
				JdbcTemplate jdbc = new JdbcTemplate(dao.getDataSource());
				int currID = 1;
				try {
					currID = jdbc.queryForInt("select CurrID from BMS_ID where TableName=?",new Object[]{tableName});
					jdbc.update("update BMS_ID set CurrID=? where TableName=?",new Object[]{new Integer(currID+count),tableName});
					logger.debug("获得主键["+tableName+"]:"+currID);
				} catch (EmptyResultDataAccessException e) {
					logger.debug("未找到主键表记录，生成新记录");
					jdbc.update("insert into BMS_ID(TableName,CurrID) values (?,?)",new Object[]{tableName,new Integer(count+1)});
				}
				
				return new Integer(currID);
			}
			
		});
		
		if(ret!=null && ret instanceof Integer) {
			id = ((Integer) ret).intValue();
		} else {
			throw new Exception ("生成主键失败");
		}
		return id;
	}
	
	public static int getID(final String tableName) throws Exception {
		return getID(tableName, 1);
	}
	
	public static int getID() throws Exception {
		return getID(1);
	}
	
	public static int getID(int count) throws Exception {
		return getID("_bms_global", count);
	}
	
	public static String BoolCharToChn(Character c) throws Exception {
		return (c==null||c.charValue()=='0')?"否":"是";
	}
	
	public static Character StringToBoolchar(String str) throws Exception {
		return (str==null||str.trim().length()!=1||(!(str.trim().equals("1"))))?new Character('0'):new Character('1');
	}
	
	public static Character boolToChar(boolean b) throws Exception {
		return b?new Character('1'):new Character('0');
	}
	
	public static boolean charToBool(Character c) throws Exception {
		if(c==null) return false;
		char chr = c.charValue();
		if(chr=='1') return true;
		return false;
	}
	
	public static String getCommonUpdateForm() throws Exception {
		String str = "";
		
		str+="<div id=\"commonUpdateDiv\" style=\"display: none ;\">                                                           \n";  
		str+="	<form id=\"commonUpdateForm\" method=\"post\" action=\"../common/CommonUpdate.do\" target=\"dataFrame\">  \n";
		str+="		<input id=\"updateName\" name=\"updateName\">                                                           \n";
		str+="		<input id=\"afterMethod\" name=\"afterMethod\">                                                         \n";
		str+="		<input id=\"param0\" name=\"param0\">                                                                   \n";
		str+="		<input id=\"param1\" name=\"param1\">                                                                   \n";
		str+="		<input id=\"param2\" name=\"param2\">                                                                   \n";
		str+="		<input id=\"param3\" name=\"param3\">                                                                   \n";
		str+="		<input id=\"param4\" name=\"param4\">                                                                   \n";
		str+="	</form>                                                                                                   \n";
		str+="</div>                                                                                                      \n";
		
		return str;
	}
	
	public static JFreeChart getAlertChart() throws Exception {
		
		JFreeChart chart=null;
		try {
			DefaultCategoryDataset ds = new DefaultCategoryDataset();
			ds.addValue(100.0d, "", "印刷费/单证印刷费");
			ds.addValue(50.0d, "", "印刷费/展业费");
			ds.addValue(30.0d, "", "印刷费/业务拓展费");
			ds.addValue(60.0d, "", "理赔查勘费/不分明细");
			ds.addValue(20.0d, "", "邮电费/续期邮费");
			ds.addValue(20.0d, "", "邮电费/展业费");
			ds.addValue(20.0d, "", "邮电费/业务拓展费");
			ds.addValue(20.0d, "", "差旅费/展业费");
			
			
			chart = ChartFactory.createBarChart(null, null, null, ds, PlotOrientation.VERTICAL, false, false, false);
			
			CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
			categoryplot.setBackgroundPaint(new Color(238, 238, 255));
			categoryplot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
			
		  	CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		  	categoryaxis.setLabelFont(new Font("宋体",Font.PLAIN,3));
		    categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
			
			CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
			categoryitemrenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
			//categoryitemrenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
			
			
			
		} catch (Throwable ex) {
			ex.printStackTrace();
		} 
		
		return chart;
	}
	
	public static String getChnSpace(int n) {
		String str = "";
		for (int i = 0; i < n; i++) {
			str+="　";
		}
		return str;
	}

}
