package com.lwb.ssh.domain;

import java.util.Date;

public class NewsComment {
	private int id;
	private int newsId;
	private int newsCommenterId;
	private String newsCommentContent;
	private Date newsCommentDate;
	private int newsCommentStatus; // 1表示评论已提交，但是未审核通过，10表示审核通过，2表示审核不通过
	private String newsExtra; // 存放评论者的真实姓名（如果有）或用户名
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}
	public int getNewsCommenterId() {
		return newsCommenterId;
	}
	public void setNewsCommenterId(int newsCommenterId) {
		this.newsCommenterId = newsCommenterId;
	}
	public String getNewsCommentContent() {
		return newsCommentContent;
	}
	public void setNewsCommentContent(String newsCommentContent) {
		this.newsCommentContent = newsCommentContent;
	}
	public Date getNewsCommentDate() {
		return newsCommentDate;
	}
	public void setNewsCommentDate(Date newsCommentDate) {
		this.newsCommentDate = newsCommentDate;
	}
	public int getNewsCommentStatus() {
		return newsCommentStatus;
	}
	public void setNewsCommentStatus(int newsCommentStatus) {
		this.newsCommentStatus = newsCommentStatus;
	}
	public String getNewsExtra() {
		return newsExtra;
	}
	public void setNewsExtra(String newsExtra) {
		this.newsExtra = newsExtra;
	}
}
