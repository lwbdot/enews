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
<%@page import="com.lwb.web.beans.NewsInfoPage"%>
<%@page import="com.lwb.web.beans.NewsInfo"%>
<%
//Below for right page
String rightTitle = "更换【校园风采】图片，请上传图片";
String addUri = "addSliders.do";
String errorMsg = (String)request.getAttribute("msg");
%>

<div class="body0">
  <jsp:include page="mMySpaceLeft.jsp"></jsp:include>

<!-- Right page start -->
<script type="text/javascript">
  function checkPicData(){
    var pic11 = document.getElementById("pic1").value;
    var pic21 = document.getElementById("pic2").value;
    var pic31 = document.getElementById("pic3").value;
    var pic41 = document.getElementById("pic4").value;
    var pic51 = document.getElementById("pic5").value;
    var nn = 0;
    if(pic11 != ""){
      nn++;
    }
    if(pic21 != ""){
      nn++;
    }
    if(pic31 != ""){
      nn++;
    }
    if(pic41 != ""){
      nn++;
    }
    if(pic51 != ""){
      nn++;
    }
    if(nn<2){
      alert("请至少选择2张图片，再上传");
      return false;
    }
    return true;
  }
</script>
  <div class="fr rightBox" style="width: 70%;">
    <span class="newsTitle"> <%=rightTitle %> </span><br/>
    <div class="newsDetailBox" style="height: auto;overflow: auto;">
      <div>
        <div style="margin:0 15px;">
          <span>更换、上传图片注意事项：</span><br/>
          <span>1. 图片至少上传【 2 】张，最多上传【 5 】张。</span><br/>
          <span>2. 图片格式仅限【 jpg 】（不区分大小写）。</span><br/>
          <span>3. 图片宽度为【 550 】像素左右，图片高度为【 300 】像素左右，如果像素值与左边的相差太大，图片显示会有问题。</span><br/>
          <span>4. 每张图片的大小不能超过【 1M 】。</span><br/>
          <span>5. 图片名称不要含有中文。</span><br/>
        </div>
        <hr size="2" width="98%" color="#0099cc"/>
        <div style="width:600px; margin: 15px auto;text-align: center;">
          <%if(null != errorMsg){ %>
          <span style="color: red;font-weight: bold;"> <%=errorMsg %> </span>
          <%} %>
          <form method="post" name="login" action="<%=addUri+"?flag=adding" %>" enctype="multipart/form-data">
            <!--  <input type="hidden" name="flag" value="adding"/>--> 
            <label class="b1">上传图片1： &nbsp;&nbsp;</label>
            <input class="b1" type="file" id="pic1" name="pic1"/><br/>
            <hr size="2" color="#eeeeee" width="70%" />
            <label class="b1">上传图片2： &nbsp;&nbsp;</label>
            <input class="b1" type="file" id="pic2" name="pic2"/><br/>
            <hr size="2" color="#eeeeee" width="70%" />
            <label class="b1">上传图片3： &nbsp;&nbsp;</label>
            <input class="b1" type="file" id="pic3" name="pic3"/><br/>
            <hr size="2" color="#eeeeee" width="70%" />
            <label class="b1">上传图片4： &nbsp;&nbsp;</label>
            <input class="b1" type="file" id="pic4" name="pic4"/><br/>
            <hr size="2" color="#eeeeee" width="70%" />
            <label class="b1">上传图片5： &nbsp;&nbsp;</label>
            <input class="b1" type="file" id="pic5" name="pic5"/><br/>
            <hr size="3" color="#515151" width="70%" />
            <input type="submit" class="rpublish_btn" onclick="return checkPicData();" value="上传图片" />
          </form>
        </div>
      </div>
    </div>
  </div>
  <div class="clearfloat"></div>
</div>