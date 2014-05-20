package com.lwb.services.implementts;

import java.util.List;

import com.lwb.services.interfaces.NewsDetailServiceInter;
import com.lwb.ssh.basics.BasicService;
import com.lwb.ssh.domain.NewsDetail;

public class NewsDetailService extends BasicService implements NewsDetailServiceInter{
	private String tb_name = "NewsDetail";

	public List getItems(int currentPage,int pageSize){
		String hql = "from "+tb_name;
		return executeQueryByPage(hql,null,currentPage,pageSize);
	}
	public int getItemsCount(){
		String hql = "select count(*) from "+tb_name;
		Object count = this.uniqueQuery(hql, null);
		return new Integer(count.toString());
	}
	public List getItems(){
		String hql = "from "+tb_name+" order by id";
		return executeQuery(hql, null);
	}
	public Object getDetailByNewsId(int newsId) {
		String hql = "from "+tb_name+" where newsId='"+newsId+"'";
		return uniqueQuery(hql, null);
	}
}
