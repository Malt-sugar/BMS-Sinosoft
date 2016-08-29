/**
 *
 * Created on 2009-4-7
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.main;

import com.sinosoft.bms.entity.*;

/**
 * @author sunrui
 *
 */
public interface Login {
	BmsUser [] queryUser() throws Exception;
}
