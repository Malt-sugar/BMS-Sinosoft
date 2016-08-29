/**
 *
 * Created on 2009-5-6
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTreeUI;

/**
 * @author sunrui
 *
 */
public class BmsTreeUI extends BasicTreeUI {

	public static BasicStroke dotted = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[]{1.0f}, 0.0f);
	/**
	 * 
	 */
	public BmsTreeUI() {
	}

	
    public static ComponentUI createUI(JComponent x) {
    	return new BmsTreeUI();
    }

	/* (non-Javadoc)
	 * @see javax.swing.plaf.basic.BasicTreeUI#paintHorizontalLine(java.awt.Graphics, javax.swing.JComponent, int, int, int)
	 */
	protected void paintHorizontalLine(Graphics g, JComponent c, int y, int left, int right) {
		Graphics2D g2d = (Graphics2D)g;
		Stroke oldStroke = g2d.getStroke();
		g2d.setStroke(dotted);
		super.paintHorizontalLine(g, c, y, left, right);
		g2d.setStroke(oldStroke);
	}



	/* (non-Javadoc)
	 * @see javax.swing.plaf.basic.BasicTreeUI#paintVerticalLine(java.awt.Graphics, javax.swing.JComponent, int, int, int)
	 */
	protected void paintVerticalLine(Graphics g, JComponent c, int x, int top, int bottom) {
		Graphics2D g2d = (Graphics2D)g;
		Stroke oldStroke = g2d.getStroke();
		g2d.setStroke(dotted);
		super.paintVerticalLine(g, c, x, top, bottom);
		g2d.setStroke(oldStroke);
	}

}
