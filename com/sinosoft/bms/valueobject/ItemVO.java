/**
 *
 * Created on 2009-4-24
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import java.io.Serializable;

import com.sinosoft.bms.entity.BmsItem;
import com.sinosoft.bms.swing.common.ITreeVO;

/**
 * @author sunrui
 *
 */
public class ItemVO implements Serializable,ITreeVO{

	public BmsItem bmsItem = null;
	public boolean selected = false;
	
	/**
	 * 
	 */
	public ItemVO(BmsItem item) {
		this.bmsItem=item;
	}

	/**
	 * @return the bmsItem
	 */
	public BmsItem getBmsItem() {
		return bmsItem;
	}

	/**
	 * @param bmsItem the bmsItem to set
	 */
	public void setBmsItem(BmsItem bmsItem) {
		this.bmsItem = bmsItem;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return bmsItem.getItemCode()+" "+bmsItem.getItemName();
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.ITreeVO#getID()
	 */
	public int getID() {
		return bmsItem.getItemId();
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.ITreeVO#getParentID()
	 */
	public int getParentID() {
		return bmsItem.getParentItem()==null?0:bmsItem.getParentItem().intValue();
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
	
	
	
	

}
