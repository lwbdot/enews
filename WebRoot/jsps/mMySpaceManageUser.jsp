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
<%
//Below for right page
boolean hasRightData = false;
int cp = 0;
int ps = 0;
int total = 0;
int last = 0;
int next = 0;
UsersList ul = (UsersList)request.getAttribute("ul");
List<UserInfo> users = null;
String rightTitle = "当前注册用户";
String delActionUri = "user.do?flag=deleteUser&currentPage=";
String lastPageUri = null;
String nextPageUri = null;
if(null != ul){
  hasRightData = true;
  cp = ul.currentPage;
  ps = ul.pageSize;
  total = ul.totalPage;
  last = (cp>1)?(cp-1):1;
  next = (cp<total)?(cp+1):total;
  users = ul.users;
  //rightTitle = cv.pageTitle;
  delActionUri += cp+"&userId=";
  lastPageUri = "user.do?flag=viewUsers&currentPage="+last;
  nextPageUri = "user.do?flag=viewUsers&currentPage="+next;
}
String userType[] ={
  "管理员",
  "老师",
  "学生",
  "普通用户",
};
%>

<div class="body0">
  <jsp:include page="mMySpaceLeft.jsp"></jsp:include>

<!-- Right page start -->
<%if(hasRightData){ %>
  <div class="fr rightBox" style="width: 70%;">
    <span class="newsTitle"> <%=rightTitle %> </span><br/>
    <div class="newsDetailBox" style="height: auto;overflow: auto;">
      <div>
      <table width="96%" cellpadding="2" cellspacing="0" align="center" border="1" bordercolor="#dedede">
        <tr align="center">
          <!-- <td>编号</td> -->
          <td width="15%">用户名</td><td width="13%">真实姓名</td>
          <td width="13%">用户类别</td><td>电子邮件</td><td width="23%">注册时间</td>
          <td width="13%">操作</td>
        </tr>
      <%
        int n5 = users.size();
        UserInfo nc = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time_s = null;
        for(int i = 0;i<n5;i++){
          nc = users.get(i);
          time_s = df.format(nc.getUserRigisterDate());
      %>
        <tr>
          <td><%=nc.getUserName() %></td><td><%=nc.getUserRealName() %></td>
          <td><%=userType[nc.getUserLevel()] %></td>
          <td><%=nc.getUserEmail() %></td><td><%=time_s %></td>
          <td align="center">
            <div style="padding:2px 5px;display:inline;width:43%; background: #0099cc;border-radius:5px;">
              <a href="<%=delActionUri+nc.getId() %>" style="color: white;">删除</a>
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