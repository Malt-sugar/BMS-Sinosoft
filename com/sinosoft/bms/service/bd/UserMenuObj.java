package com.sinosoft.bms.service.bd;

import java.util.List;

public interface UserMenuObj {
	
	//更新用户菜单权限
    public abstract void update(List inputList) throws Exception;
}
