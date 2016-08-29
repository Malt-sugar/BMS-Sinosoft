package com.sinosoft.bms.struts.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsObjectType;

public class AddBjObjTypeForm extends ActionForm{
	
	private String bjObjTypeId;
	private String bjObjTypeCode;
	private String bjObjTypeName;
	private String operate;	
	
    private String[] hideGridNo;
    private String[] hideGrid1;
    private String[] hideGrid2;
    private String[] hideGrid3;
    
    /**
     * @ 获取待删除数据
     * @return
     */
    public List getDelBmsObjType(){
    	List inputList = new ArrayList();
    	int length;
    	if(hideGridNo==null){
    	   length=0;
    	}else{
    		length = hideGridNo.length;
    	}
    	for(int i=0;i<length;i++){
    		BmsObjectType objType = new BmsObjectType();
    		objType.setBgObjTypeId(Integer.parseInt(hideGrid1[i]));
    		inputList.add(objType);
    	}
    	return inputList;
    }
	

	public String getBjObjTypeCode() {
		return bjObjTypeCode;
	}

	public void setBjObjTypeCode(String bjObjTypeCode) {
		this.bjObjTypeCode = bjObjTypeCode;
	}

	public String getBjObjTypeName() {
		return bjObjTypeName;
	}

	public void setBjObjTypeName(String bjObjTypeName) {
		this.bjObjTypeName = bjObjTypeName;
	}


	public String[] getHideGrid1() {
		return hideGrid1;
	}

	public void setHideGrid1(String[] hideGrid1) {
		this.hideGrid1 = hideGrid1;
	}

	public String[] getHideGrid2() {
		return hideGrid2;
	}

	public void setHideGrid2(String[] hideGrid2) {
		this.hideGrid2 = hideGrid2;
	}

	public String[] getHideGrid3() {
		return hideGrid3;
	}

	public void setHideGrid3(String[] hideGrid3) {
		this.hideGrid3 = hideGrid3;
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


	public String getBjObjTypeId() {
		return bjObjTypeId;
	}


	public void setBjObjTypeId(String bjObjTypeId) {
		this.bjObjTypeId = bjObjTypeId;
	}

}
