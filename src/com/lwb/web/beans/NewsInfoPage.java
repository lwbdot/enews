package com.lwb.web.beans;

import java.util.ArrayList;

public class NewsInfoPage {
	public int currentPage;
	public int totalPage;
	public int pageSize;

	public ArrayList<NewsInfo> newsList;
	public NewsInfoPage(){
		newsList = new ArrayList<NewsInfo>();
	}
}
