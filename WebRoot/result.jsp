<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Result</title>

  </head>
  
  <body>
    <h1>${result }</h1>
    <a href="<%=basePath %>employee.do?flag=doGetAdd">继续添加</a>
    <a>返回主页</a>
  </body>
</html>
