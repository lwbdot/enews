<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String msg = (String)request.getAttribute("msg");
String callBackWord = (String)request.getAttribute("cbw");
String callBackUri = (String)request.getAttribute("cbu");
String callBaceUries[];
String thisCallback = null;
if(callBackUri != null){
  callBaceUries = callBackUri.split("#");
  if(callBaceUries.length>1){
    thisCallback = callBaceUries[1];
    int n = callBaceUries.length;
    if(n>2){
      thisCallback += "&cbu="+callBaceUries[0];
      for(int i=2;i<n;i++){
        thisCallback+="#"+callBaceUries[i];
      }
    }
  }else{
    thisCallback = callBackUri;
  }
}else{
  callBackUri = "#";
  callBackWord = "";
}
%>
<div class="body0">
  <div style="margin: 50px auto;width: 80%; text-align: center;">
    <span><%=msg %>&nbsp;&nbsp;</span>
    <a href="<%=thisCallback %>"> <%=callBackWord %> </a>
  </div>
</div>