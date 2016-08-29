/**
 *
 * Created on 2009-4-23
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import org.apache.bsf.util.ObjInfo;

/**
 * @author sunrui
 *
 */
public class UseFlagVO {



	public int useflag = 0;
	
	public static final int USEFALG_INUSE = 1;
	public static final int USEFLAG_NOTUSE = 0;
	
	protected String [] strUseFlag = {"Œ¥∆Ù”√","∆Ù”√"};
	
	/**
	 * 
	 */
	public UseFlagVO(int useflag) {
		this.useflag=useflag;
	}
	
	public UseFlagVO(Character cUseFlag) {
		if(cUseFlag==null||cUseFlag.charValue()=='0') {
			useflag=0;
		} else {
			useflag=1;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return strUseFlag[useflag];
	}
	
	public Character toCharValue() {
		return new Character(useflag==0?'0':'1');
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if(obj!=null && obj instanceof UseFlagVO) {
			UseFlagVO anothervo = (UseFlagVO)obj;
			return this.useflag==anothervo.useflag;
		}
		return false;
	}

}
