<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.lwb.ssh.domain.News"%>
<%@page import="com.lwb.ssh.domain.NewsDetail"%>
<%@page import="com.lwb.ssh.domain.NewsComment"%>
<%@page import="com.lwb.web.beans.NewsShowPage"%>
<%
NewsShowPage nsp = (NewsShowPage)request.getAttribute("newsShow");

int currentPage = nsp.currentPage;
int totalPages = nsp.totalPages;
int newsCount = 20;
int maxShowChars = 20;

int nextPage = nsp.totalPages;
int lastPage = 1;
List news = nsp.newsList;
if(currentPage > 1){
  lastPage = currentPage-1;
}
if(currentPage<totalPages){
  nextPage = currentPage+1;
}

String newsType = nsp.newsType;
boolean showDetail = false;
NewsDetail detail = nsp.detail;
News oneNews = nsp.oneNews;
if(null != detail){
  showDetail = true;
}

int cmtc = 0;
int cmts = 0;
int cmtt = 0;
int cnextPage = nsp.ctotal;
int clastPage = 1;
boolean showComments = false;
List<NewsComment> comments = nsp.newsComments;
if((null != comments)&&(comments.size()>0)){
  showComments = true;
  cmtc = nsp.ccurrent;
  cmts = nsp.csize;
  cmtt = nsp.ctotal;
  if(cmtc > 1){
    clastPage = cmtc-1;
  }
  if(cmtc<nsp.ctotal){
    cnextPage = cmtc+1;
  }
}
int newsTypeId = nsp.newsTypeId;

String newsLast = "searchNews.do?flag=searchResult&newsType="+newsTypeId+"&currentPage="+lastPage;// 新闻上页
String newsNext = "searchNews.do?flag=searchResult&newsType="+newsTypeId+"&currentPage="+nextPage; // 新闻下页
String commLast = "searchNews.do?flag=searchResult&newsType="+newsTypeId+"&currentPage="+nextPage+"&cc="+clastPage+"&newsId="; // 评论上页
String commNext = "searchNews.do?flag=searchResult&newsType="+newsTypeId+"&currentPage="+nextPage+"&cc="+cnextPage+"&newsId="; // 评论下页
String newsLinkPrefix = "searchNews.do?flag=searchResult&newsType="+newsTypeId+"&currentPage="+currentPage+"&newsId=";

