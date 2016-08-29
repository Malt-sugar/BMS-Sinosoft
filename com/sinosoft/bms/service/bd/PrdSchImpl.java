/**
 *
 * Created on 2009-4-13
 * @author sunrui
 *
 */
package com.sinosoft.bms.service.bd;

import java.util.List;

import org.apache.log4j.Logger;

import com.sinosoft.bms.entity.BmsPrdSch;
import com.sinosoft.bms.framework.BmsUtils;
import com.sinosoft.bms.framework.DAO;

/**
 * @author sunrui
 *
 */
public class PrdSchImpl implements PrdSch {

	
	protected static Logger logger = Logger.getLogger(PrdSchImpl.class);
	protected DAO dao = null;
	/**
	 * 
	 */
	public PrdSchImpl(DAO dao) {
		this.dao=dao;
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.PrdSch#queryAll()
	 */
	public BmsPrdSch[] queryAll() throws Exception {
		BmsPrdSch [] prdschs = null;
		List prdSchList = dao.query("from BmsPrdSch order by prdSchCode");
		prdschs = new BmsPrdSch[prdSchList.size()];
		prdSchList.toArray(prdschs);
		return prdschs;
	}
	
	public void insert(BmsPrdSch bmsPrdSch) throws Exception {
		
		List list = dao.query("from BmsPrdSch where prdSchCode='"+bmsPrdSch.getPrdSchCode()+"'");
		if(list.size()>0) throw new Exception("期间方案编码已经存在");
		if(bmsPrdSch.getPrdSchCode()==null||bmsPrdSch.getPrdSchCode().trim().length()==0) {
			throw new Exception("期间方案编码不能为空");
		}
		if(bmsPrdSch.getPrdSchName()==null||bmsPrdSch.getPrdSchName().trim().length()==0) {
			throw new Exception("期间方案名称不能为空");
		}
		bmsPrdSch.setPrdSchId(BmsUtils.getID());
		dao.insert(bmsPrdSch);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.PrdSch#queryById(int)
	 */
	public BmsPrdSch queryById(int prdSchId) throws Exception {
		List list = dao.query("from BmsPrdSch where PrdSchID="+prdSchId);
		if(list.size()==0) throw new Exception("未找到指定期间方案");
		
		return (BmsPrdSch)list.get(0);
	}

	/* (non-Javadoc)
	 * @see com.sinosoft.bms.service.bd.PrdSch#update(com.sinosoft.bms.entity.BmsPrdSch)
	 */
	public void update(BmsPrdSch bmsPrdSch) throws Exception {
		List list = dao.query("from BmsPrdSch where prdSchCode='"+bmsPrdSch.getPrdSchCode()+"' and prdSchId<>"+bmsPrdSch.getPrdSchId());
		if(list.size()>0) throw new Exception("期间方案编码已经存在");
		if(bmsPrdSch.getPrdSchCode()==null||bmsPrdSch.getPrdSchCode().trim().length()==0) {
			throw new Exception("期间方案编码不能为空");
		}
		if(bmsPrdSch.getPrdSchName()==null||bmsPrdSch.getPrdSchName().trim().length()==0) {
			throw new Exception("期间方案名称不能为空");
		}		
		dao.update(bmsPrdSch);
	}
	
	

}
