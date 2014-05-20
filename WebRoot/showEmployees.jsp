<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>All Employees</title>
  </head>
  
  <body>
    <h1>员工列表</h1>
    <table border="1" cellpadding="5" cellspacing="0" bordercolor="black">
      <tr>
        <td>ID</td><td>Name</td><td>Email</td><td>Salary</td>
        <td>Grade</td><td>Hire Date</td><td>Department</td><td>Options</td>
      </tr>
      <c:forEach items="${employees}" var="emp">
      <tr>
        <td>${emp.id }</td><td>${emp.name }</td><td>${emp.email }</td>
        <td>${emp.salary}</td><td>${emp.grade }</td><td>${emp.hireDate }</td>
        <td>${emp.department.name }</td>
        <td>
          <a href="#">Modify</a>&nbsp;&nbsp;&nbsp;
          <a href="#">Delete</a>
        </td>
      </tr>
      </c:forEach>
      <tr>
        <td colspan="8" align="center">
          <span>Total pages: ${total }&nbsp;&nbsp;&nbsp;</span>
          <a href="<%=basePath %>employee.do?flag=doGetShow&currentPage=${current-1 }">Last</a>&nbsp;&nbsp;&nbsp;
          <a href="<%=basePath %>employee.do?flag=doGetShow&currentPage=${current+1 }">Next</a>
        </td>
      </tr>
    </table>
  </body>
</html>
