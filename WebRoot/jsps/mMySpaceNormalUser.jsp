<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.lwb.ssh.domain.UserInfo"%>
<%@page import="com.lwb.web.beans.CommentViewPage"%>
<%@page import="com.lwb.web.beans.CommentsInfo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
UserInfo user = (UserInfo)request.getSession().getAttribute("user");
String actionItems[][] = {
  {"评论管理",
      "查看我的评论"
  },
};
String actionLinks[][] = {
  {"#",
      "news.do?flag=viewMyComments"
  }
};
boolean actionLinksTarget[][] = {
  {false,
      false
  }
};

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
  lastPageUri = "news.do?flag=viewMyComments&currentPage="+last;
  nextPageUri = "news.do?flag=viewMyComments&currentPage="+next;
}
%>
<div class="body0">
  <div class="fl leftBox" style="width: 20%;">
    <span class="newsTitle"> 网站后台管理 </span><br/>
    <div class="newsListBig">
    <%
      int n3 = actionItems.length;
      int n4 = 0;
      for(int i = 0; i<n3;i++){
    %>
        <span class="newsTitle1"> &nbsp;&nbsp;&gt;&gt; &nbsp;
          <%=actionItems[i][0] %>
        </span>
        <br/>
        <%
          n4 = actionItems[i].length;
          for(int j=1;j<n4;j++){
        %>
        <hr size="1" color="#e1e1e1" width="90%" />
        <span class="newsTitle1" style="margin-left: 40px;">&gt;&nbsp;&nbsp;&nbsp;
          <%if(actionLinksTarget[i][j]){ %>
          <a href="<%=actionLinks[i][j] %>" target="＿blank"> <%=actionItems[i][j] %> </a>
          <%}else{ %>
          <a href="<%=actionLinks[i][j] %>"> <%=actionItems[i][j] %> </a>
          <%} %>
        </span>
        <%} %>
        <hr size="4" color="#0099cc" width="96%" />
        <%} %>
    </div>
  </div>

  <!-- Right page start -->
<%if(hasRightData){ %>
  <div class="fr rightBox" style="width: 70%;">
    <span class="newsTitle"> <%=rightTitle %> </span><br/>
    <div class="newsDetailBox" style="height: auto;overflow: auto;">
      <div>
      <table width="96%" cellpadding="0" cellspacing="0" align="center" border="1" bordercolor="#dedede">
        <tr align="center">
          <!-- <td>编号</td> -->
          <td width="20%">新闻标题</td>
          <td>评论内容</td>
          <td width="15%">评论时间</td>
          <td width="20%">审核意见</td>
        </tr>
      <%
        int n5 = comments.size();
        CommentsInfo nc = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time_s = null;
        for(int i = 0;i<n5;i++){
          nc = comments.get(i);
          time_s = df.format(nc.newsCommentDate);
          int status = nc.newsCommentStatus;
          //String s = (status>5)?"已审核":"未审核";
      %>
        <tr>
          <td><%=nc.newsTitle %></td>
          <td><%=nc.newsCommentContent %></td>
          <td><%=time_s %></td>
          <td align="center">
          <%if(status>5){ %>
            <div style="padding:2px 5px;color:white;width:43%; background: #0099cc;border-radius:5px;font-weight: bold;"> 已审核 </div>
          <%}else{ %>
            <div style="padding:2px 5px;color:black;width:43%; background: #eeeeee;border-radius:5px;font-weight: bold;"> 未审核 </div>
          <%} %>
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