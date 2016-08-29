/**
 *
 * Created on 2009-4-10
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.bd;

import com.sinosoft.bms.entity.BmsMenu;

/**
 * @author sunrui
 *
 */
public interface Menu {
	BmsMenu [] queryMenu() throws Exception;
}
