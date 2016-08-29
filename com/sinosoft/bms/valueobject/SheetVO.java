/**
 *
 * Created on 2009-5-15
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import java.io.Serializable;

import com.sinosoft.bms.entity.BmsSheet;
import com.sinosoft.bms.entity.BmsSheetItem;
import com.sinosoft.bms.framework.BmsUtils;

/**
 * @author sunrui
 *
 */
public class SheetVO implements Serializable {

	public BmsSheet bmsSheet = null;
	public BmsSheetItem [] bmsSheetItems = null;
	
	public String sheetCode = null;
	public String sheetName = null;
	
	public int tpID = 0;
	public int bgObjID = 0;
	public int tpParamID = 0;
	public int dimMemID = 0;
	
	public String tpCode = null;
	public String tpName = null;
	public String bgObjCode = null;
	public String bgObjName = null;
	public String dimMemCode = null;
	public String dimMemName = null;
	
	
	/**
	 * 
	 */
	public SheetVO() {
	}
	
	
	/**
	 * @return the bmsSheet
	 */
	public BmsSheet getBmsSheet() {
		return bmsSheet;
	}
	/**
	 * @param bmsSheet the bmsSheet to set
	 */
	public void setBmsSheet(BmsSheet bmsSheet) {
		this.bmsSheet = bmsSheet;
	}

	public boolean isInputed() {
		return bmsSheet!=null;
	}
	
	public String getInputedStatus() {
		return isInputed()?"已编制":"未编制";
	}
	
	public boolean isRep() throws Exception {
		return bmsSheet==null?false:BmsUtils.charToBool(bmsSheet.getRepFlag());
	}
	
	public String getRepStatus() throws Exception {
		return isRep()?"已上报":"未上报";
	}
	
	public boolean isEnabled() throws Exception {
		return bmsSheet==null?false:BmsUtils.charToBool(bmsSheet.getEnabledFlag());
	}
	
	public String getEnabledStatus() throws Exception {
		return isEnabled()?"已批复":"未批复";
	}


	/**
	 * @return the bgObjCode
	 */
	public String getBgObjCode() {
		return bgObjCode;
	}


	/**
	 * @param bgObjCode the bgObjCode to set
	 */
	public void setBgObjCode(String bgObjCode) {
		this.bgObjCode = bgObjCode;
	}


	/**
	 * @return the bgObjID
	 */
	public int getBgObjID() {
		return bgObjID;
	}


	/**
	 * @param bgObjID the bgObjID to set
	 */
	public void setBgObjID(int bgObjID) {
		this.bgObjID = bgObjID;
	}


	/**
	 * @return the bgObjName
	 */
	public String getBgObjName() {
		return bgObjName;
	}


	/**
	 * @param bgObjName the bgObjName to set
	 */
	public void setBgObjName(String bgObjName) {
		this.bgObjName = bgObjName;
	}


	/**
	 * @return the dimMemCode
	 */
	public String getDimMemCode() {
		return dimMemCode;
	}


	/**
	 * @param dimMemCode the dimMemCode to set
	 */
	public void setDimMemCode(String dimMemCode) {
		this.dimMemCode = dimMemCode;
	}


	/**
	 * @return the dimMemID
	 */
	public int getDimMemID() {
		return dimMemID;
	}


	/**
	 * @param dimMemID the dimMemID to set
	 */
	public void setDimMemID(int dimMemID) {
		this.dimMemID = dimMemID;
	}


	/**
	 * @return the dimMemName
	 */
	public String getDimMemName() {
		return dimMemName;
	}


	/**
	 * @param dimMemName the dimMemName to set
	 */
	public void setDimMemName(String dimMemName) {
		this.dimMemName = dimMemName;
	}


	/**
	 * @return the sheetCode
	 */
	public String getSheetCode() {
		return sheetCode;
	}


	/**
	 * @param sheetCode the sheetCode to set
	 */
	public void setSheetCode(String sheetCode) {
		this.sheetCode = sheetCode;
	}


	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}


	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}


	/**
	 * @return the tpCode
	 */
	public String getTpCode() {
		return tpCode;
	}


	/**
	 * @param tpCode the tpCode to set
	 */
	public void setTpCode(String tpCode) {
		this.tpCode = tpCode;
	}


	/**
	 * @return the tpID
	 */
	public int getTpID() {
		return tpID;
	}


	/**
	 * @param tpID the tpID to set
	 */
	public void setTpID(int tpID) {
		this.tpID = tpID;
	}


	/**
	 * @return the tpName
	 */
	public String getTpName() {
		return tpName;
	}


	/**
	 * @param tpName the tpName to set
	 */
	public void setTpName(String tpName) {
		this.tpName = tpName;
	}


	/**
	 * @return the tpParamID
	 */
	public int getTpParamID() {
		return tpParamID;
	}


	/**
	 * @param tpParamID the tpParamID to set
	 */
	public void setTpParamID(int tpParamID) {
		this.tpParamID = tpParamID;
	}


	/**
	 * @return the bmsSheetItems
	 */
	public BmsSheetItem[] getBmsSheetItems() {
		return bmsSheetItems;
	}


	/**
	 * @param bmsSheetItems the bmsSheetItems to set
	 */
	public void setBmsSheetItems(BmsSheetItem[] bmsSheetItems) {
		this.bmsSheetItems = bmsSheetItems;
	}


}
