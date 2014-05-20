<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.lwb.ssh.domain.UserInfo"%>
<%@page import="com.lwb.web.beans.CommentViewPage"%>
<%@page import="com.lwb.web.beans.CommentsInfo"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
UserInfo user = (UserInfo)request.getSession().getAttribute("user");
if(user.getUserLevel()<1){
String actionItems[][] = {
  {"用户管理",
      "查看所有注册用户"
  },
  {"新闻管理",
      "查看所有发布新闻",
      "查看未审核的评论",
      "更换校园风采图片"
  }
};
String actionLinks[][] = {
  {"#",
      "user.do?flag=viewUsers"
  },
  {"#",
      "news.do?flag=manageNews&newsType=1",
      "news.do?flag=viewComment&newsType=1",
      "addSliders.do?flag=toAdd"
  }
};
boolean actionLinksTarget[][] = {
  {false,
      false
  },
  {false,
      false,
      false,
      false
  }
};

%>
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
<%}%>