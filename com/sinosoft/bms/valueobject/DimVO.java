/**
 *
 * Created on 2009-5-6
 * @author sunrui
 *
 */
package com.sinosoft.bms.valueobject;

import com.sinosoft.bms.entity.BmsDim;
import com.sinosoft.bms.entity.BmsDimMember;
import com.sinosoft.bms.swing.common.ITreeVO;

/**
 * @author sunrui
 *
 */
public class DimVO implements ITreeVO {

	public BmsDim dim = null;
	public BmsDimMember member = null;
	
	public static final int TYPE_DIM = 0;
	public static final int TYPE_MEMBER = 1;
	
	public int type = TYPE_DIM;
	/**
	 * 
	 */
	public DimVO(BmsDim dim) {
		this.dim = dim;
		type = TYPE_DIM;
	}
	
	public DimVO(BmsDimMember member) {
		this.member=member;
		type = TYPE_MEMBER;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.ITreeVO#getID()
	 */
	public int getID() {
		//如果是维度返回正数，如果是成员返回负数
		
		if(type==TYPE_DIM) {
			return dim.getDimId();
		} else {
			return -1*member.getDimMemId();
		}
		
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.swing.common.ITreeVO#getParentID()
	 */
	public int getParentID() {
		
		if(type==TYPE_DIM) {
			return 0;
		} else {
			if(member.getParentDimMem()==null) {
				return member.getBmsDim().getDimId();
			} else {
				int parentid = member.getParentDimMem().intValue();
				if(parentid==0) {
					return member.getBmsDim().getDimId();
				} else {
					return -1*parentid;
				}
				
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if(type==TYPE_DIM) {
			return dim.getDimCode()+" "+dim.getDimName();
		} else {
			return member.getDimMemCode()+" "+member.getDimMemName();
		}
	}

	/**
	 * @return the dim
	 */
	public BmsDim getDim() {
		return dim;
	}

	/**
	 * @param dim the dim to set
	 */
	public void setDim(BmsDim dim) {
		this.dim = dim;
	}

	/**
	 * @return the member
	 */
	public BmsDimMember getMember() {
		return member;
	}

	/**
	 * @param member the member to set
	 */
	public void setMember(BmsDimMember member) {
		this.member = member;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

}
