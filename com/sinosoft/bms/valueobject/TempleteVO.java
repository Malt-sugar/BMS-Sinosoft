/**
 *
 * Created on 2009-4-22
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import java.io.Serializable;

import com.sinosoft.bms.entity.BmsTemplet;
import com.sinosoft.bms.entity.BmsTpBgObj;
import com.sinosoft.bms.entity.BmsTpColDim;
import com.sinosoft.bms.entity.BmsTpParamDim;
import com.sinosoft.bms.entity.BmsTpRowDim;

/**
 * @author sunrui
 *
 */
public class TempleteVO implements Serializable {

	public BmsTemplet bmsTemplet = null;
	public BmsTpBgObj [] bmsTpBgObjs = null;
	public BmsTpParamDim [] bmsTpParamDims = null;
	public BmsTpColDim [] colDims = null;
	public BmsTpRowDim [] rowDims = null;
	
	/**
	 * 
	 */
	public TempleteVO(BmsTemplet bmsTemplet) {
		this.bmsTemplet=bmsTemplet;
	}
	/**
	 * @return the bmsTemplet
	 */
	public BmsTemplet getBmsTemplet() {
		return bmsTemplet;
	}
	/**
	 * @param bmsTemplet the bmsTemplet to set
	 */
	public void setBmsTemplet(BmsTemplet bmsTemplet) {
		this.bmsTemplet = bmsTemplet;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if(bmsTemplet!=null) {
			return bmsTemplet.getTpCode()+" "+bmsTemplet.getTpName();
		}
		return super.toString();
	}
	/**
	 * @return the bmsTpBgObjs
	 */
	public BmsTpBgObj[] getBmsTpBgObjs() {
		return bmsTpBgObjs;
	}
	/**
	 * @param bmsTpBgObjs the bmsTpBgObjs to set
	 */
	public void setBmsTpBgObjs(BmsTpBgObj[] bmsTpBgObjs) {
		this.bmsTpBgObjs = bmsTpBgObjs;
	}
	/**
	 * @return the bmsTpParamDims
	 */
	public BmsTpParamDim[] getBmsTpParamDims() {
		return bmsTpParamDims;
	}
	/**
	 * @param bmsTpParamDims the bmsTpParamDims to set
	 */
	public void setBmsTpParamDims(BmsTpParamDim[] bmsTpParamDims) {
		this.bmsTpParamDims = bmsTpParamDims;
	}
	/**
	 * @return the colDims
	 */
	public BmsTpColDim[] getColDims() {
		return colDims;
	}
	/**
	 * @param colDims the colDims to set
	 */
	public void setColDims(BmsTpColDim[] colDims) {
		this.colDims = colDims;
	}
	/**
	 * @return the rowDims
	 */
	public BmsTpRowDim[] getRowDims() {
		return rowDims;
	}
	/**
	 * @param rowDims the rowDims to set
	 */
	public void setRowDims(BmsTpRowDim[] rowDims) {
		this.rowDims = rowDims;
	}

}
