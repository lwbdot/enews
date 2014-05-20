package com.lwb.ssh.basics;

import java.io.Serializable;
import java.util.List;

public interface BasicServiceInter {
	// 1. 单个记录查询
	public Object getObjectById(Class className, Serializable id);

	// 2. 根据hql查询
	public List executeQuery(String hql, Object []parameter);

	// 3. 分页查询
	public List executeQueryByPage(String hql,Object []parameter, int currentPageIndex,int pageSize);

	// 4. 添加一条记录
	public void addObject(Object object);

	public void updateObject(Object object);

	public void deleteObject(Object obj);

	// 5. 统一执行hql语句：删除、修改
	public List executeUpdate(String hql, Object []parameter);

	// 6.
	public Object uniqueQuery(String hql,Object []paras);
}
