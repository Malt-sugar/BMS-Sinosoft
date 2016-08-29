/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * @author sunrui
 *
 */
public class BmsButton extends JButton {

	public boolean bMouseInButton = false;
	public boolean bButtonPressed = false;
	/**
	 * 
	 */
	public BmsButton() {
		super();
		initEvent();
	}

	/**
	 * @param text
	 */
	public BmsButton(String text) {
		super(text);
		initEvent();
		if(text!=null) {
			int textWidth = 14*text.length();
			setPreferredSize(new Dimension(textWidth+30,25));
		}
	}

	/**
	 * @param icon
	 */
	public BmsButton(Icon icon) {
		super(icon);
		initEvent();
	}

	/**
	 * @param text
	 * @param icon
	 */
	public BmsButton(String text, Icon icon) {
		super(text, icon);
		initEvent();
	}
	
	public void initEvent() {
		addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				bMouseInButton=true;
			}

			public void mouseExited(MouseEvent e) {
				bMouseInButton=false;
			}

			public void mousePressed(MouseEvent e) {
				bButtonPressed=true;
			}

			public void mouseReleased(MouseEvent e) {
				bButtonPressed=false;
			}});
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
//	public void paint(Graphics g) {
//		//super.paint(g);
//		Graphics2D g2d = (Graphics2D) g;
//		//g2d.setBackground(new Color(255,255,255));
//		g2d.setBackground(new Color(236,233,216));
//		g2d.clearRect(0, 0, getWidth(), getHeight());
//		String text = getText();
//		if(text!=null) {
//			int textWidth = g2d.getFontMetrics().stringWidth(text);
//			if(isEnabled()) {
//				g2d.setColor(new Color(0,0,0));
//			} else {
//				g2d.setColor(new Color(150,150,150));
//			}
//			g2d.drawString(text, (getWidth()-textWidth)/2, (getHeight()-12)/2+12-2);
//		}
//		
//		if(!bButtonPressed) {
//			g2d.setColor(new Color(255,255,255));
//			g2d.drawLine(0, 0, getWidth()-1, 0);
//			g2d.drawLine(0, 0, 0, getHeight()-1);
//			
//			g2d.setColor(new Color(153,153,153));
//			g2d.drawLine(0, getHeight()-1, getWidth()-1, getHeight()-1);
//			g2d.drawLine(getWidth()-1, 0, getWidth()-1, getHeight()-1);
//		}
//		
//		if(bButtonPressed) {
//			g2d.setColor(new Color(153,153,153));
//			
//			g2d.drawLine(0, 0, getWidth(), 0);
//			g2d.drawLine(0, 0, 0, getHeight());
//			
//			g2d.setColor(new Color(255,255,255));
//			g2d.drawLine(0, getHeight()-1, getWidth()-1, getHeight()-1);
//			g2d.drawLine(getWidth()-1, 0, getWidth()-1, getHeight()-1);
//		}
//		
//	}

}
