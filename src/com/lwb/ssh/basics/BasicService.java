package com.lwb.ssh.basics;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BasicService implements BasicServiceInter {
	private SessionFactory factory;
	
	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public void addObject(Object object) {
		// TODO Auto-generated method stub
		this.factory.getCurrentSession().save(object);
	}

	public List executeQuery(String hql, Object[] parameter) {
		Query query = this.factory.getCurrentSession().createQuery(hql);
		if(null != parameter && parameter.length>0){
			for(int i = 0;i<parameter.length;i++){
				query.setParameter(i, parameter[i]);
			}
		}
		return query.list();
	}

	public List executeQueryByPage(String hql, Object[] parameter,
			int currentPageIndex, int pageSize) {
		Query query = this.factory.getCurrentSession().createQuery(hql);
		if(null != parameter && parameter.length>0){
			for(int i = 0;i<parameter.length;i++){
				query.setParameter(i, parameter[i]);
			}
		}
		int current = (currentPageIndex-1)*pageSize;
		return query.setFirstResult(current).setMaxResults(pageSize).list();
	}

	public List executeUpdate(String hql, Object[] parameter) {
		
		return null;
	}

	public Object getObjectById(Class className, Serializable id) {
		return factory.getCurrentSession().get(className, id);
	}

	public Object uniqueQuery(String hql, Object[] paras) {
		Query query = this.factory.getCurrentSession().createQuery(hql);
		if(null != paras && paras.length>0){
			for(int i = 0;i<paras.length;i++){
				query.setParameter(i, paras[i]);
			}
		}
		return query.uniqueResult();
	}

	public void updateObject(Object obj){
		factory.getCurrentSession().update(obj);
	}

	public void deleteObject(Object obj){
		factory.getCurrentSession().delete(obj);
	}
}
