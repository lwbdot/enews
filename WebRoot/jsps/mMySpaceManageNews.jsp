<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.lwb.ssh.domain.News"%>
<%@page import="com.lwb.ssh.domain.NewsDetail"%>
<%@page import="com.lwb.ssh.domain.NewsComment"%>
<%@page import="com.lwb.web.beans.NewsShowPage"%>
<%@page import="com.lwb.web.beans.CommentViewPage"%>
<%@page import="com.lwb.web.beans.CommentsInfo"%>
<%@page import="com.lwb.ssh.domain.UserInfo"%>
<%@page import="com.lwb.web.beans.UsersList"%>
<%@page import="com.lwb.web.beans.NewsInfoPage"%>
<%@page import="com.lwb.web.beans.NewsInfo"%>
<%
//Below for right page
boolean hasRightData = false;
int cp = 0;
int ps = 0;
int total = 0;
int last = 0;
int next = 0;
NewsInfoPage ni = (NewsInfoPage)request.getAttribute("ni");
List<NewsInfo> items = null;
String rightTitle = "浏览网站所有新闻";
String delActionUri = "news.do?flag=deleteNews&currentPage=";
String lastPageUri = null;
String nextPageUri = null;
if(null != ni){
  hasRightData = true;
  cp = ni.currentPage;
  ps = ni.pageSize;
  total = ni.totalPage;
  last = (cp>1)?(cp-1):1;
  next = (cp<total)?(cp+1):total;
  items = ni.newsList;
  delActionUri += cp+"&newsId=";
  lastPageUri = "news.do?flag=manageNews&currentPage="+last;
  nextPageUri = "news.do?flag=manageNews&currentPage="+next;
}
%>

<div class="body0">
  <jsp:include page="mMySpaceLeft.jsp"></jsp:include>

<!-- Right page start -->
<%if(hasRightData){ %>

  <div class="fr rightBox" style="width: 70%;">
  <script type="text/javascript">
    function conDel(){
      if(confirm("删除新闻将删除该新闻的所有内容，包括对该新闻的所有评论。确定要删除吗？")){
         return true;
      }
      return false;
    }
  </script>
    <span class="newsTitle"> <%=rightTitle %> </span><br/>
    <div class="newsDetailBox" style="height: auto;overflow: auto;">
      <div>
      <table width="96%" cellpadding="2" cellspacing="0" align="center" border="1" bordercolor="#dedede">
        <tr align="center">
          <!-- <td>编号</td> -->
          <td width="15%">新闻名称</td><td width="13%">新闻类别</td>
          <td width="13%">发布者</td><td>阅读次数</td><td width="23%">发布时间</td>
          <td width="13%">操作</td>
        </tr>
      <%
        int n5 = items.size();
        NewsInfo nc = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time_s = null;
        for(int i = 0;i<n5;i++){
          nc = items.get(i);
          time_s = df.format(nc.newsPublishDate);
      %>
        <tr>
          <td><%=nc.newsTitle %></td><td><%=nc.newsClassify %></td>
          <td><%=nc.newsWriterRealName %></td>
          <td><%=nc.readTimes %></td><td><%=time_s %></td>
          <td align="center">
            <div style="padding:2px 5px;display:inline;width:43%; background: #0099cc;border-radius:5px;">
              <a href="<%=delActionUri+nc.newsId %>" style="color: white;" onclick="return conDel();">删除</a>
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