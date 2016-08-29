package com.sinosoft.bms.struts.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsUserMenu;
import com.sinosoft.bms.entity.BmsUserMenuId;

public class AddUserMenuForm extends ActionForm{
	
	private String userId;
	private String Menu;
	
	/**
	 * @ 获取菜单权限节点ID
	 * @return
	 */
	public List getUserMenuCode(String menuid){
		List inputList = new ArrayList();
		System.out.println("=======菜单节点======"+menuid);
		//分解菜单节点
		String[] menu_id =menuid.split(":");
		for(int i=0;i<menu_id.length;i++){
			String _menu = menu_id[i];
		    if(_menu!=null&&!"".equals(_menu)){
				BmsUserMenu _userM = new BmsUserMenu();
				BmsUserMenuId _id = new BmsUserMenuId();
				_id.setMenuId(new Integer(_menu));
				_id.setUserId(new Integer(this.getUserId()));
				_userM.setId(_id);
				inputList.add(_userM);
		    }
		}
		return inputList;
	}
	
	public String getMenu() {
		return Menu;
	}
	public void setMenu(String menu) {
		Menu = menu;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
