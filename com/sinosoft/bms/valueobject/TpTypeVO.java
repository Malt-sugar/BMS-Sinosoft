/**
 *
 * Created on 2009-4-23
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

/**
 * @author sunrui
 *
 */
public class TpTypeVO {

	public static final int TPTYPE_INPUT = 0;
	public static final int TPTYPE_ANALYSE = 1;
	
	
	protected String [] strTpType = {"编制类表样","分析类表样"};
	
	public int tpType = 0;
	/**
	 * 
	 */
	public TpTypeVO(int tpType) {
		this.tpType=tpType;
	}
	
	public TpTypeVO(Character cTpType) {
		if(cTpType==null||cTpType.charValue()=='0') {
			tpType=0;
		} else {
			tpType=1;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof TpTypeVO) {
			TpTypeVO anothervo = (TpTypeVO) obj;
			return this.tpType==anothervo.tpType;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return strTpType[tpType];
	}
	
	public Character toCharValue() {
		return new Character(tpType==0?'0':'1');
	}

}
