package com.sinosoft.bms.struts.formbeans;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.sinosoft.bms.entity.BmsItem;

public class AddBmsItemForm extends ActionForm{
	
	private String itemID;
	private String itemCode;
	private String itemName;
	private String parentItem;
	private String itemType;
	private String subjCode;
	private String formula;
	private String operate;
	
    private String[] hideGridNo;
    private String[] hideGrid1;
	
    /**
     * @ 获取待删除数据
     * @return
     */
    public List getDelBmsItemInfo(){
    	List inputList = new ArrayList();
    	int length;
    	if(hideGridNo==null){
    	   length=0;
    	}else{
    		length = hideGridNo.length;
    	}
    	for(int i=0;i<length;i++){
    		BmsItem item = new BmsItem();    		
    		item.setItemId(Integer.parseInt(hideGrid1[i]));
    		inputList.add(item);
    	}
    	return inputList;
    }

    
    public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getParentItem() {
		return parentItem;
	}
	public void setParentItem(String parentItem) {
		this.parentItem = parentItem;
	}
	public String getSubjCode() {
		return subjCode;
	}
	public void setSubjCode(String subjCode) {
		this.subjCode = subjCode;
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
