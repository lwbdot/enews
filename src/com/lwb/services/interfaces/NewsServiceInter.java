package com.lwb.services.interfaces;

import java.util.List;

import com.lwb.ssh.basics.BasicServiceInter;

public interface NewsServiceInter extends BasicServiceInter {
	public List getItems();
	public List getItems(int currentPage, int pageSize);
	public List getItemsByTypeId(int typeId, int currentPage, int pageSize);
	public int getItemsCount(int typeId);
	public Object getAnItem(String attr, String value);
	public void updateItem(String attr,String newValue);
	public List searchItem(String keyword, int typeId, int currentPage, int pageSize);
	public int searchItemCount(String keyword);
}
