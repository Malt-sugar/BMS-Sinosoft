package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsScheme;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;

public class BmsSchemeObjImpl implements BmsSchemeObj{	
	
	protected DAO dao = null;
	public BmsSchemeObjImpl(DAO dao){
		this.dao=dao;
	}
	
	//查询预算方案信息
	public SSRS queryBmsSchemeAll() throws Exception{
		ExeSQL exeSql;
		SSRS ssrs = new SSRS(10);
		String hsql = " from BmsScheme order by bgSchId ";
		List list = dao.query(hsql);
		if(list==null)
			throw new RuntimeException("尚未建立预算方案信息!");
		for(int i=0;i<list.size();i++){
			BmsScheme shcheme = (BmsScheme) list.get(i);
			String objectSql = " select bgObjCode,bgObjName from bms_object where bgObjId='"+shcheme.getBmsObject().getBgObjId()+"'";
			exeSql = new ExeSQL();
			SSRS rs = exeSql.execSQL(objectSql);
			ssrs.SetText(String.valueOf(shcheme.getBgSchId()));
			ssrs.SetText(shcheme.getBgSchCode());
			ssrs.SetText(shcheme.getBgSchName());
			ssrs.SetText(String.valueOf(shcheme.getBmsObject().getBgObjId()));
			if(rs.getMaxRow()==0){
				ssrs.SetText(" ");
				ssrs.SetText(" ");
			}else{
				for(int j=1;j<=rs.getMaxRow();j++){
					ssrs.SetText(rs.GetText(j,1));
					ssrs.SetText(rs.GetText(j,2));
				}
			}
			ssrs.SetText(shcheme.getStartDate());
			ssrs.SetText(shcheme.getEndDat());
			ssrs.SetText(shcheme.getUseFlag().toString());
			if("0".equals(shcheme.getUseFlag())){
				ssrs.SetText("未启用");
			}else{
				ssrs.SetText("启用");
			}
		}
		return ssrs;
	}
	
	/**
	 * 根据界面元素查询预算方案
	 */
	public SSRS queryBmsScheme(BmsScheme shecheme,String bgObjId) throws Exception{
		ExeSQL exeSql;
		SSRS ssrs = new SSRS(10);
		String strSql = " select bs.bgSchId,bs.bgSchCode,bs.bgSchName,bs.bgObjId,"
			          + " bo.bgObjCode,bo.bgObjName,bs.startDate,bs.endDat,bs.useFlag "
			          + " from bms_scheme bs "
			          + " join bms_object bo on bs.bgObjId = bo.bgObjId "
			          + " where 1=1 "
			          + getWhereSql("bs.bgObjId",bgObjId.trim())
			          + getWhereSql("bs.startDate>",shecheme.getStartDate())
			          + getWhereSql("bs.endDat<",shecheme.getEndDat())
			          + getWhereSql("bs.useFlag",(shecheme.getUseFlag()==null?"":shecheme.getUseFlag().toString()))
			          + getWhereSql("bs.bgSchId",(shecheme.getBgSchId()==0?"":String.valueOf(shecheme.getBgSchId())));
		System.out.println("*******查询预算方案SQL****"+strSql);
		try{
			exeSql = new ExeSQL();
			SSRS rs = exeSql.execSQL(strSql);
			if(rs.getMaxRow()==0)
				throw new RuntimeException("没有符合条件的数据!");
			
			for(int i=1;i<=rs.getMaxRow();i++){
				ssrs.SetText(rs.GetText(i,1));
				ssrs.SetText(rs.GetText(i,2));
				ssrs.SetText(rs.GetText(i,3));
				ssrs.SetText(rs.GetText(i,4));
				ssrs.SetText(rs.GetText(i,5));
				ssrs.SetText(rs.GetText(i,6));
				ssrs.SetText(rs.GetText(i,7));
				ssrs.SetText(rs.GetText(i,8));
				ssrs.SetText(rs.GetText(i,9));
				if(rs.GetText(i,9).equals("0")){
				  ssrs.SetText("未启用");
				}else{
					ssrs.SetText("启用");
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ssrs;
	}
	
	//删除预算方案
	public void delete(List inputList) throws Exception{
		if(inputList==null)
			throw new RuntimeException("未找到待删除的信息!");
		
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsScheme scheme = (BmsScheme) inputList.get(i);
			dao.delete(scheme);
		}		
	}
	
	//更新预算方案
	public void update(BmsScheme shecheme,String bgObjId) throws Exception{
		String hsql = " from BmsObject bo where bo.id.bgObjId='"+bgObjId+"'";
		List list = dao.query(hsql);
		if(list.size()<=0)
			throw new RuntimeException("未找到预算主体信息!");
		
		shecheme.setBmsObject((BmsObject) list.get(0));
		dao.update(shecheme);
	}
	
	/**
	 * @执行取消启用、启用操作
	 */
	public void update(String bgSchId,String useType) throws Exception{
		String updateSql = "";
		if(useType.equals("cancelUse")){//取消启用
			updateSql = " update bms_scheme set useflag='0' where bgSchId='"+bgSchId+"'";
		}
		if(useType.equals("budgetUse")){//启用
			updateSql = " update bms_scheme set useflag='1' where bgSchId='"+bgSchId+"'";
		}
		try{
			dao.excute(updateSql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//新增预算方案
	public void insert(BmsScheme shecheme,String bgObjId) throws Exception{	
		
		String hsql = " from BmsObject bo where bo.id.bgObjId='"+bgObjId+"'";
		List list = dao.query(hsql);
		if(list.size()<=0)
			throw new RuntimeException("未找到预算主体信息!");
		
		shecheme.setBmsObject((BmsObject) list.get(0));
		
		int bgSchId = BmsUtils.getID();
		shecheme.setBgSchId(bgSchId);
		dao.insert(shecheme);
	}
	
	/**
	 * 根据字段名和字段值取得Where子句
	 *
	 * @param fieldCode String
	 * @param fieldValue String
	 * @return String
	 */
	public String getWhereSql(String fieldCode, String fieldValue) {
		if (fieldValue == null || fieldValue.trim().equals("")) {
			return "";
		} else {
			return " and " + fieldCode + "='" + fieldValue.trim() + "'";
		}
	}
	
}
