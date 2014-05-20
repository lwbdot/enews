package com.lwb.services.interfaces;

import java.util.List;

import com.lwb.ssh.basics.BasicServiceInter;

public interface NewsDetailServiceInter extends BasicServiceInter {
	public List getItems(int currentPage,int pageSize);
	public int getItemsCount();
	public List getItems();
	public Object getDetailByNewsId(int newsId);
}
