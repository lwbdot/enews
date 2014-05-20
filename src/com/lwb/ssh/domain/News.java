package com.lwb.ssh.domain;

import java.util.Date;

public class News {
	private int id;
	private String newsTitle;
	private Date newsPublishDate;
	private Date newsWrittenDate;
	private String newsExtra;
	private int newsTypeId;
	public int getNewsTypeId() {
		return newsTypeId;
	}
	public void setNewsTypeId(int newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public Date getNewsPublishDate() {
		return newsPublishDate;
	}
	public void setNewsPublishDate(Date newsPublishDate) {
		this.newsPublishDate = newsPublishDate;
	}
	public Date getNewsWrittenDate() {
		return newsWrittenDate;
	}
	public void setNewsWrittenDate(Date newsWrittenDate) {
		this.newsWrittenDate = newsWrittenDate;
	}
	public String getNewsExtra() {
		return newsExtra;
	}
	public void setNewsExtra(String newsExtra) {
		this.newsExtra = newsExtra;
	}
}
