package com.lwb.services.implementts;

import java.util.List;

import com.lwb.services.interfaces.NewsCommentServiceInter;
import com.lwb.ssh.basics.BasicService;

public class NewsCommentService extends BasicService implements NewsCommentServiceInter{
	private String tb_name = "NewsComment";

	public List getItems(int currentPage,int pageSize){
		String hql = "from "+tb_name;
		return executeQueryByPage(hql,null,currentPage,pageSize);
	}
	
	public int getItemsCount(int typeId){
		String hql = "select count(*) from "+tb_name+" where newsId="+typeId+" and newsCommentStatus>9";
		Object count = this.uniqueQuery(hql, null);
		return new Integer(count.toString());
	}
	
	public int getItemsCountByStatus(int status){
		String hql = "select count(*) from "+tb_name+" where newsCommentStatus="+status;
		Object count = this.uniqueQuery(hql, null);
		return new Integer(count.toString());
	}
	
	public List getItems(){
		String hql = "from "+tb_name+" order by id";
		return executeQuery(hql, null);
	}
	
	public List getItemsByTypeId(int typeId, int currentPage, int pageSize){
		String hql = "from "+tb_name+" where newsId="+typeId+" and newsCommentStatus>1 order by newsCommentDate desc";
		//if (typeId < 2) {
		//	hql = "from "+tb_name+" order by newsCommentDate desc";
		//}
		return executeQueryByPage(hql,null,currentPage,pageSize);
	}

	public Object getCommentByNewsId(int newsId) {
		String hql = "from "+tb_name+" where newsId='"+newsId+"'";
		return uniqueQuery(hql, null);
	}

	public List getItemsByAttr(String attr, String attrValue, int currentPage,
			int pageSize) {
		String hql = "from "+tb_name+" where "+attr+"='"+attrValue+"' order by newsCommentDate desc";
		
		return executeQueryByPage(hql,null,currentPage,pageSize);
	}

	public int getItemsCountByAttr(String attr, String attrValue) {
		String hql = "select count(*) from "+tb_name+" where "+attr+"='"+attrValue+"'";
		Object count = this.uniqueQuery(hql, null);
		return new Integer(count.toString());
	}
}
