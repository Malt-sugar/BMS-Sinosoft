/**
 *
 * Created on 2009-5-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.formula;

/**
 * @author sunrui
 *
 */
public class FormulaTools {

	/**
	 * 
	 */
	public FormulaTools() {
	}
	
	public static String getScript(String formula) throws Exception {
		String script = "";
		
		script+="com.sinosoft.bms.formula.DataGetter dg = new com.sinosoft.bms.formula.DataGetter(); \n";
		script+="dg.bgObjID = bgObjID;                                                               \n";
		script+="dg.dimMemID = dimMemID;                                                             \n";
		script+="dg.itemID = itemID;                                                                 \n";
		script+="dg.dao = dao;                                                                 \n";
		script+="double getActVal(String period) throws Exception {                                  \n";
		script+="	return dg.getActVal(period);                                                     \n";
		script+="}                                                                                   \n";
		script+="result = "+formula+";\n";
		
		
		return script;
	}
	
	public static String getClientScript(String formula) throws Exception {
		String script = "";
		
		script+="com.sinosoft.bms.formula.ClientGetter cg = new com.sinosoft.bms.formula.ClientGetter(); \n";
		script+="cg.tvo = tvo;                                                               \n";
		script+="cg.sheetPanel = sheetPanel;                                                               \n";
		script+="cg.hmCol = hmCol;                                                               \n";
		script+="cg.row = row;                                                               \n";
		script+="double col(String colCode) throws Exception {                                  \n";
		script+="	return cg.col(colCode);                                                     \n";
		script+="}                                                                                   \n";
		script+="result = "+formula+";\n";
		
		
		return script;
	}

}