String date_s = "";
SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//String linkToPost = "onclick='return linkclick(this)'";
String linkToPost = "return linkclick(this)";
%>
<script type="text/javascript">
  function linkclick(linkObject){
    var formObject = document.createElement("form");
    document.body.appendChild(formObject);
    formObject.setAttribute("method", "post");
    var url = linkObject.href;
    var uri = "";
    var i = url.indexOf("?");
    if(i==-1){
        formObject.action = url;
    }else{
        formObject.action = url.substring(0,i);
    }
    if(i>=0 && url.length >= i+1){
        uri = url.substring(i+1,url.length);
    }
    
    var sa = uri.split("&");
    for(var i=0;i<sa.length;i++){
        var isa = sa[i].split("=");
        var inputObject = document.createElement("input");
        inputObject.setAttribute("type", "hidden");
        inputObject.setAttribute("name", isa[0]);
        inputObject.setAttribute("value", isa[1]);
        formObject.appendChild(inputObject);
    }
    
    var isa = sa[sa.length-1].split("=");
    var inputObject = document.createElement("input");
    inputObject.setAttribute("type", "hidden");
    inputObject.setAttribute("name", isa[0]);
    inputObject.setAttribute("value", isa[1]);
    formObject.appendChild(inputObject);
    
    var keyword = document.getElementById("kw").value;
    inputObject.setAttribute("type", "hidden");
    inputObject.setAttribute("name", "kw");
    inputObject.setAttribute("value", keyword);
    formObject.appendChild(inputObject);
    
    formObject.submit();
    return false;
}
</script>
<div class="body0">
  <div class="fl leftBox">
    <span class="newsTitle"> 搜索结果： </span><br/>
    <div class="newsListBig">
      <%if((null == news)||(news.size()<1)){ %>
      <span class="newsTitle1">没有搜索到任何新闻消息！</span>
      <%}else{
        String title = "";
        //Date date = null;
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //String date_s = "";

        int i= 0,n = news.size();
        if(n>newsCount){
          n = newsCount;
        }
        News one = null;
        for(i=0;i<n; i++){
          one = (News)news.get(i);
          title = one.getNewsTitle();
          if(title.length()>maxShowChars){
            title = title.substring(0,maxShowChars);
          }
          //date = one.getNewsPublishDate();
          //date_s = format.format(date);
      %>
        <span class="newsTitle1"> &nbsp;&nbsp;&gt;&gt; &nbsp;
          <a onclick="<%=linkToPost %>" href="<%=newsLinkPrefix+one.getId() %>"> <%=title %> </a>
        </span>
        <br/>
        <hr size="1" color="#0000ff" width="96%" />
      <%}%>
        <div style="width:300px; margin: 15px auto;text-align: center;">
          <a onclick="return linkclick(this)" href="<%=newsLast %>">
            <span > 上一页 </span>
          </a>
          <span > 【共<%=totalPages %>页，当前第<%=currentPage %>页】 </span>
          <a onclick="return linkclick(this)" href="<%=newsNext %>">
            <span > 下一页 </span>
          </a>
        </div>
        <hr size="3" color="#0099cc" width="96%" />
      <%} %>
    </div>
  </div>
  <%if(showDetail){%>
  <div class="fr rightBox">
    <span class="newsTitle"> 新闻内容 </span><br/>
    <div class="newsDetailBox">
      <div class="newsContent">
      <div class="title0">
        <span> <%=oneNews.getNewsTitle() %> </span>
      </div>
      <div class="c1">
        <%
          date_s = df.format(oneNews.getNewsPublishDate());
        %>
        <span> 发布时间：<%=date_s %> &nbsp;&nbsp;&nbsp;</span>
        <span> 发布人：<%=nsp.newsWriterName %> &nbsp;&nbsp;&nbsp;</span>
        <span> 阅读次数：<%=detail.getNewsReadTimes() %> </span>
      </div>
      <hr size="2" width="98%" color="#0099cc"/>
      <div class="c2">
        <p><%=detail.getNewsContent() %></p>
      </div>
      <hr size="3" width="98%" color="#0099cc"/>
      <span class="newsTitle"> 新闻评论 </span><br/>
      <hr size="1" width="98%" color="#dddddd"/>
      <div class="newsComment">
      <%
        if(showComments){
          int n1 = comments.size();
          if(n1>cmts){
            n1 = cmts;
          }
          NewsComment nc = null;
          for(int k = 0;k<n1;k++){
            nc = comments.get(k);
      %>
        <div class="newsCommentBox">
          <div class="c3">
            <span>评论者：<%=nc.getNewsExtra() %>&nbsp;&nbsp;&nbsp;</span>
            <span>评论时间：<%=df.format(nc.getNewsCommentDate()) %></span>
          </div>
          <div class="c4">
            <p><%=nc.getNewsCommentContent() %></p>
          </div>
        </div>
        <hr size="1" width="98%" color="#dddddd"/>
        <%} %>
        <div style="width:300px; margin: 15px auto;text-align: center;">
          <a onclick="return linkclick(this)" href="<%=commLast+oneNews.getId() %>">
            <span > 上一页 </span>
          </a>
          <span > 【共<%=cmtt %>页，当前第<%=cmtc %>页】 </span>
          <a onclick="return linkclick(this)" href="<%=commNext+oneNews.getId() %>">
            <span > 下一页 </span>
          </a>
        </div>
      <%}else{ %>
        <div class="c4">
          <span>暂无评论，你可以在下面发表评论。<br/>发表评论时，请先<a href="user.do?flag=loginOrRegister">【登录或注册】</a></span>
        </div>
      <%} %>
      </div>
      <hr size="2" width="98%" color="#0099cc"/>
      <script type="text/javascript">
        function checkData(){
          var v = document.getElementById("cmt").value;
          if(v == ""){
            alert("请输入评论内容，再提交。");
            return false;
          }
          if(confirm("你的评论需要管理员审核后才会显示，确定要提交评论吗？")){
            return true;
          }
          return false;
        }
      </script>
      <div class="c5">
        <span class="newsTitle" style="text-align:center;width: 80%;margin: 0 auto;"> 发表评论 </span><br/>
        <form action="news.do" method="post" target="_blank">
          <textarea id="cmt" name="cmt" rows="8" cols="60"></textarea>
          <input type="hidden" name="flag" value="addComment"/>
          <input type="hidden" name="newsId" value="<%=oneNews.getId() %>"/>
          <input class="fr submitBtn" type="submit" value="提交评论" onclick="return checkData();"/>
        </form>
      </div>
    </div>
  </div>
  </div>
  <%} %>
  <div class="clearfloat"></div>
</div>