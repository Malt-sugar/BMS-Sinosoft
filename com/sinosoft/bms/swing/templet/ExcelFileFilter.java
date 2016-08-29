/**
 *
 * Created on 2009-4-23
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.templet;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * @author sunrui
 *
 */
public class ExcelFileFilter extends FileFilter {

	/**
	 * 
	 */
	public ExcelFileFilter() {
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File f) {
		if(f.isDirectory()) return true;
		String fileName = f.getName();
		fileName = fileName.toLowerCase();
		return fileName.endsWith(".xls");
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	public String getDescription() {
		return "Excel Files(.xls)";
	}

}
