package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsScheme;
import com.sinosoft.utility.SSRS;

public interface BmsSchemeObj {
	
	//查询预算方案信息
    public abstract SSRS queryBmsSchemeAll() throws Exception;
    
    //删除预算方案信息
    public abstract void delete(List inputList) throws Exception;
    
    //更新预算方案
    public abstract void update(BmsScheme shecheme,String bgObjId) throws Exception;
    
    //新增预算方案
    public abstract void insert(BmsScheme shecheme,String bgObjId) throws Exception;
    
    //根据界面元素查询方案
    public abstract SSRS queryBmsScheme(BmsScheme shecheme,String bgObjId) throws Exception;
    
    //更新预算方案---启用、取消启用
    public abstract void update(String bgSchId,String useType) throws Exception;
    
}
