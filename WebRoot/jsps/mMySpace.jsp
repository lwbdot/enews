<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.lwb.ssh.domain.News"%>
<%@page import="com.lwb.ssh.domain.NewsDetail"%>
<%@page import="com.lwb.ssh.domain.NewsComment"%>
<%@page import="com.lwb.web.beans.NewsShowPage"%>
<%@page import="com.lwb.web.beans.CommentViewPage"%>
<%@page import="com.lwb.web.beans.CommentsInfo"%>
<%
//Below for right page
boolean hasRightData = false;
CommentViewPage cv = (CommentViewPage)request.getAttribute("cv");
int cp = 0;
int ps = 0;
int total = 0;
int last = 0;
int next = 0;
ArrayList<CommentsInfo> comments = null;
String rightTitle = "";
String actionUriPrefix = "news.do?flag=commentAction&act=";
String delAction = "del&currentPage=";// For agree comment
String agreeAction = "agree&currentPage=";// For delete comment
String lastPageUri = null;
String nextPageUri = null;
if(null != cv){
  hasRightData = true;
  cp = cv.currentPage;
  ps = cv.pageSize;
  total = cv.totalPage;
  last = (cp>1)?(cp-1):1;
  next = (cp<total)?(cp+1):total;
  comments = cv.comments;
  rightTitle = cv.pageTitle;
  delAction += cp+"&cmtId=";
  agreeAction += cp+"&cmtId=";
  lastPageUri = "news.do?flag=viewComment&currentPage="+last;
  nextPageUri = "news.do?flag=viewComment&currentPage="+next;
}
%>

<div class="body0">
  <jsp:include page="mMySpaceLeft.jsp"></jsp:include>

<!-- Right page start -->
<%if(hasRightData){ %>
  <div class="fr rightBox" style="width: 70%;">
    <span class="newsTitle"> <%=rightTitle %> </span><br/>
    <div class="newsDetailBox" style="height: auto;overflow: auto;">
      <div>
      <table width="96%" cellpadding="0" cellspacing="0" align="center" border="1" bordercolor="#dedede">
        <tr align="center">
          <!-- <td>编号</td> -->
          <td width="20%">新闻标题</td><td>评论内容</td><td width="12%">评论时间</td>
          <td width="8%">评论人</td><td width="18%">审核意见</td>
        </tr>
      <%
        int n5 = comments.size();
        CommentsInfo nc = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time_s = null;
        for(int i = 0;i<n5;i++){
          nc = comments.get(i);
          time_s = df.format(nc.newsCommentDate);
      %>
        <tr>
          <td><%=nc.newsTitle %></td><td><%=nc.newsCommentContent %></td>
          <td><%=time_s %></td><td><%=nc.newsExtra %></td>
          <td align="center">
            <div style="padding:2px 5px;display:inline;width:43%; background: #0099cc;border-radius:5px;">
              <a href="<%=actionUriPrefix+agreeAction+nc.id %>" style="color: white;">允许</a>
            </div>
            <div style="margin-left:5px;padding:2px 5px;display:inline;width:45%; background: #aaaaaa;border-radius:5px;">
              <a href="<%=actionUriPrefix+delAction+nc.id %>" style="color: black;">删除</a>
            </div>
          </td>
        </tr>
      <%} %>
      </table>
      <hr size="2" width="98%" color="#0099cc"/>
      <div style="width:300px; margin: 15px auto;text-align: center;">
          <a href="<%=lastPageUri %>">
            <span > 上一页 </span>
          </a>
          <span > 【共<%=total %>页，当前第<%=cp %>页】 </span>
          <a href="<%=nextPageUri %>">
            <span > 下一页 </span>
          </a>
        </div>
    </div>
  </div>
  </div>
  <%} %>
  <div class="clearfloat"></div>
</div>