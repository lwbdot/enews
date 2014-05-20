package com.lwb.services.implementts;

import java.util.List;

import com.lwb.services.interfaces.NewsServiceInter;
import com.lwb.ssh.basics.BasicService;
import com.lwb.ssh.domain.News;

public class NewsService extends BasicService implements NewsServiceInter {
	private String tb_name = "News";

	public List getItems(int currentPage,int pageSize){
		String hql = "from "+tb_name;
		return executeQueryByPage(hql,null,currentPage,pageSize);
	}

	public int getItemsCount(int typeId){
		String hql = "select count(*) from "+tb_name+" where newsTypeId="+typeId;
		if (typeId == 1) {
			hql = "select count(*) from "+tb_name;
		}
		Object count = this.uniqueQuery(hql, null);
		return new Integer(count.toString());
	}

	public List getItems(){
		String hql = "from "+tb_name+" order by id";
		return executeQuery(hql, null);
	}

	public List getItemsByTypeId(int typeId, int currentPage, int pageSize) {
		String hql = "from "+tb_name+" where newsTypeId="+typeId+" order by newsPublishDate desc";
		if (typeId < 2) {
			hql = "from "+tb_name+" order by newsPublishDate desc";
		}
		return executeQueryByPage(hql,null,currentPage,pageSize);
	}

	public void updateItem(String attr,String newValue){
		String hql = "update "+tb_name+" set "+attr+"='"+newValue+"' where newsExtra='-1'";
		executeQuery(hql, null);
	}

	public Object getAnItem(String attr, String value){
		String hql = "from "+tb_name+" where "+attr+"='"+value+"'";
		return uniqueQuery(hql, null);
	}

	public List searchItem(String keyword, int typeId, int currentPage, int pageSize) {
		String hql = "from "+tb_name+" where newsTitle like '%"+keyword+"%' order by newsPublishDate desc";
		//if (typeId>1) {
			//hql = "from "+tb_name+" order by newsPublishDate desc";
		//	hql = "from "+tb_name+" where newsTypeId="+typeId+" order by newsPublishDate desc";
		//}
		return executeQueryByPage(hql,null,currentPage,pageSize);
	}

	public int searchItemCount(String keyword) {
		String hql = "select count(*) from "+tb_name+" where newsTitle like '%"+keyword+"%'";
		Object count = this.uniqueQuery(hql, null);
		return new Integer(count.toString());
	}
}
