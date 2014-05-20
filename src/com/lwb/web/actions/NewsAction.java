package com.lwb.web.actions;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lwb.services.interfaces.NewsCommentServiceInter;
import com.lwb.services.interfaces.NewsDetailServiceInter;
import com.lwb.services.interfaces.NewsServiceInter;
import com.lwb.services.interfaces.UserInfoServiceInter;
import com.lwb.ssh.domain.News;
import com.lwb.ssh.domain.NewsComment;
import com.lwb.ssh.domain.NewsDetail;
import com.lwb.ssh.domain.UserInfo;
import com.lwb.web.beans.CommentViewPage;
import com.lwb.web.beans.CommentsInfo;
import com.lwb.web.beans.HomePage;
import com.lwb.web.beans.NewsInfo;
import com.lwb.web.beans.NewsInfoPage;
import com.lwb.web.beans.NewsShowPage;

public class NewsAction extends DispatchAction {
	private NewsServiceInter nsi;
	private NewsCommentServiceInter csi;
	private NewsDetailServiceInter dsi;
	private UserInfoServiceInter usi;

	public void setUsi(UserInfoServiceInter usi) {
		this.usi = usi;
	}
	public void setNsi(NewsServiceInter esi){
		this.nsi = esi;
	}
	public void setCsi(NewsCommentServiceInter csi) {
		this.csi = csi;
	}
	public void setDsi(NewsDetailServiceInter dsi) {
		this.dsi = dsi;
	}

