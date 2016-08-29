/*
 * Created on 2005-4-8
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.sinosoft.bms.framework;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Administrator
 * 
 * 数据访问实现类
 */
public class BaseDAO extends HibernateDaoSupport implements DAO {

	protected Session session;

	protected DataSource dataSource; // dataSource用于直接jdbc查询。

	public Serializable create(Object obj) {
		return getHibernateTemplate().save(obj);
	}
	
	

	public void update(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	public void delete(Object obj) {
		getHibernateTemplate().delete(obj);
	}

	public Object load(Class entityClass, Serializable id) {
		Object obj = null;
		try {
			obj = getHibernateTemplate().load(entityClass, id);
		} catch (Exception e) {
		}
		return obj;
	}

	public Object get(String className, Serializable id) {
		Object obj = null;
		try {
			obj = getHibernateTemplate().get(className, id);
		} catch (Exception e) {
		}
		return obj;
	}

	public Object get(Class entityClass, Serializable id) {
		Object obj = null;
		try {
			obj = getHibernateTemplate().get(entityClass, id);
		} catch (Exception e) {
		}
		return obj;
	}

	public void evict(Object entity) {
		getHibernateTemplate().evict(entity);
	}

	public List query(String hsql) {
		return getHibernateTemplate().find(hsql);
	}

	public List queryWithJDBC(String sql) {
		JdbcTemplate jTemplate = new JdbcTemplate(getDataSource());
		return jTemplate.queryForList(sql);
	}

	public List queryWithJDBC(String sql, Class elementType) {
		JdbcTemplate jTemplate = new JdbcTemplate(getDataSource());
		return jTemplate.queryForList(sql, elementType);
	}

	/**
	 * 用于执行select count(*)方法
	 */
	public int queryForInt(String sql) {
		JdbcTemplate jTemplate = new JdbcTemplate(getDataSource());
		return jTemplate.queryForInt(sql);
	}

	/**
	 * 用于执行delete方法
	 */
	public void excute(String sql) {
		JdbcTemplate jTemplate = new JdbcTemplate(getDataSource());
		jTemplate.execute(sql);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Criteria createCriteria(Class entity) {
		session = getSession(true);
		return session.createCriteria(entity);
	}

	public void closeSession() {
		releaseSession(session);
	}

	public HibernateTemplate getHTemplate() {
		return getHibernateTemplate();
	}

	public void refresh(Object obj) {
		getHibernateTemplate().refresh(obj);
	}

	public void flush() {
		getHibernateTemplate().flush();
	}

	public Serializable insert(Object obj) {
		return getHibernateTemplate().save(obj);
	}
}
