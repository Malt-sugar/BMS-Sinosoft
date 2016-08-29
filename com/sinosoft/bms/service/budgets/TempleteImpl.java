/**
 *
 * Created on 2009-4-22
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.budgets;

import java.io.RandomAccessFile;
import java.util.List;
import java.util.Vector;

import org.apache.commons.collections.map.ListOrderedMap;
import org.nfunk.jep.JEP;

import bsh.Interpreter;

import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.bms.entity.BmsSheetItem;
import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.entity.BmsTpBgObj;
import com.sinosoft.bms.entity.BmsTpColDim;
import com.sinosoft.bms.entity.BmsTpParamDim;
import com.sinosoft.bms.entity.BmsTpRowDim;
import com.sinosoft.bms.formula.FormulaTools;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;
import com.sinosoft.bms.framework.ListMapGetter;
import com.sinosoft.bms.funcs.FuncGetActVal;
import com.sinosoft.bms.valueobject.BmsDouble;
import com.sinosoft.bms.valueobject.CellVO;
import com.sinosoft.bms.valueobject.ColTypeVO;
import com.sinosoft.bms.valueobject.SheetQueryVO;
import com.sinosoft.bms.valueobject.SheetVO;
import com.sinosoft.bms.valueobject.TempleteVO;

/**
 * @author sunrui
 *
 */
public class TempleteImpl implements Templete {

	protected DAO dao = null;
	
