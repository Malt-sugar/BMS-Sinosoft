/**
 *
 * Created on 2009-4-30
 * @author sunrui
 *
 */
package com.sinosoft.bms.swing.common;

/**
 * @author sunrui
 *
 */
public class BmsAppletClassLoader extends ClassLoader {

	/**
	 * 
	 */
	public BmsAppletClassLoader() {
	}

	/**
	 * @param parent
	 */
	public BmsAppletClassLoader(ClassLoader parent) {
		super(parent);
	}

	/* (non-Javadoc)
	 * @see java.lang.ClassLoader#findClass(java.lang.String)
	 */
	protected Class findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}

}
