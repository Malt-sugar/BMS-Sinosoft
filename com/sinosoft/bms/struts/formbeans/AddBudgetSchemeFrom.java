package com.sinosoft.bms.struts.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsScheme;

public class AddBudgetSchemeFrom extends ActionForm{
	
	private String bgSchID;
	private String bgSchCode;
	private String bgSchName;
	private String bgObjID;
	private String useFlag;
	private String startDate;
	private String endDat;
	private String operate;
	private String useType;
	
    private String[] hideGridNo;
    private String[] hideGrid1;
    private String[] hideGrid4;
	
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
    		BmsScheme bs = new BmsScheme();    		
    		bs.setBgSchId(Integer.parseInt(hideGrid1[i]));
    		inputList.add(bs);
    	}
    	return inputList;
    }
    
    /**
     * @取界面元素查询
     * @return
     */
    public BmsScheme getBmsScheme(){
    	BmsScheme scheme = new BmsScheme();
    	if(this.getBgSchID()!=null&&!"".equals(this.getBgSchID().trim())){
    	  scheme.setBgSchId(Integer.parseInt(this.getBgSchID().trim()));
    	}
    	if(this.getStartDate()!=null&&!"".equals(this.getStartDate().trim())){
    		scheme.setStartDate(this.getStartDate().trim());
    	}
    	if(this.getEndDat()!=null&&!"".equals(this.getEndDat().trim())){
    		scheme.setEndDat(this.getEndDat().trim());
    	}
    	if(this.getUseFlag()!=null&&!"".equals(this.getUseFlag().trim())){
    		char flag=0;
    		for(int i=0;i<this.getUseFlag().trim().length();i++){
    			flag = this.getUseFlag().trim().charAt(i);
    		}
    		scheme.setUseFlag(new Character(flag));
    	}
    	return scheme;
    }
	
	public String getBgObjID() {
		return bgObjID;
	}
	public void setBgObjID(String bgObjID) {
		this.bgObjID = bgObjID;
	}
	public String getBgSchCode() {
		return bgSchCode;
	}
	public void setBgSchCode(String bgSchCode) {
		this.bgSchCode = bgSchCode;
	}
	public String getBgSchID() {
		return bgSchID;
	}
	public void setBgSchID(String bgSchID) {
		this.bgSchID = bgSchID;
	}
	public String getBgSchName() {
		return bgSchName;
	}
	public void setBgSchName(String bgSchName) {
		this.bgSchName = bgSchName;
	}
	public String getEndDat() {
		return endDat;
	}
	public void setEndDat(String endDat) {
		this.endDat = endDat;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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

	public String[] getHideGrid4() {
		return hideGrid4;
	}

	public void setHideGrid4(String[] hideGrid4) {
		this.hideGrid4 = hideGrid4;
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

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

}
