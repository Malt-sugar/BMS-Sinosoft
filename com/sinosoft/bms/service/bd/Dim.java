/**
 *
 * Created on 2009-4-17
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.bd;

import com.sinosoft.bms.entity.BmsDim;
import com.sinosoft.bms.entity.BmsDimMember;

/**
 * @author sunrui
 *
 */
public interface Dim {
	BmsDim [] queryDim(String whereSQL) throws Exception;
	BmsDim queryDimByID(int dimID) throws Exception;
	BmsDimMember [] queryDimMem(String whereSQL) throws Exception;
	BmsDimMember queryMemberByID(int dimID) throws Exception;
	void insertDim(BmsDim vo) throws Exception;
	void updateDim(BmsDim vo) throws Exception;
	void insertDimMember(BmsDimMember vo) throws Exception;
	void updateDimMember(BmsDimMember vo) throws Exception;
	
}
