/**
 *
 * Created on 2009-4-30
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import java.io.Serializable;

import com.sinosoft.bms.entity.BmsObject;
import com.sinosoft.bms.swing.common.ITreeVO;

/**
 * @author sunrui
 *
 */
public class ObjVO implements Serializable, ITreeVO {

	public BmsObject bmsObject = null;
	public boolean selected = false;
	
	/**
	 * 
	 */
	public ObjVO(BmsObject bmsObj) {
		bmsObject=bmsObj;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.ITreeVO#getID()
	 */
	public int getID() {
		return bmsObject.getBgObjId();
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.ITreeVO#getParentID()
	 */
	public int getParentID() {
		return bmsObject.getParentBgObj()==null?0:bmsObject.getParentBgObj().intValue();
	}
	
	public String toString() {
		return bmsObject.getBgObjCode()+" "+bmsObject.getBgObjName();
	}

	/**
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @return the bmsObject
	 */
	public BmsObject getBmsObject() {
		return bmsObject;
	}

	/**
	 * @param bmsObject the bmsObject to set
	 */
	public void setBmsObject(BmsObject bmsObject) {
		this.bmsObject = bmsObject;
	}

}
