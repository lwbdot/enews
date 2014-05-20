package com.lwb.services.interfaces;

import java.util.List;

import com.lwb.ssh.basics.BasicServiceInter;

public interface NewsCommentServiceInter extends BasicServiceInter {
	public List getItems();
	public List getItems(int currentPage,int pageSize);
	public List getItemsByTypeId(int typeId, int currentPage, int pageSize);
	public List getItemsByAttr(String attr, String attrValue, int currentPage, int pageSize);
	public int getItemsCountByAttr(String attr, String attrValue);
	public int getItemsCount(int typeId);
	public int getItemsCountByStatus(int status);
	public Object getCommentByNewsId(int newsId);
}
