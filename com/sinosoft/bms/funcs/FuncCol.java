/**
 *
 * Created on 2009-5-22
 * @author sunrui
 *
 */
package com.sinosoft.bms.funcs;

import java.util.HashMap;
import java.util.Stack;

import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import com.sinosoft.bms.swing.templet.DesignSpreadSheetPane;
import com.sinosoft.bms.valueobject.BmsDouble;
import com.sinosoft.bms.valueobject.TempleteVO;
import com.sinosoft.bmscell.report.CellElement;

/**
 * @author sunrui
 *
 */
public class FuncCol extends PostfixMathCommand {

	public TempleteVO tvo = null;
	public DesignSpreadSheetPane sheetPanel = null;
	public HashMap hmCol = null;
	public int row = 0;

	/**
	 * 
	 */
	public FuncCol() {
		numberOfParameters=1;
	}

	/* (non-Javadoc)
	 * @see org.nfunk.jep.function.PostfixMathCommand#run(java.util.Stack)
	 */
	public void run(Stack st) throws ParseException {
		checkStack(st);
		
		String colCode = (String)st.pop();
		
		double result = 0.0d;
		Object objCol = hmCol.get(colCode);
		if(objCol!=null) {
			int col = ((Integer)objCol).intValue();
			
			CellElement ce = sheetPanel.getReport().getCellElement(col, row);
			Object obj = ce.getValue();
			
			if(obj!=null) {
				try {
					BmsDouble val = new BmsDouble(obj.toString());
					result = val.doubleValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		st.push(new Double(result));
		
	}

	/**
	 * @return the hmCol
	 */
	public HashMap getHmCol() {
		return hmCol;
	}

	/**
	 * @param hmCol the hmCol to set
	 */
	public void setHmCol(HashMap hmCol) {
		this.hmCol = hmCol;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @return the sheetPanel
	 */
	public DesignSpreadSheetPane getSheetPanel() {
		return sheetPanel;
	}

	/**
	 * @param sheetPanel the sheetPanel to set
	 */
	public void setSheetPanel(DesignSpreadSheetPane sheetPanel) {
		this.sheetPanel = sheetPanel;
	}

	/**
	 * @return the tvo
	 */
	public TempleteVO getTvo() {
		return tvo;
	}

	/**
	 * @param tvo the tvo to set
	 */
	public void setTvo(TempleteVO tvo) {
		this.tvo = tvo;
	}
}
