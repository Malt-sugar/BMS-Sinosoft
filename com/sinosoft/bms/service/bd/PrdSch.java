/**
 *
 * Created on 2009-4-13
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.bd;

import com.sinosoft.bms.entity.BmsPrdSch;

/**
 * @author sunrui
 *
 */
public interface PrdSch {
	BmsPrdSch [] queryAll() throws Exception;
	void insert(BmsPrdSch bmsPrdSch) throws Exception;
	BmsPrdSch queryById(int prdSchId) throws Exception;
	void update(BmsPrdSch bmsPrdSch) throws Exception;
	
}
