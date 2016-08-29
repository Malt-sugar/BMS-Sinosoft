/**
 *
 * Created on 2009-4-22
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Rectangle;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * @author sunrui
 *
 */
public class BmsDialog extends JDialog {

	public static final int ID_CANCEL = 0;
	public static final int ID_OK = 1;
	
	public int result = 0;
	/**
	 * @throws HeadlessException
	 */
	public BmsDialog() throws HeadlessException {
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public BmsDialog(Dialog owner) throws HeadlessException {
		super(owner);
	}

	/**
	 * @param owner
	 * @throws HeadlessException
	 */
	public BmsDialog(Frame owner) throws HeadlessException {
		super(owner);
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws HeadlessException
	 */
	public BmsDialog(Dialog owner, boolean modal) throws HeadlessException {
		super(owner, modal);
	}

	/**
	 * @param owner
	 * @param modal
	 * @throws HeadlessException
	 */
	public BmsDialog(Frame owner, boolean modal) throws HeadlessException {
		super(owner, modal);
	}

	/**
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public BmsDialog(Dialog owner, String title) throws HeadlessException {
		super(owner, title);
	}

	/**
	 * @param owner
	 * @param title
	 * @throws HeadlessException
	 */
	public BmsDialog(Frame owner, String title) throws HeadlessException {
		super(owner, title);
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @throws HeadlessException
	 */
	public BmsDialog(Dialog owner, String title, boolean modal)
			throws HeadlessException {
		super(owner, title, modal);
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @throws HeadlessException
	 */
	public BmsDialog(Frame owner, String title, boolean modal)
			throws HeadlessException {
		super(owner, title, modal);
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @param gc
	 * @throws HeadlessException
	 */
	public BmsDialog(Dialog owner, String title, boolean modal,
			GraphicsConfiguration gc) throws HeadlessException {
		super(owner, title, modal, gc);
	}

	/**
	 * @param owner
	 * @param title
	 * @param modal
	 * @param gc
	 */
	public BmsDialog(Frame owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
	}
	
	public BmsDialog(Component owner) {
		this(owner,true);
	}
	
	public BmsDialog(Component owner,boolean modal) {
		super(JOptionPane.getFrameForComponent(owner),modal);
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	
	public void closeOK() {
		result = ID_OK;
		setVisible(false);
	}
	
	public void closeCancel() {
		result = ID_CANCEL;
		setVisible(false);
	}
	
	public int showModal() {
		result = ID_CANCEL;
		setModal(true);
		setLocationRelativeTo(ClientEnv.getInstance().getMainApplet());
		setVisible(true);
		return getResult();
	}

}
