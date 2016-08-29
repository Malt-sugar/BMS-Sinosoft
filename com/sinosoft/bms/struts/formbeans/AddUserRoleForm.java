package com.sinosoft.bms.struts.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsUserBgObj;
import com.sinosoft.bms.entity.BmsUserBgObjId;
import com.sinosoft.bms.entity.BmsUserRole;
import com.sinosoft.bms.entity.BmsUserRoleId;

public class AddUserRoleForm extends ActionForm{
	
	private String userId;
	private String parentRoleId;
	private String operate;
	private String alginType;
	private String parentBgObjId;
	
    private String[] hideGridNo;
    private String[] hideGrid1;
    private String[] hideGrid4;
	
    /**
     * @ 获取待删除的角色数据
     * @return
     */
    public List getDelBmsUserRole(){
    	List inputList = new ArrayList();
    	int length;
    	if(hideGridNo==null){
    	   length=0;
    	}else{
    		length = hideGridNo.length;
    	}
    	for(int i=0;i<length;i++){
			BmsUserRole bmsRole = new BmsUserRole();
			BmsUserRoleId bmsRoleId = new BmsUserRoleId();
			bmsRoleId.setRoleId(new Integer(hideGrid4[i]));
			bmsRoleId.setUserId(new Integer(hideGrid1[i]));
			bmsRole.setId(bmsRoleId);
    		inputList.add(bmsRole);
    	}
    	return inputList;
    }

	
    /**
     * @ 获取待删除的预算主体数据
     * @return
     */
    public List getDelBmsUserBgObj(){
    	List inputList = new ArrayList();
    	int length;
    	if(hideGridNo==null){
    	   length=0;
    	}else{
    		length = hideGridNo.length;
    	}
    	for(int i=0;i<length;i++){
    		BmsUserBgObj object = new BmsUserBgObj();
    		BmsUserBgObjId objectId = new BmsUserBgObjId();
    		objectId.setBgObjId(new Integer(hideGrid4[i]));
    		objectId.setUserId(new Integer(hideGrid1[i]));
    		object.setId(objectId);
    		inputList.add(object);
    	}
    	return inputList;
    }
    
    /**
     * @处理角色列表
     * @return
     */
    public List getRoleList(String roleId){
    	List parentRoleIdList = new ArrayList();
    	String parentRoleId = roleId.concat(",");
    	int start=0;
    	int i=0;
    	int len = parentRoleId.length();
    	while(i<len){
    		if(parentRoleId.charAt(i)==','){
    			parentRoleIdList.add(parentRoleId.substring(start,i));
    			start=++i;
    		}else{
    			i++;
    		}
    	}
    	if(parentRoleIdList.size()<=0)
    		throw new RuntimeException("未找到已添加的角色信息!");
    	
    	return parentRoleIdList;
    }

    public String getParentRoleId() {
		return parentRoleId;
	}
	public void setParentRoleId(String parentRoleId) {
		this.parentRoleId = parentRoleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String[] getHideGrid1() {
		return hideGrid1;
	}

	public void setHideGrid1(String[] hideGrid1) {
		this.hideGrid1 = hideGrid1;
	}

	public String[] getHideGridNo() {
		return hideGridNo;
	}

	public void setHideGridNo(String[] hideGridNo) {
		this.hideGridNo = hideGridNo;
	}

	public String[] getHideGrid4() {
		return hideGrid4;
	}

	public void setHideGrid4(String[] hideGrid4) {
		this.hideGrid4 = hideGrid4;
	}

	public String getAlginType() {
		System.out.println("========"+alginType);
		return alginType;
	}

	public void setAlginType(String alginType) {
		this.alginType = alginType;
		System.out.println("========"+alginType);
	}


	public String getParentBgObjId() {
		return parentBgObjId;
	}


	public void setParentBgObjId(String parentBgObjId) {
		this.parentBgObjId = parentBgObjId;
	}

}
