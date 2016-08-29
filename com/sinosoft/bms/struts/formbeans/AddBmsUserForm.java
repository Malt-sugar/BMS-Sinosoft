package com.sinosoft.bms.struts.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsUser;

public class AddBmsUserForm extends ActionForm{
	
	private String userId;
	private String  userCode;
	private String  userName;
	private String  password;
	private String  bgObjId;
	private String  useFlag;
	private String  email;
	private String  phone;
	private String operate;
	
    private String[] hideGridNo;
    private String[] hideGrid1;
	
    /**
     * @ 获取待删除数据
     * @return
     */
    public List getDelBmsUserObjInfo(){
    	List inputList = new ArrayList();
    	int length;
    	if(hideGridNo==null){
    	   length=0;
    	}else{
    		length = hideGridNo.length;
    	}
    	for(int i=0;i<length;i++){
    		BmsUser bmsUser = new BmsUser();
    		System.out.println("=====================");
    		bmsUser.setUserId(Integer.parseInt(hideGrid1[i]));
    		inputList.add(bmsUser);
    	}
    	return inputList;
    }
	
	
	public String getBgObjId() {
		return bgObjId;
	}
	public void setBgObjId(String bgObjId) {
		this.bgObjId = bgObjId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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

}
