<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String middlePage = "jsps/";
String tm = (String)request.getAttribute("middlePath");
if(null != tm){
  middlePage=middlePage+tm+".jsp";
%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="<%=middlePage %>"></jsp:include>
<jsp:include page="tail.jsp"></jsp:include>
<%}else{%>
<jsp:forward page="index.jsp"></jsp:forward>
<%}%>