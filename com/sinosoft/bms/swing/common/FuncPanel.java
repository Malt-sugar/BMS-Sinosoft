/**
 *
 * Created on 2009-4-20
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * @author sunrui
 *
 */
public abstract class FuncPanel extends JPanel {

	protected MainApplet mainApplet = null;
	
	/**
	 * 
	 */
	public FuncPanel(MainApplet mainApplet) {
		this.mainApplet=mainApplet;
		initialize();
	}
		

	/**
	 * @param isDoubleBuffered
	 */
	public FuncPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initialize();
	}

	/**
	 * @param layout
	 */
	public FuncPanel(LayoutManager layout) {
		super(layout);
		initialize();
	}

	/**
	 * @param layout
	 * @param isDoubleBuffered
	 */
	public FuncPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		initialize();
	}
	
	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
			
	}

	public void setButtons(TitleButton [] buttons) throws Exception {
		getMainApplet().setButtons(buttons);
	}
	
	public abstract void onButtonClick(TitleButton button);


	/**
	 * @return the mainApplet
	 */
	public MainApplet getMainApplet() {
		return mainApplet;
	}


	/**
	 * @param mainApplet the mainApplet to set
	 */
	public void setMainApplet(MainApplet mainApplet) {
		this.mainApplet = mainApplet;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
