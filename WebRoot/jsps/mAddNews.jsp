<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String errorMsg = "";
String error = (String)request.getAttribute("error");
if(null != error){
  errorMsg = error;
}
%>

<script type="text/javascript">
  function checkNewsData(){
    var newsTitle = document.getElementById("newsTitle").value;
    if(newsTitle==""){
      alert("请输入新闻题目");
      return false;
    }
    var newsType = document.getElementById("newsType").value;
    if(newsType == 0){
      alert("请选择一个新闻类别");
      return false;
    }
    var newsBo = document.getElementById("newsBody").value;
    if(newsBo == ""){
      alert("请输入新闻内容");
      return false;
    }
    return true;
  }
</script>
<div class="body0" style="border: none;">
  <div class="addNewsBox">
    <div class="a1">
      <span class="newsTitle">请在下面输入要发布新闻的内容</span>
    </div>
    <hr size="3" color="#0099cc" width="96%" />
    <div class="a2">
    <form method="post" name="login" action="addNews.do">
      <input type="hidden" name="flag" value="publish"/>
      <label class="b1">新闻题目</label><br/>
      <input class="b1" size="50" type="text" id="newsTitle" name="newsTitle"/><br/>
      <hr size="2" color="#eeeeee" width="96%" />
      <label class="b1">新闻分类</label>
      <select class="b1" name="newsType" id="newsType">
        <option class="b1" value="0">请选择新闻类别</option>
        <option class="b1" value="2">学院公告</option>
        <option class="b1" value="3">学院新闻</option>
      </select>
      <br/>
      <hr size="2" color="#eeeeee" width="96%" />
      <label class="b1">新闻内容</label><br/>
      <textarea rows="15" cols="67" name="newsBody" id="newsBody"></textarea>
      <table style="margin-bottom:10px;color:red;">
        <tr><td><%=errorMsg %></td></tr>
      </table>
      <input type="submit" class="rpublish_btn fr" onclick="return checkNewsData();" value="发布新闻" />
    </form>
    </div>
    <div class="clearfloat"></div>
  </div>
</div>