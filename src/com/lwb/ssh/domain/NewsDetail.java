package com.lwb.ssh.domain;

public class NewsDetail {
	private int id;
	private int newsId;
	private int newsWriterId;
	private String newsClassify;
	private int newsReadTimes;
	private int newsFlag; // 标志，1表示有评论，2表示有图片附件，3表示有非图片附件
	private String newsPicPath; // 0表示无图片，其他值表示图片的路径，多张图片用#号分隔
	private String newsAttachPath; // 和nPicPath一样，只是这里是非图片附件的路径
	private String newsContent;
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
	public int getNewsWriterId() {
		return newsWriterId;
	}
	public void setNewsWriterId(int newsWriterId) {
		this.newsWriterId = newsWriterId;
	}
	public String getNewsClassify() {
		return newsClassify;
	}
	public void setNewsClassify(String newsClassify) {
		this.newsClassify = newsClassify;
	}
	public int getNewsReadTimes() {
		return newsReadTimes;
	}
	public void setNewsReadTimes(int newsReadTimes) {
		this.newsReadTimes = newsReadTimes;
	}
	public int getNewsFlag() {
		return newsFlag;
	}
	public void setNewsFlag(int newsFlag) {
		this.newsFlag = newsFlag;
	}
	public String getNewsPicPath() {
		return newsPicPath;
	}
	public void setNewsPicPath(String newsPicPath) {
		this.newsPicPath = newsPicPath;
	}
	public String getNewsAttachPath() {
		return newsAttachPath;
	}
	public void setNewsAttachPath(String newsAttachPath) {
		this.newsAttachPath = newsAttachPath;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
}
