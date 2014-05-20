<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.lwb.web.beans.HomePage"%>
<%@page import="com.lwb.ssh.domain.News"%>
<%@page import="java.text.SimpleDateFormat"%>

<jsp:include page="./header.jsp"></jsp:include>
<div class="clearfloat"></div>
<link rel="stylesheet" type="text/css" href="css/en/home.css">
<link href="css/jquery.bxslider.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script src="js/jquery.bxslider.min.js"></script>
<script type="text/javascript">
  $(document).ready(function($) {
    $('.bxslider').bxSlider({
      auto: true
    });
  });
</script>
<%
  String sliderAddr[] = {
    "images/slides/s1.jpg",
    "images/slides/s2.jpg",
    "images/slides/s3.jpg",
    "images/slides/s4.jpg",
  };
  String []sliderAddr_s = (String[])request.getAttribute("sa");
  if(null != sliderAddr_s){
    if(sliderAddr_s.length>=1){
      sliderAddr = sliderAddr_s;
    }
  }

  int latestNewsCount = 7;
  int collegeNewsCount = 10;
  int collegeNoticeCount = collegeNewsCount;
  HomePage hp = (HomePage)request.getAttribute("homePage");
  List latestNews = hp.latestNews;
  List collegeNews = hp.collegeNews;
  List collegeNotice = hp.collegeNotice;

  String moreNewsLink[] = {
    "news.do?flag=viewNews&newsType=1",
    "news.do?flag=viewNews&newsType=2",
    "news.do?flag=viewNews&newsType=3"
  };
%>
<div class="bodys">
  <div class="body0">
    <div class="slidesArea fl">
      <div class="body1">
        <span class="newsTitle"> 校园风采 </span><br/>
      </div>
      <ul class="bxslider">
        <% 
          for(String s:sliderAddr){
        %>
        <li><img width="550px" height="300px" src="<%=s %>"/></li>
        <%} %>
      </ul>
    </div>
    <div class="body1 newsBoxRight fr">
      <span class="newsTitle"> 最新新闻 </span>
      <a href="<%=moreNewsLink[0] %>">
        <span class="newsMore fr"> 更多 &gt; </span>
      </a>
      <br/>
      <!--  
      <hr size="1" color="#dddddd" width="100%"/>
      -->
      <div class="newsListBox">
      <%
        String title = "";
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date_s = "";

        int i= 0,n = latestNews.size();
        if(n>latestNewsCount){
          n = latestNewsCount;
        }
        News news1 = null;
        for(i=0;i<n; i++){
          news1 = (News)latestNews.get(i);
          title = news1.getNewsTitle();
          if(title.length()>20){
            title = title.substring(0,20);
          }
          date = news1.getNewsPublishDate();
          date_s = format.format(date);
      %>
        <span class="newsTitle1"> &nbsp;&nbsp;&gt;&gt; &nbsp;
          <a href="news.do?flag=viewNews&newsType=1&newsId=<%=news1.getId() %>"> <%=title %> </a>
        </span>
        <span class="newsDate fr"> <%=date_s %> </span>
        <br/>
      <%//if(i!=(n-1)){ %>
        <hr size="1" color="#0000ff" width="96%" />
      <%}//} %>
      </div>
    </div>
    <div class="clearfloat"></div>
    <hr size="2" color="#0000ff" width="96%" />
    <div class="body2">
      <div class="newsBox fl">
        <span class="newsTitle"> 学院公告 </span>
        <a href="<%=moreNewsLink[1] %>">
          <span class="newsMore fr"> 更多 &gt; </span>
        </a>
        <br/>
        <div class="newsListBox1">
        <%
          n = collegeNotice.size();
          if(n>collegeNoticeCount){
            n = collegeNoticeCount;
          }
          for(i=0;i<n; i++){
            news1 = (News)collegeNotice.get(i);
            title = news1.getNewsTitle();
            if(title.length()>20){
              title = title.substring(0,20);
            }
            date = news1.getNewsPublishDate();
            date_s = format.format(date);
        %>
          <span class="newsTitle1"> &nbsp;&nbsp;&gt;&gt; &nbsp;
            <a href="news.do?flag=viewNews&newsType=2&newsId=<%=news1.getId() %>"> <%=title %> </a>
          </span>
          <span class="newsDate fr"> <%=date_s %> </span>
          <br/>
          <hr size="1" color="#0000ff" width="96%" />
        <%}%>
        </div>
      </div>
      <div class="newsBox fr">
        <span class="newsTitle"> 学院新闻 </span>
        <a href="<%=moreNewsLink[2] %>">
          <span class="newsMore fr"> 更多 &gt; </span>
        </a>
        <br/>
        <div class="newsListBox1">
        <%
          n = collegeNews.size();
          if(n>collegeNoticeCount){
            n = collegeNoticeCount;
          }
          for(i=0;i<n; i++){
            news1 = (News)collegeNews.get(i);
            title = news1.getNewsTitle();
            if(title.length()>20){
              title = title.substring(0,20);
            }
            date = news1.getNewsPublishDate();
            date_s = format.format(date);
        %>
          <span class="newsTitle1"> &nbsp;&nbsp;&gt;&gt; &nbsp;
            <a href="news.do?flag=viewNews&newsType=3&newsId=<%=news1.getId() %>"> <%=title %> </a>
          </span>
          <span class="newsDate fr"> <%=date_s %> </span>
          <br/>
          <hr size="1" color="#0000ff" width="96%" />
        <%}%>
        </div>
      </div>
    </div>
    <div class="clearfloat"></div>
  </div>
</div>
<jsp:include page="./tail.jsp"></jsp:include>