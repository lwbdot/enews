package com.lwb.web.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lwb.services.interfaces.UserInfoServiceInter;
import com.lwb.ssh.domain.UserInfo;
import com.lwb.web.beans.UsersList;
import com.lwb.web.utils.CheckingCode;

public class UserAction extends DispatchAction {
	private UserInfoServiceInter usi;

	public void setUsi(UserInfoServiceInter usi){
		this.usi = usi;
	}

	public ActionForward deleteUser(ActionMapping am, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) 
            throws Exception {
		String msg = "";
		if(!adminRightCheck(req)){
			msg = "你未登陆或者你不是管理员，不可以删除用户。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		String userId_s = req.getParameter("userId");
		if (null == userId_s) {
			msg = "你未登陆或者你不是管理员，不可以删除用户。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		int ui = Integer.parseInt(userId_s);
		UserInfo user = (UserInfo) usi.getObjectById(UserInfo.class, ui);
		if(null != user){
			usi.deleteObject(user);
		}
		return viewUsers(am, form, req, res);
	}

	public ActionForward viewUsers(ActionMapping am, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) 
            throws Exception {
		String msg = "";
		if(!adminRightCheck(req)){
			msg = "你未登陆或者你不是管理员，不可以查看用户信息。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		UsersList ul = new UsersList();
		ul.currentPage = 1;
		ul.pageSize = 20;
		ul.totalPage = 0;
		int totalUsers = usi.getItemsCount();
		if(totalUsers>0){
			ul.users = usi.getItems(ul.currentPage, ul.pageSize);
			ul.totalPage = totalUsers/ul.pageSize;
			if(totalUsers%ul.pageSize != 0){
				ul.totalPage ++;
			}
			String cp = req.getParameter("currentPage");
			if(null != cp){
				ul.currentPage = Integer.parseInt(cp);
			}
		}

		req.setAttribute("middlePath", "mMySpaceManageUser");
		req.setAttribute("ul", ul);
		return am.findForward("gotoUserList");
	}

	private boolean adminRightCheck(HttpServletRequest req){
		UserInfo user = (UserInfo) req.getSession().getAttribute("user");
		if(null == user){
			return false;
		}
		if(user.getUserLevel() != 0){
			return false;
		}
		return true;
	}
	
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) 
            throws Exception {
		String action = "r";
		String codeStr = (String) req.getSession().getAttribute("checkStr");
		String checkCode = req.getParameter("checkCode");
		if(null == codeStr){
			req.setAttribute("error", "验证码已过期，请重新输入");
			req.setAttribute("middlePath", "mLoginOrRegister");
			req.setAttribute("action", action);
			req.getSession().invalidate();
			return mapping.findForward("gotoLogin");
		}else if(!codeStr.equals(checkCode)){
			req.setAttribute("error", "验证码不正确，请重新输入");
			req.setAttribute("middlePath", "mLoginOrRegister");
			req.setAttribute("action", action);
			req.getSession().invalidate();
			return mapping.findForward("gotoLogin");
		}

		//String middlePath = "mLoginOrRegister";
		String callBackUri = "user.do?flag=loginOrRegister";
		String userName = req.getParameter("userName");
		UserInfo user0 = usi.getUserInfoByName(userName);
		if(null != user0){
			req.setAttribute("error", "此用户名已被注册，请重输");
			req.setAttribute("middlePath", "mLoginOrRegister");
			req.setAttribute("action", action);
			return mapping.findForward("gotoLogin");
		}
		UserInfo user = this.getUserInfoFromBrowser(req);
		
		usi.addObject(user);
		callBackUri = "callbackUries#user.do?flag=loginOrRegister";
		req.setAttribute("msg", "注册成功，");
		req.setAttribute("middlePath", "mResult");
		req.setAttribute("cbw", "【请登录】");
		req.setAttribute("cbu", callBackUri);
		req.setAttribute("action", action);
		req.setAttribute("pt", 4+"");
		return mapping.findForward("gotoLogin");
	}

	private UserInfo getUserInfoFromBrowser(HttpServletRequest req){
		UserInfo user = new UserInfo();
		user.setUserEmail(req.getParameter("userEmail"));
		user.setUserLevel(1);
		user.setUserName(req.getParameter("userName"));
		user.setUserPass(req.getParameter("password"));
		user.setUserRealName(req.getParameter("realName"));
		user.setUserRigisterDate(new Date());
		return user;
	}

	public ActionForward loginOrRegister(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) 
            throws Exception {
		if(null != req.getSession().getAttribute("user")){
			req.setAttribute("msg", "你已经登录，请不要重复登录");
			req.setAttribute("middlePath", "mResult");
			return mapping.findForward("goResult");
		}
		String p = req.getParameter("register");
		String action = "l"; // For user login
		if(null != p){
			action = "r";// For user register
		}
		String middlePath = "mLoginOrRegister";
		String titleNameIndex = "4";
		req.setAttribute("action", action);
		req.setAttribute("pt", titleNameIndex);
		req.setAttribute("middlePath", middlePath);
		//CheckingCode image = new CheckingCode();

		//String code = image.generateCodeImage(4,0,0,res.getOutputStream());
		//req.getSession().setAttribute("checkStr", code);
		return mapping.findForward("gotoLogin");
	}

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) 
	    throws Exception {

		if(null != req.getSession().getAttribute("user")){
			req.setAttribute("msg", "你已经登录，请不要重复登录");
			req.setAttribute("middlePath", "mResult");
			return mapping.findForward("goResult");
		}
		String userName = req.getParameter("userName");
		String userPass = req.getParameter("password");
		if((null == userName)||(null == userPass)||(userName.length()<1)||(userPass.length()<6)){
			req.setAttribute("error", "用户名或密码错误");
			String middlePath = "mLoginOrRegister";
			req.setAttribute("middlePath", middlePath);
			return mapping.findForward("gotoLogin");
		}
		
		UserInfo user = usi.getUserInfoByName(userName);
		if ((null != user)&&user.getUserPass().equals(userPass)) {
			String forward = "goIndex";
			req.getSession().setAttribute("user", user);
			return mapping.findForward(forward);
		}
		req.setAttribute("error", "用户名或密码错误");
		String middlePath = "mLoginOrRegister";
		req.setAttribute("middlePath", middlePath);
		return mapping.findForward("gotoLogin");
	}

	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) 
	    throws Exception {

		if(null != req.getSession().getAttribute("user")){
			req.getSession().invalidate();
		}
		return mapping.findForward("goIndex");
	}

	public ActionForward mySpace(ActionMapping mapping, ActionForm form,
			HttpServletRequest req, HttpServletResponse res) 
	    throws Exception {

		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
		if(null == user ){
			req.setAttribute("msg", "只有管理员才可以进入网站后台，如果你是管理员，请");
			req.setAttribute("cbw", "【点此登录】");
			req.setAttribute("cbu", "callbackUries#user.do?flag=loginOrRegister#addNews.do?flag=publish");
			req.setAttribute("middlePath", "mResult");
			return mapping.findForward("goResult");
		}
		/*
		else if (user.getUserLevel()>0) {
			req.setAttribute("msg", "你不是管理员，不能进入后台");
			req.setAttribute("middlePath", "mResult");
			return mapping.findForward("goResult");
		}
		*/
		if(user.getUserLevel()<1){
			req.setAttribute("middlePath", "mMySpace");
		}else {
			req.setAttribute("middlePath", "mMySpaceNormalUser");
		}
		return mapping.findForward("gotoMySpace");
	}
}
