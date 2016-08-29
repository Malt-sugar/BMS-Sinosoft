package com.sinosoft.bms.struts.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.entity.BmsObjectType;

public class AddBgObjForm extends ActionForm{
	
	private String bgObjID;
	private String bgObjTypeID;
	private String parentBgObj;
	private String bgObjCode;
	private String bgObjName;
	private String operSysCode;
	private String useFlag;
	private String operate;
	
    private String[] hideGridNo;
    private String[] hideGrid1;
	
    /**
     * @ 获取待删除数据
     * @return
     */
    public List getDelBmsObjInfo(){
    	List inputList = new ArrayList();
    	int length;
    	if(hideGridNo==null){
    	   length=0;
    	}else{
    		length = hideGridNo.length;
    	}
    	for(int i=0;i<length;i++){
    		BmsObject bmsObj = new BmsObject();
    		System.out.println("=====================");
    		bmsObj.setBgObjId(Integer.parseInt(hideGrid1[i]));
    		inputList.add(bmsObj);
    	}
    	return inputList;
    }
	
	public String getBgObjCode() {
		return bgObjCode;
	}
	public void setBgObjCode(String bgObjCode) {
		this.bgObjCode = bgObjCode;
	}
	public String getBgObjID() {
		return bgObjID;
	}
	public void setBgObjID(String bgObjID) {
		this.bgObjID = bgObjID;
	}
	public String getBgObjName() {
		return bgObjName;
	}
	public void setBgObjName(String bgObjName) {
		this.bgObjName = bgObjName;
	}
	public String getBgObjTypeID() {
		return bgObjTypeID;
	}
	public void setBgObjTypeID(String bgObjTypeID) {
		this.bgObjTypeID = bgObjTypeID;
	}
	public String getOperSysCode() {
		return operSysCode;
	}
	public void setOperSysCode(String operSysCode) {
		this.operSysCode = operSysCode;
	}
	public String getParentBgObj() {
		return parentBgObj;
	}
	public void setParentBgObj(String parentBgObj) {
		this.parentBgObj = parentBgObj;
	}
	public String getUseFlag() {
		return useFlag;
	}
	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
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
