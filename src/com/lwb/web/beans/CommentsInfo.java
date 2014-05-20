package com.lwb.web.beans;

import java.util.Date;

public class CommentsInfo {
	public int id;
	public int newsId;
	public int newsCommenterId;
	public String newsCommentContent;
	public Date newsCommentDate;
	public int newsCommentStatus; // 1表示评论已提交，但是未审核通过，10表示审核通过，2表示审核不通过
	public String newsExtra; // 存放评论者的真实姓名（如果有）或用户名
	public String newsTitle;
}
