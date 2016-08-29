package com.sinosoft.bms.service.bd;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;
import com.sinosoft.utility.ExeSQL;
import com.sinosoft.utility.SSRS;

public class BmsItemObjImpl implements BmsItemObj{

	protected DAO dao = null;
	private JdbcTemplate jdbcTemplate;
	/**
	 * 
	 */
	public BmsItemObjImpl(DAO dao) {
		this.dao=dao;
	}
	/**
	 *@throws Exception 
	 * @ 角色类新增操作 
	 */
	public void insert(BmsItem bmsItem) throws Exception{	
		int itemId = BmsUtils.getID();
		bmsItem.setItemId(itemId);	
		try{
		 dao.insert(bmsItem);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
   
	/**
	 * @throws Exception 
	 * @ 查询预算项目信息
	 */
	public SSRS queryBmsItemObj() throws Exception{			
		SSRS ssrs =new SSRS(10);		
		String hsql = "from BmsItem order by itemId";
		List list = dao.query(hsql);		
		try{			
			for(int i=0;i<list.size();i++){
				BmsItem bi = (BmsItem) list.get(i);
				String strSql = "select itemCode,itemName from Bms_Item  where itemId='"+bi.getParentItem()+"'";
				System.out.println("---------"+strSql);
				ExeSQL exeSQL = new ExeSQL();
				SSRS rs = exeSQL.execSQL(strSql);				
				ssrs.SetText(String.valueOf(bi.getItemId()));
				ssrs.SetText(bi.getItemCode());
				ssrs.SetText(bi.getItemName());
				ssrs.SetText(String.valueOf(bi.getParentItem()));
				if(rs.getMaxRow()<=0){
					ssrs.SetText("  ");
					ssrs.SetText("  ");
				}else{
                //if(rsList.size()>0){
                	for(int j=1;j<=rs.getMaxRow();j++){  
					   ssrs.SetText(rs.GetText(j,1));
					   ssrs.SetText(rs.GetText(j,2));
                	}
				}				
				ssrs.SetText(bi.getItemType());
				if(bi.getItemType().equals("1")){
					ssrs.SetText("科目");
				}else{
					ssrs.SetText("指标");
				}
				ssrs.SetText(bi.getSubjCode());
				ssrs.SetText(bi.getFormula());							
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return ssrs;		
	}
	
	/**
	 * @ 查父项目编码及名称
	 */
	public List queryParentItemInfo(String itemId) throws Exception{
		DataSource dataSource = (DataSource) BeanFactory.getBean("dataSource");
		jdbcTemplate = new JdbcTemplate(dataSource);
		String strSql = "select itemCode,itemName from bms_item where itemId='"+itemId+"'";
		List list =jdbcTemplate.queryForList(strSql);		
		return list;
	}
	
	/**
	 * @ 删除预算项目信息
	 */
	public void delete(List inputList) throws Exception{
		if(inputList==null)
			throw new RuntimeException("数据信息异常!");
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsItem item = (BmsItem) inputList.get(i);
			String rcId = String.valueOf(item.getItemId());
			String strSql = "delete from Bms_Item where itemId='"+rcId+"'";
			dao.excute(strSql);
		}
	}

	/**
	 *@throws Exception 
	 * @ 预算项目修改操作 
	 */
	public void update(BmsItem bmsItem) throws Exception{		
		dao.update(bmsItem);
	}
	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.BmsItemObj#queryAllItems()
	 */
	public BmsItem[] queryAllItems() throws Exception {
		List list = dao.query("from BmsItem order by itemCode");
		BmsItem [] items = new BmsItem[list.size()];
		list.toArray(items);
		return items;
	}
}
