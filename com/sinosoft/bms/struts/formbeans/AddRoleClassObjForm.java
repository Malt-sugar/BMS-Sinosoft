package com.sinosoft.bms.struts.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsRoleClass;

public class AddRoleClassObjForm extends ActionForm{
	
	private String rcid;
	private String rcCode;
	private String rcName;
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
    		BmsRoleClass bmsRoleClass = new BmsRoleClass();    		
    		bmsRoleClass.setRcid(Integer.parseInt(hideGrid1[i]));
    		inputList.add(bmsRoleClass);
    	}
    	return inputList;
    }
	
	public String getRcCode() {
		return rcCode;
	}
	public void setRcCode(String rcCode) {
		this.rcCode = rcCode;
	}
	public String getRcid() {
		return rcid;
	}
	public void setRcid(String rcid) {
		this.rcid = rcid;
	}
	public String getRcName() {
		return rcName;
	}
	public void setRcName(String rcName) {
		this.rcName = rcName;
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
