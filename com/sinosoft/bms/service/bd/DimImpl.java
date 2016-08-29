/**
 *
 * Created on 2009-4-17
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.bd;

import java.util.List;

import com.sinosoft.bms.entity.BmsDim;
import com.sinosoft.bms.entity.BmsDimMember;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;

/**
 * @author sunrui
 *
 */
public class DimImpl implements Dim {

	protected DAO dao = null;
	/**
	 * 
	 */
	public DimImpl(DAO dao) {
		this.dao=dao;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.Dim#queryDim()
	 */
	public BmsDim[] queryDim(String whereSQL) throws Exception {
		BmsDim [] dims = null;
		List list = dao.query("from BmsDim order by dimCode");
		dims = new BmsDim[list.size()];
		list.toArray(dims);
		return dims;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.Dim#queryDimByID(int)
	 */
	public BmsDim queryDimByID(int dimID) throws Exception {
		List list = dao.query("from BmsDim where dimID ="+dimID);
		if(list.size()==0) return null;
		
		return (BmsDim)list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.Dim#queryMemberByDimID(int)
	 */
	public BmsDimMember queryMemberByID(int dimID) throws Exception {
		List list = dao.query("from BmsDimMember where dimMemId="+dimID);
		if(list.size()==0) return null;
		return (BmsDimMember)list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.Dim#insertDim(com.sinosoft.bms.entity.BmsDim)
	 */
	public void insertDim(BmsDim vo) throws Exception {
		
		List list = dao.query("from BmsDim where dimCode='"+vo.getDimCode()+"'");
		if(list.size()>0) throw new Exception("维度编码已经存在");
		if(vo.getDimCode()==null||vo.getDimCode().trim().length()==0) {
			throw new Exception("维度编码不能为空");
		}
		if(vo.getDimName()==null||vo.getDimName().trim().length()==0) {
			throw new Exception("维度名称不能为空");
		}
		
		vo.setDimId(BmsUtils.getID());
		
		dao.insert(vo);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.Dim#insertDimMember(com.sinosoft.bms.entity.BmsDimMember)
	 */
	public void insertDimMember(BmsDimMember vo) throws Exception {
		vo.setDimMemId(BmsUtils.getID());
		dao.insert(vo);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.Dim#updateDim(com.sinosoft.bms.entity.BmsDim)
	 */
	public void updateDim(BmsDim vo) throws Exception {
		
		List list = dao.query("from BmsDim where dimCode='"+vo.getDimCode()+"' and dimID<>"+vo.getDimId());
		if(list.size()>0) throw new Exception("维度编码已经存在");
		if(vo.getDimCode()==null||vo.getDimCode().trim().length()==0) {
			throw new Exception("维度编码不能为空");
		}
		if(vo.getDimName()==null||vo.getDimName().trim().length()==0) {
			throw new Exception("维度名称不能为空");
		}
		
		dao.update(vo);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.Dim#updateDimMember(com.sinosoft.bms.entity.BmsDimMember)
	 */
	public void updateDimMember(BmsDimMember vo) throws Exception {
		dao.update(vo);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.Dim#queryDimMem(java.lang.String)
	 */
	public BmsDimMember[] queryDimMem(String whereSQL) throws Exception {
		BmsDimMember [] dms = null;
		List list = dao.query("from BmsDimMember order by dimMemCode");
		dms = new BmsDimMember[list.size()];
		list.toArray(dms);
		return dms;
	}

}
