package com.lwb.web.beans;

import java.util.ArrayList;

public class CommentViewPage {
	public int currentPage;
	public int totalPage;
	public int pageSize;
	public String pageTitle;

	/**NewsComment的newsExtra字段在数据库中存储评论者的真实姓名，
	 * 取出后再加上所属新闻标题，即该字段在发送到JSP页面时，是下面的格
	 * 式：新闻评论者的真实姓名#所属新闻标题 
	 */
	public ArrayList<CommentsInfo> comments;
	public CommentViewPage(){
		comments = new ArrayList<CommentsInfo>();
	}
}
