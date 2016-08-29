/**
 *
 * Created on 2009-4-21
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.Color;
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
public class RefButton extends JButton {
	public boolean bMouseInButton = false;
	public boolean bButtonPressed = false;
	/**
	 * 
	 */
	public RefButton() {
		initEvent();
	}

	/**
	 * @param icon
	 */
	public RefButton(Icon icon) {
		super(icon);
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
	public void paint(Graphics g) {
		//super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		//g2d.setBackground(new Color(255,255,255));
		//g2d.setBackground(new Color(236,233,216));
		g2d.setBackground(getBackground());
		g2d.clearRect(0, 0, getWidth(), getHeight());
		String text = getText();
		if(text!=null) {
			int textWidth = g2d.getFontMetrics().stringWidth(text);
			if(isEnabled()) {
				g2d.setColor(new Color(0,0,0));
			} else {
				g2d.setColor(new Color(150,150,150));
			}
			g2d.drawString(text, (getWidth()-textWidth)/2, 16);
		}
		
		if(bMouseInButton&&(!bButtonPressed)) {
			g2d.setColor(new Color(255,255,255));
			g2d.drawLine(0, 0, getWidth()-1, 0);
			g2d.drawLine(0, 0, 0, getHeight()-1);
			
			g2d.setColor(new Color(153,153,153));
			g2d.drawLine(0, getHeight()-1, getWidth()-1, getHeight()-1);
			g2d.drawLine(getWidth()-1, 0, getWidth()-1, getHeight()-1);
		}
		
		if(bButtonPressed) {
			g2d.setColor(new Color(153,153,153));
			
			g2d.drawLine(0, 0, getWidth(), 0);
			g2d.drawLine(0, 0, 0, getHeight());
			
			g2d.setColor(new Color(255,255,255));
			g2d.drawLine(0, getHeight()-1, getWidth()-1, getHeight()-1);
			g2d.drawLine(getWidth()-1, 0, getWidth()-1, getHeight()-1);
		}
		
		
		if(getIcon()!=null) {
			int iw = getIcon().getIconWidth();
			int ih = getIcon().getIconHeight();
			
			getIcon().paintIcon(this, g2d, (getWidth()-iw)/2, (getHeight()-ih)/2);
		}
		
	}
}