	public static String bmsDataPath = "D:\\bmswas\\bmsdata\\";
	/**
	 * 
	 */
	public TempleteImpl(DAO dao) {
		this.dao=dao;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#insert(com.sinosoft.bms.entity.BmsTemplet)
	 */
	public Integer insert(BmsTemplet tp) throws Exception {
		
		List list = dao.query("from BmsTemplet where tpCode='"+tp.getTpCode()+"'");
		if(list.size()>0) throw new Exception("样表编码已经存在");
		if(tp.getTpCode()==null||tp.getTpCode().trim().length()==0) {
			throw new Exception("样表编码不能为空");
		}
		
		int id = BmsUtils.getID();
		tp.setTpId(id);
		dao.insert(tp);
		return new Integer(id);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#queryAll()
	 */
	public BmsTemplet[] queryAll() throws Exception {
		List list = dao.query("from BmsTemplet order by tpCode");
		BmsTemplet [] tps = new BmsTemplet[list.size()];
		list.toArray(tps);
		return tps;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#update(com.sinosoft.bms.entity.BmsTemplet)
	 */
	public void update(BmsTemplet tp) throws Exception {
		List list = dao.query("from BmsTemplet where tpCode='"+tp.getTpCode()+"' and tpID<>"+tp.getTpId());
		if(list.size()>0) throw new Exception("样表编码已经存在");
		if(tp.getTpCode()==null||tp.getTpCode().trim().length()==0) {
			throw new Exception("样表编码不能为空");
		}
		
		dao.update(tp);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#delete(com.sinosoft.bms.entity.BmsTemplet)
	 */
	public void delete(BmsTemplet tp) throws Exception {
		dao.delete(tp);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#loadFile(java.lang.String)
	 */
	public byte[] loadFile(String fileName) throws Exception {
		RandomAccessFile f = new RandomAccessFile(bmsDataPath+fileName,"r");
		byte [] fileData = new byte[(int)f.length()];
		f.readFully(fileData);
		f.close();
		return fileData;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#saveFile(java.lang.String, byte[])
	 */
	public void saveFile(String fileName, byte[] fileData) throws Exception {
		RandomAccessFile f = new RandomAccessFile(bmsDataPath+fileName,"rw");
		f.setLength(0);
		f.write(fileData);
		f.close();
	}
	
	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#saveTpBgObj(int, com.sinosoft.bms.entity.BmsTpBgObj[])
	 */
	public void saveTpBgObj(int tpID, BmsTpBgObj[] tpobjs) throws Exception {
		dao.excute("delete from BMS_TpBgObj where TpID="+tpID);
		if(tpobjs!=null) {
			for (int i = 0; i < tpobjs.length; i++) {
				tpobjs[i].setTpBgObjId(BmsUtils.getID());
				dao.insert(tpobjs[i]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#saveTpParamDim(int, com.sinosoft.bms.entity.BmsTpParamDim[])
	 */
	public void saveTpParamDim(int tpID, BmsTpParamDim[] tpParams) throws Exception {
		dao.excute("delete from BMS_TpParamDim where TpID="+tpID);
		if(tpParams!=null) {
			for (int i = 0; i < tpParams.length; i++) {
				tpParams[i].setTpParamDimId(BmsUtils.getID());
				dao.insert(tpParams[i]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#queryBmsTpBgObj(int)
	 */
	public BmsTpBgObj[] queryBmsTpBgObj(int tpID) throws Exception {
		List list = dao.query("from BmsTpBgObj where tpID="+tpID);
		BmsTpBgObj [] vos = new BmsTpBgObj[list.size()];
		list.toArray(vos);
		return vos;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#queryBmsTpParamDim(int)
	 */
	public BmsTpParamDim[] queryBmsTpParamDim(int tpID) throws Exception {
		List list = dao.query("from BmsTpParamDim where tpID="+tpID);
		BmsTpParamDim[] vos = new BmsTpParamDim[list.size()];
		list.toArray(vos);
		return vos;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#queryBmsRowDim(int)
	 */
	public BmsTpRowDim[] queryBmsRowDim(int tpID) throws Exception {
		List list = dao.query("from BmsTpRowDim where tpID="+tpID);
		BmsTpRowDim [] vos = new BmsTpRowDim[list.size()];
		list.toArray(vos);
		return vos;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#saveTpRowDim(int, com.sinosoft.bms.entity.BmsTpRowDim[])
	 */
	public void saveTpRowDim(int tpID, BmsTpRowDim[] tpRows) throws Exception {
		dao.excute("delete from BMS_TpRowDim where tpID="+tpID);
		if(tpRows!=null) {
			for (int i = 0; i < tpRows.length; i++) {
				tpRows[i].setTpRowDimId(BmsUtils.getID());
				dao.insert(tpRows[i]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#queryBmsColDim(int)
	 */
	public BmsTpColDim[] queryBmsColDim(int tpID) throws Exception {
		List list = dao.query("from BmsTpColDim where tpID="+tpID);
		BmsTpColDim [] vos = new BmsTpColDim[list.size()];
		list.toArray(vos);
		return vos;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#saveTpColDim(int, com.sinosoft.bms.entity.BmsTpColDim[])
	 */
	public void saveTpColDim(int tpID, BmsTpColDim[] tpCols) throws Exception {
		dao.excute("delete from BMS_TpColDim where tpID="+tpID);
		if(tpCols!=null) {
			for (int i = 0; i < tpCols.length; i++) {
				tpCols[i].setTpColDimId(BmsUtils.getID());
			}
			
			//设置父列的id号
			for (int i = 0; i < tpCols.length; i++) {
				
				if(tpCols[i].getParentCol()!=null) {
					int index = tpCols[i].getParentCol().intValue();
					if(index==-1) {
						tpCols[i].setParentCol(null);
					} else {
						tpCols[i].setParentCol(new Integer(tpCols[index].getTpColDimId()));
					}
				}
				dao.insert(tpCols[i]);
			}
			
			
		}
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#querySheet(com.sinosoft.bms.valueobject.SheetQueryVO)
	 */
	public SheetVO[] querySheet(SheetQueryVO queryVO) throws Exception {
		
		String sql = "";
		sql+="select tp.tpid,tp.tpcode,tp.tpname,obj.bgobjid,obj.bgobjcode,obj.bgobjname,               ";
		sql+="       pdim.tpparamdimid,dm.dimmemid,dm.dimmemcode,dm.dimmemname,                         ";
		sql+="       tp.tpcode+'_'+obj.bgobjcode+'_'+dm.dimmemcode sheetcode,                           ";
		sql+="       tp.tpname+'_'+obj.bgobjname+'_'+dm.dimmemname sheetname                            ";
		sql+="from bms_templet tp,bms_tpbgobj tpobj,bms_object obj,bms_tpparamdim pdim,bms_dimmember dm,";   
		sql+="     bms_userbgobj uo,bms_role r,bms_roleclass rc,bms_userrole ur                         ";   
		sql+="where tpobj.leafflag=1 and tp.tpid=tpobj.tpid and tp.disflag='1'                          ";   
		sql+="      and tpobj.bgobjid=obj.bgobjid                                                       ";   
		sql+="      and pdim.dimmemid=dm.dimmemid                                                       ";   
		sql+="      and obj.bgobjid=uo.bgobjid and uo.userid="+queryVO.getUserID()+"                            ";
		sql+="      and uo.userid=ur.userid and r.rcid=rc.rcid and ur.roleid=r.roleid                   ";
		sql+="      and tp.inputroleid=rc.rcid                                                          ";
		
		
		List listSheet = dao.queryWithJDBC(sql);
		
		
		SheetVO [] sheets = new SheetVO[listSheet.size()];
		for (int i = 0; i < sheets.length; i++) {
			ListOrderedMap map = (ListOrderedMap)listSheet.get(i);
			ListMapGetter g = new ListMapGetter(map);
			sheets[i] = new SheetVO();
			sheets[i].setTpID(g.getInt("tpid"));
			sheets[i].setTpCode(g.getString("tpcode"));;
			sheets[i].setTpName(g.getString("tpname"));
			sheets[i].setBgObjID(g.getInt("bgobjid"));
			sheets[i].setBgObjCode(g.getString("bgobjcode"));
			sheets[i].setBgObjName(g.getString("bgobjname"));
			sheets[i].setTpParamID(g.getInt("tpparamdimid"));
			sheets[i].setDimMemID(g.getInt("dimmemid"));
			sheets[i].setDimMemCode(g.getString("dimmemcode"));
			sheets[i].setDimMemName(g.getString("dimmemname"));
			sheets[i].setSheetCode(g.getString("sheetcode"));
			sheets[i].setSheetName(g.getString("sheetname"));
			
			List listBmsSheet = dao.query("from BmsSheet where sheetcode='"+sheets[i].getSheetCode()+"'");
			if(listBmsSheet.size()>0) {
				sheets[i].setBmsSheet((BmsSheet)listBmsSheet.get(0));
				List listBmsSheetItem = dao.query("from BmsSheetItem where sheetid="+sheets[i].getBmsSheet().getSheetId());
				BmsSheetItem [] sheetItems = new BmsSheetItem[listBmsSheetItem.size()];
				listBmsSheetItem.toArray(sheetItems);
				sheets[i].setBmsSheetItems(sheetItems);
			}
			
		}
		
		return sheets;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#deleteSheet(int)
	 */
	public void deleteSheet(int sheetID) throws Exception {
		dao.excute("delete from bms_sheetitem where sheetid="+sheetID);
		dao.excute("delete from bms_sheet where sheetid="+sheetID);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#updateSheet(com.sinosoft.bms.entity.BmsSheet)
	 */
	public void updateSheet(BmsSheet sheet) throws Exception {
		dao.update(sheet);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#insertSheet(com.sinosoft.bms.entity.BmsSheet)
	 */
	public Integer insertSheet(BmsSheet sheet) throws Exception {
		
		int sheetId = BmsUtils.getID();
		
		sheet.setSheetId(sheetId);
		dao.insert(sheet);
		
		return new Integer(sheetId);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#queryCellVO(int)
	 */
	public CellVO[] queryCellVO(TempleteVO tvo,int bgObjID,int dimMemberID) throws Exception {
		
		BmsTpColDim [] colDims = tvo.getColDims();
		BmsTpRowDim [] rowDims = tvo.getRowDims();
		BmsTemplet tp = tvo.getBmsTemplet();
		
		int startRow = tp.getDataStartRow().intValue();
		int startCol = tp.getDataStartCol().intValue();
		
		Vector v = new Vector();
		
		
		//Interpreter bsh = new Interpreter();
		
		FuncGetActVal funcGetActVal = new FuncGetActVal();
		
		JEP jep = new JEP();
		jep.addStandardConstants();
		jep.addStandardFunctions();
		jep.addFunction("getActVal", funcGetActVal);
		
		funcGetActVal.setDao(dao);
		
		for (int i = 0; i < rowDims.length; i++) {
			for (int j = 0; j < colDims.length; j++) {
				if(colDims[j].getColType()==null) continue;
				int coltype = colDims[j].getColType().intValue();
				if(!(coltype==ColTypeVO.CT_DATABASE||coltype==ColTypeVO.CT_ACTUAL)) continue;
				
				CellVO vo = new CellVO();
				vo.setCol(j+2+startCol);
				vo.setRow(i+1+startRow);
				vo.setColDimID(colDims[j].getTpColDimId());
				vo.setItemID(rowDims[i].getBmsItem().getItemId());
				//String script = FormulaTools.getScript(colDims[j].getFormula());
				
				
				
//				bsh.set("bgObjID", bgObjID);
//				bsh.set("dimMemID", dimMemberID);
//				bsh.set("itemID", rowDims[i].getBmsItem().getItemId());
//				bsh.set("dao", dao);
//				
//				bsh.eval(script);
//				
//				double result = ((Double)bsh.get("result")).doubleValue();	
				
				funcGetActVal.setBgObjID(bgObjID);
				funcGetActVal.setDimMemID(dimMemberID);
				funcGetActVal.setItemID(rowDims[i].getBmsItem().getItemId());
				
				jep.parseExpression(colDims[j].getFormula());
				if(jep.hasError()) {
					throw new Exception("公式解析发生错误："+jep.getErrorInfo());
				}
				
				double result = jep.getValue();
				
				vo.setValue(new BmsDouble(result));
				v.add(vo);
			}
		}
		CellVO [] vos = new CellVO[v.size()];
		v.copyInto(vos);
		
		return vos;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#queryTemplete(int)
	 */
	public BmsTemplet queryTemplete(int tpid) throws Exception {
		List list = dao.query("from BmsTemplet where tpid="+tpid);
		if(list.size()>0) {
			return (BmsTemplet)list.get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#saveSheetItems(com.sinosoft.bms.entity.BmsSheetItem[])
	 */
	public void saveSheetItems(int sheetid,BmsSheetItem[] items) throws Exception {
		dao.excute("delete from bms_sheetitem where sheetid="+sheetid);
		
		if(items!=null && items.length>0) {
			int id = BmsUtils.getID(items.length);
			for (int i = 0; i < items.length; i++) {
				items[i].setSheetItemId(id+i);
				dao.insert(items[i]);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#queryBmsSheet(java.lang.String)
	 */
	public BmsSheet[] queryBmsSheet(String whereSql) throws Exception {
		
		String hsql = "from BmsSheet ";
		if(whereSql!=null) {
			if(whereSql.startsWith("select")) {
				List listID = dao.queryWithJDBC(whereSql);
				if(listID.size()==0) {
					hsql+="where 1<>1";
				} else {
					String ids = "";
					hsql+="where sheetid in (";
					for (int i = 0; i < listID.size(); i++) {
						ListOrderedMap map = (ListOrderedMap)listID.get(i);
						hsql+=map.getValue(0);
						if(i<listID.size()-1) {
							hsql+=",";
						}
					}
					hsql+=")";
				}
			} else {
				hsql+=" where "+whereSql;
			}
		}
		hsql+=" order by sheetcode";
		
		List list = dao.query(hsql);
		BmsSheet[] sheets = new BmsSheet[list.size()];
		list.toArray(sheets);
		return sheets;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#saveGatherSheetItems(int, com.sinosoft.bms.entity.BmsSheet[])
	 */
	public void saveGatherSheetItems(int sheetid, BmsSheet[] children) throws Exception {
		String sql = "";
		sql+="select tpColDimID,tpRowDimID,itemid,sum(itemval) itemval from bms_sheetitem ";
		sql+="where sheetid in (";
		for (int i = 0; i < children.length; i++) {
			sql+=children[i].getSheetId();
			if(i<children.length-1){
				sql+=",";
			}
		}
		sql+=") group by tpColDimID,tpRowDimID,itemid";
		
		BmsSheet [] sheet = queryBmsSheet("sheetid="+sheetid);
		
		List list = dao.queryWithJDBC(sql);
		
		int itemid = BmsUtils.getID(list.size());
		
		for (int i = 0; i < list.size(); i++) {
			ListOrderedMap map = (ListOrderedMap)list.get(i);
			ListMapGetter g = new ListMapGetter(map);
			
			BmsSheetItem bsi = new BmsSheetItem();
			bsi.setSheetItemId(itemid+i);
			bsi.setBmsItem(new BmsItem(g.getInt("itemid")));
			bsi.setBmsObject(sheet[0].getBmsObject());
			bsi.setBmsSheet(sheet[0]);
			bsi.setBmsTemplet(sheet[0].getBmsTemplet());
			bsi.setBmsTpColDim(new BmsTpColDim(g.getInt("tpColDimID")));
			bsi.setBmsTpRowDim(new BmsTpRowDim(g.getInt("tpRowDimID")));
			bsi.setItemVal(g.getBigDecimal("itemval"));
			dao.insert(bsi);
			
		}
		
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.budgets.Templete#queryBmsSheetItem(int)
	 */
	public BmsSheetItem[] queryBmsSheetItem(int sheetid) throws Exception {
		BmsSheetItem [] items = null;
		List list = dao.query("from BmsSheetItem where sheetid="+sheetid);
		items = new BmsSheetItem[list.size()];
		list.toArray(items);
		return items;
	}
	
}
