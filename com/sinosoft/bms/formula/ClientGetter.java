/**
 *
 * Created on 2009-5-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.formula;

import java.io.Serializable;
import java.util.HashMap;

import com.sinosoft.bms.swing.templet.DesignSpreadSheetPane;
import com.sinosoft.bms.valueobject.BmsDouble;
import com.sinosoft.bms.valueobject.TempleteVO;
import com.sinosoft.bmscell.report.CellElement;

/**
 * @author sunrui
 *
 */
public class ClientGetter implements Serializable {

	public TempleteVO tvo = null;
	public DesignSpreadSheetPane sheetPanel = null;
	public HashMap hmCol = null;
	public int row = 0;
	
	/**
	 * 
	 */
	public ClientGetter() {
	}
	
		
	public double col(String colCode) throws Exception {
		double result = 0.0d;
		Object objCol = hmCol.get(colCode);
		if(objCol!=null) {
			int col = ((Integer)objCol).intValue();
			
			CellElement ce = sheetPanel.getReport().getCellElement(col, row);
			Object obj = ce.getValue();
			
			if(obj!=null) {
				try {
					BmsDouble val = new BmsDouble(obj.toString());
					return val.doubleValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		return result;
	}

}
