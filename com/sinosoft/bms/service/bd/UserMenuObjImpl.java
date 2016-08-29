package com.sinosoft.bms.service.bd;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sinosoft.bms.entity.BmsUserMenu;
import com.sinosoft.bms.framework.BeanFactory;
import com.sinosoft.bms.framework.DAO;

public class UserMenuObjImpl implements UserMenuObj{
	
	protected DAO dao = null;
	//private JdbcTemplate jdbcTemplate;
	
	public UserMenuObjImpl(DAO dao){
		this.dao=dao;
	}

	/**
	 * @  更新用户菜单权限节点
	 */
	public void update(List inputList) throws Exception {		
		// TODO Auto-generated method stub
		//DataSource dataSource = (DataSource) BeanFactory.getBean("dataSource");
		if(inputList==null)
			throw new RuntimeException("请先设置菜单权限!");
		
		for(int i=0;inputList.size()>0&&i<inputList.size();i++){
			BmsUserMenu _menu = (BmsUserMenu) inputList.get(i);
			//String delSql = " delete from bms_userMenu where userid="+_menu.getId().getUserId()+" ";
			//jdbcTemplate = new JdbcTemplate(dataSource);
			//jdbcTemplate.execute(delSql);			
			//dao.flush();
			System.out.println("========="+_menu.getId().getMenuId());
			dao.update(_menu);
			//dao.create(_menu);
		}
	}

}