	public ActionForward deleteNews(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res)
	        throws Exception{
		String msg = "";
		if(!adminRightCheck(req)){
			msg = "你未登陆或者你不是管理员，不可以管理新闻。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		int newsId = 0;
		String newsId_s = req.getParameter("newsId");
		if(null != newsId_s){
			newsId = Integer.parseInt(newsId_s);
		}
		News news = (News) nsi.getObjectById(News.class, newsId);
		NewsDetail detail = (NewsDetail) dsi.getDetailByNewsId(newsId);
		List<NewsComment> cmts = csi.getItemsByAttr("newsId", newsId+"", 1, 20);
		if(null != news){
			nsi.deleteObject(news); // 删除新闻本身
		}
		if(null != news){
			dsi.deleteObject(detail); // 删除新闻详细内容
		}
		int n = 0;
		NewsComment cmt = null;
		while((null != cmts)&&(cmts.size()>0)){// 删除新闻所有评论
			n = cmts.size();
			for (int i = 0; i < n; i++) {
				cmt = cmts.get(i);
				csi.deleteObject(cmt);
			}
			cmts = csi.getItemsByAttr("newsId", newsId+"", 1, 20);
		}
		return manageNews(am, af, req, res);
	}

	public ActionForward manageNews(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res)
	        throws Exception{
		String msg = "";
		if(!adminRightCheck(req)){
			msg = "你未登陆或者你不是管理员，不可以管理新闻。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		int cp = 1;
		String cp_s = req.getParameter("currentPage");
		if(null != cp_s){
			cp = Integer.parseInt(cp_s);
		}
		NewsInfoPage ni = new NewsInfoPage();
		ni.currentPage = cp;
		ni.pageSize = 20;
		ni.totalPage = 0;
		int total = nsi.getItemsCount(1);
		ni.totalPage = total/ni.pageSize;
		if(total%ni.pageSize != 0){
			ni.totalPage++;
		}
		List news = nsi.getItemsByTypeId(1, cp, ni.pageSize);
		int n = news.size();
		if ((null != news)&&(n>0)) {
			News news2 = null;
			NewsDetail detail = null;
			for (int i = 0; i < n; i++) {
				news2 = (News) news.get(i);
				NewsInfo newsInfo = new NewsInfo();
				newsInfo.newsId = news2.getId();
				newsInfo.newsPublishDate = news2.getNewsPublishDate();
				newsInfo.newsTitle = news2.getNewsTitle();

				detail = (NewsDetail) dsi.getDetailByNewsId(newsInfo.newsId);
				newsInfo.newsClassify = detail.getNewsClassify();
				newsInfo.newsWriterId = detail.getNewsWriterId();
				newsInfo.readTimes = detail.getNewsReadTimes();

				UserInfo user = (UserInfo)usi.getObjectById(UserInfo.class, newsInfo.newsWriterId);
				newsInfo.newsWriterRealName=user.getUserRealName();
				
				ni.newsList.add(newsInfo);
			}
			req.setAttribute("ni", ni);
		}
		req.setAttribute("middlePath", "mMySpaceManageNews");
		return am.findForward("gotoMySpaceNewsList");
	}

	public ActionForward commentAction(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res)
	        throws Exception{
		String msg = "";
		if(!adminRightCheck(req)){
			msg = "你未登陆或者你不是管理员，不可以审核评论。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		String action = req.getParameter("act");
		String commentId_s = req.getParameter("cmtId");
		NewsComment nc = null;
		if ("del".equals(action)){
			if(null != commentId_s){
				int cid = Integer.parseInt(commentId_s);
				nc = (NewsComment) csi.getObjectById(NewsComment.class, cid);
				csi.deleteObject(nc);
			}else {
				msg = "操作失败，请重新操作";
				req.setAttribute("middlePath", "mResult");
				req.setAttribute("msg", msg);
				return am.findForward("goResult");
			}
		}else if("agree".equals(action)) {
			if(null != commentId_s){
				int cid = Integer.parseInt(commentId_s);
				nc = (NewsComment) csi.getObjectById(NewsComment.class, cid);
				nc.setNewsCommentStatus(10);
				csi.updateObject(nc);
			}else {
				msg = "操作失败，请重新操作";
				req.setAttribute("middlePath", "mResult");
				req.setAttribute("msg", msg);
				return am.findForward("goResult");
			}
		}else {
			msg = "操作失败，请重新操作";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		return viewComment(am,af,req,res);
	}

	public ActionForward viewMyComments(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res)
	        throws Exception{
		String msg = "";
		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
		if(null == user){
			msg = "请先登录。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		CommentViewPage cv = new CommentViewPage();
		cv.currentPage = 1;
		cv.pageSize = 20;
		cv.pageTitle = "我发表的评论";
		cv.totalPage = 0;

		String cp = req.getParameter("currentPage");
		if(null != cp){
			cv.currentPage = Integer.parseInt(cp);
		}
		int items = csi.getItemsCountByAttr("newsCommenterId", user.getId()+"");
		cv.totalPage = items/cv.pageSize;
		if((items%cv.pageSize) != 0){
			cv.totalPage++;
		}
		if(cv.totalPage>0){
			List<NewsComment> al = csi.getItemsByAttr("newsCommenterId", user.getId()+"", cv.currentPage, cv.pageSize);
			//cv.comments =  ;
			int n = al.size();
			News news = null;
			NewsComment nc = null;
			for (int i = 0; i < n; i++) {
				nc = al.get(i);
				CommentsInfo ci = new CommentsInfo();
				ci.id = nc.getId();
				ci.newsCommentContent = nc.getNewsCommentContent();
				ci.newsCommentDate = nc.getNewsCommentDate();
				ci.newsCommenterId = nc.getNewsCommenterId();
				ci.newsCommentStatus = nc.getNewsCommentStatus();
				ci.newsExtra = nc.getNewsExtra();
				ci.newsId = nc.getNewsId();

				news = (News) nsi.getObjectById(News.class, nc.getNewsId());
				ci.newsTitle = news.getNewsTitle();
				cv.comments.add(ci);
			}
			req.setAttribute("cv", cv);
		}

		req.setAttribute("middlePath", "mMySpaceNormalUser");
		
		return am.findForward("gotoMySpace");
	}

	public ActionForward viewComment(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res)
	        throws Exception{
		String msg = "";
		if(!adminRightCheck(req)){
			msg = "你未登陆或者你不是管理员，不可以查看未审核的评论。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		CommentViewPage cv = new CommentViewPage();
		cv.currentPage = 1;
		cv.pageSize = 20;
		cv.pageTitle = "未审核的评论";
		cv.totalPage = 0;

		String cp = req.getParameter("currentPage");
		if(null != cp){
			cv.currentPage = Integer.parseInt(cp);
		}
		int items = csi.getItemsCountByStatus(1);// 1表示已经评论，但是未审核
		cv.totalPage = items/cv.pageSize;
		if((items%cv.pageSize) != 0){
			cv.totalPage++;
		}
		if(cv.totalPage>0){
			List<NewsComment> al = csi.getItemsByAttr("newsCommentStatus", "1", cv.currentPage, cv.pageSize);
			//cv.comments =  ;
			int n = al.size();
			News news = null;
			NewsComment nc = null;
			for (int i = 0; i < n; i++) {
				nc = al.get(i);
				CommentsInfo ci = new CommentsInfo();
				ci.id = nc.getId();
				ci.newsCommentContent = nc.getNewsCommentContent();
				ci.newsCommentDate = nc.getNewsCommentDate();
				ci.newsCommenterId = nc.getNewsCommenterId();
				ci.newsCommentStatus = nc.getNewsCommentStatus();
				ci.newsExtra = nc.getNewsExtra();
				ci.newsId = nc.getNewsId();

				news = (News) nsi.getObjectById(News.class, nc.getNewsId());
				ci.newsTitle = news.getNewsTitle();
				cv.comments.add(ci);
			}
			req.setAttribute("cv", cv);
		}
		req.setAttribute("middlePath", "mMySpace");
		return am.findForward("gotoMySpace");
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

	public ActionForward addComment(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		String cmt = req.getParameter("cmt");
		String msg = "评论成功，你的评论需要管理员审核后才可以看到，请耐心等待。";
		String mpage = "mResult";
		if ((null == cmt)||(cmt.length()<1)) {
			msg = "提交评论失败，失败原因是：未输入评论内容。";
			mpage = "mResult";
			String callBackUri = req.getParameter("cbu");

			req.setAttribute("cbw", "返回");
			req.setAttribute("cbu", callBackUri);
			req.setAttribute("msg", msg);
			req.setAttribute("middlePath", mpage);
			return am.findForward("goResult");
		}
		
		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
		if(null == user){
			msg = "提交评论失败，失败原因是：你没有登录，请登录后再评论。";
			req.setAttribute("cbw", "点此登录");
			req.setAttribute("cbu", "callbackUries#user.do?flag=loginOrRegister");
			req.setAttribute("middlePath", mpage);
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}

		NewsComment comment = this.getCommentFromBrowser(req, user);
		csi.addObject(comment);
		req.setAttribute("msg", msg);
		req.setAttribute("middlePath", "mResult");
		return am.findForward("goResult");
	}

	private NewsComment getCommentFromBrowser(HttpServletRequest req, UserInfo user){
		NewsComment comment = new NewsComment();
		comment.setNewsCommentContent(req.getParameter("cmt"));
		comment.setNewsCommentDate(new Date());
		comment.setNewsCommenterId(user.getId());
		comment.setNewsCommentStatus(1);
		String userName = user.getUserRealName();
		if((null == userName)||("0".equals(userName))){
			userName = user.getUserName();
		}
		comment.setNewsExtra(userName);
		comment.setNewsId(Integer.parseInt(req.getParameter("newsId")));
		return comment;
	}

	public ActionForward goHome(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		
		HomePage hp = new HomePage();
		hp.latestNews = nsi.getItemsByTypeId(1, 1, 10);
		hp.collegeNotice = nsi.getItemsByTypeId(2, 1, 11);
		hp.collegeNews = nsi.getItemsByTypeId(3, 1, 11);

		req.setAttribute("homePage", hp);
		return am.findForward("goHome");
	}

	public ActionForward viewNews(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		String []newsTypes = {" ","最新新闻","学院通告","学院新闻"};
		int newsTypeId = 1;
		String newsType = newsTypes[newsTypeId];
		int currentPage = 1;
		int pageSize = 20;
		String currentPage_s = req.getParameter("currentPage");
		String pageSize_s = req.getParameter("pageSize");
		String newsType_s = req.getParameter("newsType");
		String oneDetailNewsId = req.getParameter("newsId");
		if(null != currentPage_s){
			currentPage = Integer.parseInt(currentPage_s);
		}
		if(null != pageSize_s){
			pageSize = Integer.parseInt(pageSize_s);
		}
		if(null != newsType_s){
			newsTypeId = Integer.parseInt(newsType_s);
			newsType = newsTypes[newsTypeId];
		}

		NewsShowPage nsp = new NewsShowPage();
		nsp.newsList = nsi.getItemsByTypeId(newsTypeId, currentPage, pageSize);
		int totalItems = nsi.getItemsCount(newsTypeId);
		nsp.totalPages = totalItems/pageSize;
		if(totalItems%pageSize != 0){
			nsp.totalPages++;
		}
		if(currentPage>nsp.totalPages){
			currentPage = nsp.totalPages;
		}
		nsp.pageSize = pageSize;
		nsp.currentPage = currentPage;

		nsp.detail = null;
		nsp.oneNews = null;
		nsp.newsWriterName = null;
		nsp.newsComments = null;
		if(null != oneDetailNewsId){
			int id = Integer.parseInt(oneDetailNewsId);
			nsp.detail = (NewsDetail)dsi.getDetailByNewsId(id);
			int readTimes = nsp.detail.getNewsReadTimes();
			nsp.detail.setNewsReadTimes(readTimes+1);
			nsp.oneNews = (News)nsi.getObjectById(News.class, id);
			UserInfo user = (UserInfo)usi.getObjectById(UserInfo.class, nsp.detail.getNewsWriterId());
			nsp.newsWriterName = user.getUserRealName();
			if("0".equals(nsp.newsWriterName)){
				nsp.newsWriterName = user.getUserName();
			}

			nsp.ccurrent = 1;
			nsp.csize = 10;
			nsp.ctotal = csi.getItemsCount(id)/nsp.csize;
			if(csi.getItemsCount(id)%nsp.csize != 0){
				nsp.ctotal ++;
			}

			String current = req.getParameter("cc");
			if((null != current)&&(current.length()>0)){
				nsp.ccurrent = Integer.parseInt(current);
			}
			if (nsp.ccurrent>nsp.ctotal) {
				nsp.ccurrent = nsp.ctotal;
			}
			nsp.newsComments = (List<NewsComment>)csi.getItemsByTypeId(id, nsp.ccurrent, nsp.csize);
			if(null != nsp.newsComments){
				int n = nsp.newsComments.size();
				int commenterId = 0;
				UserInfo users = null;
				for(int j = 0;j<n;j++){
					commenterId = nsp.newsComments.get(j).getNewsCommenterId();
					users = (UserInfo)usi.getObjectById(UserInfo.class, commenterId);
					nsp.newsComments.get(j).setNewsExtra(users.getUserRealName());
				}
			}
		}
		nsp.newsType = newsType;
		nsp.newsTypeId = newsTypeId;

		req.setAttribute("newsShow", nsp);
		req.setAttribute("middlePath", "mShowNews");
		return am.findForward("listNews");
	}
}
