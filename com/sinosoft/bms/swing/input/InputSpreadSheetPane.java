/**
 *
 * Created on 2009-5-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.input;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPopupMenu;

import com.sinosoft.bms.swing.templet.DesignSpreadSheetPane;
import com.sinosoft.bmscell.report.SpreadSheet;
import com.sinosoft.bmscell.report.gui.Grid;

/**
 * @author sunrui
 *
 */
public class InputSpreadSheetPane extends DesignSpreadSheetPane {

	/**
	 * 
	 */
	public InputSpreadSheetPane() {
	}

	/**
	 * @param spreadsheet
	 */
	public InputSpreadSheetPane(SpreadSheet spreadsheet) {
		super(spreadsheet);
	}

	/**
	 * @param spreadsheet
	 * @param grid
	 */
	public InputSpreadSheetPane(SpreadSheet spreadsheet, Grid grid) {
		super(spreadsheet, grid);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.templet.DesignSpreadSheetPane#createPopupMenu()
	 */
	public JPopupMenu createPopupMenu() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.templet.DesignSpreadSheetPane#createColumnPopupMenu(int, java.awt.Point)
	 */
	public JPopupMenu createColumnPopupMenu(int columnIndex, Point point) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.templet.DesignSpreadSheetPane#createRowPopupMenu(int, java.awt.Point)
	 */
	public JPopupMenu createRowPopupMenu(int rowIndex, Point point) {
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		//long t_start = System.currentTimeMillis();
		super.paint(g);
		//long t_end = System.currentTimeMillis();
		//System.out.println("ªÊ÷∆ ±º‰"+(t_end-t_start));
	}

}
