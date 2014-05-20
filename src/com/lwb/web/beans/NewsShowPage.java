package com.lwb.web.beans;

import java.util.List;

import com.lwb.ssh.domain.News;
import com.lwb.ssh.domain.NewsComment;
import com.lwb.ssh.domain.NewsDetail;

public class NewsShowPage {
	public int pageSize;
	public int totalPages;
	public int currentPage;
	public List newsList;
	public String newsType;
	public int newsTypeId;

	public News oneNews;
	public NewsDetail detail;
	public String newsWriterName;
	public List<NewsComment> newsComments; // 评论者的名字，都保存在“newsExtra”中
	public int csize; // 评论每一页显示的评论条数
	public int ctotal; // 评论共有多少页
	public int ccurrent; // 当前评论的页码
}
