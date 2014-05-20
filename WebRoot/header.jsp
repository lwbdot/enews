<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.lwb.ssh.domain.UserInfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"
    +request.getServerName()+":"
    +request.getServerPort()+path+"/";

// 导航标题名称
String []naviItems = {
    "学院首页",
    "学院新闻",
    "学院通告",
    "最新新闻",
    "登陆注册",
    "发布新闻",
    "后台管理"
};

String naviLinkPrefix = "";
String []naviLink = {
    "index.jsp",
    "news.do?flag=viewNews&newsType=3",
    "news.do?flag=viewNews&newsType=2",
    "news.do?flag=viewNews&newsType=1",
    "user.do?flag=loginOrRegister",
    "addNews.do?flag=gotoAdd",
    "user.do?flag=mySpace"
};

String pageTitle = naviItems[0];
String logoWords = "欢迎访问三亚理工学院网站";

String pageTitleIndex_s = request.getParameter("pt");
if(null == pageTitleIndex_s){
    pageTitleIndex_s = (String)request.getAttribute("pt");
}
if (null != pageTitleIndex_s){
    try{
       int pti = Integer.parseInt(pageTitleIndex_s);
       pageTitle = naviItems[pti];
    }catch(Exception e){
        pageTitle = naviItems[0];
    }
}

String searchLink = "searchNews.do";
//String loginLink = "";
//String registerLink = "";
String kw = (String)request.getAttribute("kw");
if(null == kw){
  kw = "输入搜索关键字";
}

UserInfo user = (UserInfo)request.getSession().getAttribute("user");
%>
<!DOCTYPE html>
<html>
  <head>    
    <title><%=pageTitle %></title>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <!--  
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content=""/>

	<link rel="stylesheet" type="text/css" href="css/en/basic.css"/>
	<link rel="stylesheet" type="text/css" href="css/en/header.css"/>
	<script type="text/javascript">
	  function clearContent(){
	    var ti = document.getElementById("ti");
	    if(ti.value == 0){
	      document.getElementById("kw").value = "";
	      ti.value = 1;
	    }
	  }

	  function checkKw(){
        var kw = document.getElementById("kw").value;
        if(kw == ""){
          return false;
        }
        return true;
      }
	</script>
  </head>
  <body>
    <div class="header">
      <div class="header1">
        <div class="header1a1">
          <div class="logopic fl">
<a href="<%=naviLinkPrefix+naviLink[0] %>">
  <img width="196px" height="90px" src="images/header/logo.png"/> 
</a>
          </div>
          <div class="logotext fl">
            <span><%=logoWords %></span>
          </div>
          <div class="searchBox fr">
<form action="<%=searchLink %>" method="post">
  <span class="fl"> 新闻搜索 </span>
  <input type="hidden" name="flag" value="searchResult"/>
  <input type="hidden" id="ti" name="ti" value="0"/>
  <input id="kw" name="kw" class="input1 fl" value="<%=kw %>" onclick="clearContent();"/>
  <input class="searchBtn fr" type="submit" onclick="return checkKw();" value="  ">
</form>
          </div>
        </div>
      </div>
      <div class="clearfloat"></div>
      <div class="header2">
        <div class="navi">
          <ul>
            <%
              int naviCount = naviItems.length;
              String link = "";
              for(int i = 0; i < naviCount; i++){
                link = naviLinkPrefix + naviLink[i];
                if(i==0){
            %>
            <li style="border-left: none;margin-left: 30px">
            <%  }else if(i==(naviCount-1)){ %>
            <li style="border-right: none;">
            <%  }else{%>
            <li>
            <%} %>
              <div style="width: 100px; height: 49px; display: inline;">
                <span><a href="<%=link %>"> <%=naviItems[i] %> </a></span>
              </div>
            </li>
            <%} %>
          </ul>
        </div>
      </div>
    </div>
    <div class="clearfloat"></div>
    <div style="width: 100%;height: 50px; background: #0099cc;">
      <div style="width: 980px;margin: 0 auto;">
      <%if(null != user){ %>
        <a href="user.do?flag=logout">
        <span class="fr welcome1">退出登录</span>
        </a>
        <span class="fr welcome1"> &nbsp;&nbsp;&nbsp;&nbsp; </span>
        <span class="fr welcome1">欢迎你，<%=user.getUserRealName() %>!</span>
      <%} %>
      </div>
    </div>
