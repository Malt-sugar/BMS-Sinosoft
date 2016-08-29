/**
 *
 * Created on 2009-4-22
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.budgets;

import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.bms.entity.BmsSheetItem;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.entity.BmsTpBgObj;
import com.sinosoft.bms.entity.BmsTpColDim;
import com.sinosoft.bms.entity.BmsTpParamDim;
import com.sinosoft.bms.entity.BmsTpRowDim;
import com.sinosoft.bms.valueobject.CellVO;
import com.sinosoft.bms.valueobject.SheetQueryVO;
import com.sinosoft.bms.valueobject.SheetVO;
import com.sinosoft.bms.valueobject.TempleteVO;

/**
 * @author sunrui
 *
 */
public interface Templete {
	BmsTemplet [] queryAll() throws Exception;
	BmsTemplet queryTemplete(int tpid) throws Exception;
	
	Integer insert(BmsTemplet tp) throws Exception;
	void update(BmsTemplet tp) throws Exception;
	void delete(BmsTemplet tp) throws Exception;
	void saveFile(String fileName,byte [] fileData) throws Exception;
	byte [] loadFile(String fileName) throws Exception;
	void saveTpBgObj(int tpID, BmsTpBgObj [] tpobjs) throws Exception;
	void saveTpParamDim(int tpID,BmsTpParamDim [] tpParams) throws Exception;
	void saveTpRowDim(int tpID,BmsTpRowDim [] tpRows) throws Exception;
	void saveTpColDim(int tpID,BmsTpColDim [] tpCols) throws Exception;
	BmsTpBgObj [] queryBmsTpBgObj(int tpID) throws Exception;
	BmsTpParamDim [] queryBmsTpParamDim(int tpID) throws Exception;
	BmsTpRowDim [] queryBmsRowDim(int tpID) throws Exception;
	BmsTpColDim [] queryBmsColDim(int tpID) throws Exception;
	
	SheetVO [] querySheet(SheetQueryVO queryVO) throws Exception;
	Integer insertSheet(BmsSheet sheet) throws Exception;
	void updateSheet(BmsSheet sheet) throws Exception;
	void deleteSheet(int sheetID) throws Exception;
	void saveSheetItems(int sheetid,BmsSheetItem [] items) throws Exception;
	void saveGatherSheetItems(int sheetid,BmsSheet [] children) throws Exception;
	
	BmsSheet [] queryBmsSheet(String whereSql) throws Exception;
	
	CellVO [] queryCellVO(TempleteVO tvo,int bgObjID,int dimMemberID) throws Exception;
	
	BmsSheetItem [] queryBmsSheetItem(int sheetid) throws Exception;
	
}
