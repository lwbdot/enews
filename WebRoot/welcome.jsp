<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Welcome</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>Welcome ${employee.name},Please choose a action.</h1>
    <h2>You are from ${employee.department.name}.</h2>
    <ul>
      <li><a href="<%=basePath %>employee.do?flag=doGetAdd">Add Employee</a></li>
      <li><a href="<%=basePath %>employee.do?flag=doGetShow">Show Employees</a></li>
      <li><a href="#">Update Employee</a></li>
      <li><a href="#">Delete Employee</a></li>
      <li><a href="#">Logout</a></li>
    </ul>
  </body>
</html>
