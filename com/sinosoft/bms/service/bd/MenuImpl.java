/**
 *
 * Created on 2009-4-10
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsMenu;
import com.sinosoft.bms.framework.DAO;

/**
 * @author sunrui
 *
 */
public class MenuImpl implements Menu {

	protected DAO dao;
	/**
	 * 
	 */
	public MenuImpl(DAO dao) {
		this.dao=dao;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.Menu#queryMenu()
	 */
	public BmsMenu[] queryMenu() throws Exception {
		List menuList = dao.query("from BmsMenu where menuType in ('0','1') order by menuCode ");
		BmsMenu [] menus = new BmsMenu[menuList.size()];
		menuList.toArray(menus);
		return menus;
	}

}
