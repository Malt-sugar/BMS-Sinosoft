/*
 * Created on 2005-4-8
 *
 */
package com.sinosoft.bms.framework;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * @author Administrator
 *
 * 数据访问类接口
 */
public interface DAO {
	public abstract Serializable create(Object obj);

	public abstract Serializable insert(Object obj);
	
	public abstract void update(Object obj);

	public abstract void delete(Object obj);

    public abstract Object load(Class entityClass, Serializable id);

    public abstract Object get(String className, Serializable id);

    public abstract Object get(Class entityClass, Serializable id);
    
	public abstract List query(String hsql);

	public abstract List queryWithJDBC(String sql);

    public abstract List queryWithJDBC(String sql,Class elementType);

	public abstract int queryForInt(String sql);

	public abstract void excute(String sql);

	public abstract Criteria createCriteria(Class entity);

	public abstract void closeSession();

	public abstract HibernateTemplate getHTemplate();

	public abstract void refresh(Object object);
	
	public abstract void evict(Object entity);
	
	public abstract void flush();
	
	public abstract DataSource getDataSource();
}
