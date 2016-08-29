/**
 *
 * Created on 2009-5-6
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * @author sunrui
 *
 */
public class ColCodeCellEditor extends DefaultCellEditor {

	/**
	 * @param checkBox
	 */
	public ColCodeCellEditor(JCheckBox checkBox) {
		super(checkBox);
	}

	/**
	 * @param comboBox
	 */
	public ColCodeCellEditor(JComboBox comboBox) {
		super(comboBox);
	}

	/**
	 * @param textField
	 */
	public ColCodeCellEditor(JTextField textField) {
		super(textField);
	}

}
