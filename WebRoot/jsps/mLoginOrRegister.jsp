<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% 
String action = (String)request.getAttribute("action");
boolean toLogin = true;
if("r".equals(action)){
    toLogin = false;
}

String errorMsg = "";
String error = (String)request.getAttribute("error");
if(null != error){
  errorMsg = error;
}
%>
<link rel="stylesheet" href="css/styles.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/loginpage.css" type="text/css" media="screen" />
<script type="text/javascript">
  function chechLoginFormData() {
    var username = document.getElementById("userName").value;
    var password = document.getElementById("password").value;
    if (username=="") {
      alert("请输入用户名");
      return false;
    }
    if((password=="")||(password.length < 6)){
      alert("请输入密码，密码至少6位");
      return false;
    }
    return true;
  }

  function chechRegisterFormData() {
    var password = document.getElementById("password").value;
    var password2 = document.getElementById("password2").value;
    var realName = document.getElementById("realName").value;
    var userEmail = document.getElementById("userEmail").value;
    var checkCode = document.getElementById("checkCode").value;
    if (realName=="") {
        alert("请输入真实姓名");
        return false;
    }
    if (userEmail=="") {
        alert("请输入电子邮件");
        return false;
    }
    if((password=="")||(password.length < 6)){
      alert("请输入密码，密码至少6位");
      return false;
    }
    if(checkCode==""){
      alert("请输入验证码");
      return false;
    }
    if((password2=="")||(password2 != password)){
        alert("2次输入的密码不正确，请重新输入密码");
        return false;
    }
    return true;
  }
</script>

<%if(toLogin){ %>
  <div class="body0" style="border: none;">
    <div class="login_box">
      <div class="login_main">
        <div class='fr mr20 lo_box'>
          <div class="welcome_label">
            <span>用户登录</span>
          </div>
          <form method="post" name="login" action="user.do">
            <input type="hidden" name="flag" value="login"/>
            <label style="font-size:15px;color:#666666;line-height:20px;">用户名</label><br/>
            <input type="text" id="userName" name="userName" class="login_text" />
            <label style="font-size:15px;color:#666666;line-height:20px">密码</label><br/>
            <input type="password" id="password" name="password" class="login_text" />
            <table style="margin-bottom:10px;color:red;">
              <tr>
                <td><%=errorMsg %></td>
              </tr>
            </table>
            <input type="submit" class="login_button" onclick="return chechLoginFormData();" value="" />
          </form>
          <span class="fl" style="margin-left:25px;margin-top:20px;font-size:15px;color:#000000">没有账号，请先
          <a href="user.do?flag=loginOrRegister&register=r"> 【注册】 </a>
          </span>
        </div>
      </div>
    </div>
  </div>
<%}else{ %>
  <div class="body0" style="border: none;">
    <div class="login_box">
      <div class="register_note">
        <div style="float:left;">
          <table width="530px" cellspacing="10" cellpadding="0" align="center" border="0">
            <tr><td class="note_title" align="center">注册须知</td></tr>
            <tr><td><hr class="sliderline"/></td></tr>
            <tr>
              <td class="note_content" align="left">
                <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
本网站仅限XXX学院学生注册，注册时，需保证注册信息真实。如果输入不实的注册信息，将会导致注册失败。
如果你符合注册条件，但是无法注册成功，可能原因是网站数据库中没有你的相关信息，请你联系网站管理员或
者学院管理员。如果非XXX学院的学生注册，一经发现，年级管理员或者网站管理员有权在不通知注册者的情况
下，删掉其账号。如果遇到其他问题，请联系网站管理员。<br></br>
网站管理员邮箱：xxx@xxx.com。<br></br>希望此网站能给你带去方便和帮助。
                </p>
              </td>
            </tr>
          </table>
        </div>
        <div class='fr mr20 lo_box'>
          <div class="welcome_label">用户注册</div>
          <form method="post" name="login" action="user.do" onclick="1">
            <input type="hidden" name="flag" value="register"/>
            <label class="register_label">
              <span class="register_star">*</span>用户名 <span style="color: red;"><%=errorMsg %></span> </label><br/>
            <input type="text" id="userName" name="userName" class="login_text" /><br/>
            
            <label class="register_label">
              <span class="register_star">*</span>密码（至少6位）</label><br/>
            <input type="password" id="password" name="password" class="login_text" /><br/>
            
            <label class="register_label">
              <span class="register_star">*</span>再次输入密码</label><br/>
            <input type="password" id="password2" name="password2" class="login_text" /><br/>
            
            <label class="register_label">
              <span class="register_star">*</span>真实姓名</label><br/>
            <input type="text" id="realName" name="realName" class="login_text" /><br/>
            
            <label class="register_label">
              <span class="register_star">*</span>Email</label><br/>
            <input type="text" id="userEmail" name="userEmail" class="login_text" /><br/>
             <label class="register_label">
              <span class="register_star">*</span>请输入下面的验证码</label><br/>
            <div>
            <script type="text/javascript">
              function changeCode(obj){
                obj.src="jspUtils/getCodedImg.jsp";
              }
            </script>
              <input type="text" id="checkCode" name="checkCode" class="login_text" style="width: 100px;margin-right: 10px;"/>
              <img align="top" style="cursor: pointer;" title="换一张" onclick="changeCode(this);" src="jspUtils/getCodedImg.jsp"/>
              <br/>
              <span>看不清楚，点击图片即可更换验证码</span>
              <br/>
            </div>
            <input style="margin-top: 10px" type="submit" class="register_btn" onclick="return chechRegisterFormData();" value="提交" />
            <!--  
            <input type="reset" class="fl reset_button" value="" />
            -->
          </form>
        </div>
      </div>
    </div>
    <div class="clearfloat"></div>
  </div>
<%} %>
  <div class="clearfloat"></div>