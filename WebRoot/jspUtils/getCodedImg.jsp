<%@page import="com.lwb.web.utils.CheckingCode"%><%
response.setHeader("Pragma", "no-cache");
response.setHeader("Cache-control","no-cache");
response.setHeader("Expires","0");
CheckingCode image = new CheckingCode();
ServletOutputStream os = response.getOutputStream();
String code = image.generateCodeImage(4,80,30,os);
os.close();
session.setAttribute("checkStr", code);
%>