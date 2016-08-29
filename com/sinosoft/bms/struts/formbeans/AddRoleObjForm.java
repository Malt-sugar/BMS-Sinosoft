package com.sinosoft.bms.struts.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsRole;

public class AddRoleObjForm extends ActionForm{
	
	private String roleID;
	private String roleCode;
	private String roleName;
	private String bgObjID;
	private String rcID;
	private String useFlag;
	private String operate;
	
	
    private String[] hideGridNo;
    private String[] hideGrid1;
	
    /**
     * @ 获取待删除数据
     * @return
     */
    public List getDelBmsRoleInfo(){
    	List inputList = new ArrayList();
    	int length;
    	if(hideGridNo==null){
    	   length=0;
    	}else{
    		length = hideGridNo.length;
    	}
    	for(int i=0;i<length;i++){
    		BmsRole bmsRole = new BmsRole();    		
    		bmsRole.setRoleId(Integer.parseInt(hideGrid1[i]));
    		inputList.add(bmsRole);
    	}
    	return inputList;
    }
    
	public String getBgObjID() {
		return bgObjID;
	}
	public void setBgObjID(String bgObjID) {
		this.bgObjID = bgObjID;
	}
	public String getRcID() {
		return rcID;
	}
	public void setRcID(String rcID) {
		this.rcID = rcID;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
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

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

}
