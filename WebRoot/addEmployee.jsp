<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Add member</title>
  </head>
  
  <body>
    <h1>Add Employee</h1>
    <form action="<%=basePath %>employee.do?flag=doPostAdd" method="post">
      <table>
        <tr><td>Name: </td><td><input type="text" name="name"></td></tr>
        <tr><td>Email: </td><td><input type="text" name="email"></td></tr>
        <tr><td>Password: </td><td><input type="text" name="password"></td></tr>
        <tr><td>Grade: </td><td><input type="text" name="grade"></td></tr>
        <tr><td>Salary: </td><td><input type="text" name="salary"></td></tr>
        <tr><td>Department: </td>
          <td>
            <select name="departmentId">
              <option value="3">财务部</option>
            </select>
          </td>
        </tr>
        <tr><td colspan="2"><input type="submit" value="Add Employee"></td></tr>
      </table>
    </form>
  </body>
</html>
