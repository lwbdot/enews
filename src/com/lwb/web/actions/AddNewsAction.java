package com.lwb.web.actions;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.lwb.services.interfaces.NewsDetailServiceInter;
import com.lwb.services.interfaces.NewsServiceInter;
import com.lwb.services.interfaces.UserInfoServiceInter;
import com.lwb.ssh.domain.News;
import com.lwb.ssh.domain.NewsDetail;
import com.lwb.ssh.domain.UserInfo;

public class AddNewsAction extends DispatchAction {

	private NewsServiceInter nsi;
	private NewsDetailServiceInter dsi;
	private UserInfoServiceInter usi;

	public void setUsi(UserInfoServiceInter usi) {
		this.usi = usi;
	}
	public void setNsi(NewsServiceInter esi){
		this.nsi = esi;
	}
	public void setDsi(NewsDetailServiceInter dsi) {
		this.dsi = dsi;
	}

	public ActionForward gotoAdd(ActionMapping am, ActionForm af,
	    HttpServletRequest req, HttpServletResponse res) 
	    throws Exception{

		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
		if(null == user){
			req.setAttribute("msg", "只有管理员可以发布新闻，如果你是管理员，请");
			req.setAttribute("cbw", "【点此登录】");
			req.setAttribute("cbu", "callbackUries#user.do?flag=loginOrRegister#addNews.do?flag=publish");
			req.setAttribute("middlePath", "mResult");
			return am.findForward("goLogin");
		}

		if(user.getUserLevel() != 0){
			req.setAttribute("msg", "你不是管理员，不能发布新闻");
			req.setAttribute("middlePath", "mResult");
			return am.findForward("goResult");
		}
		req.setAttribute("middlePath", "mAddNews");
		return am.findForward("gotoAdd");
	}

	public ActionForward publish(ActionMapping am, ActionForm af,
		HttpServletRequest req, HttpServletResponse res) 
		throws Exception{

		UserInfo user = (UserInfo)req.getSession().getAttribute("user");
		if(null == user){
			req.setAttribute("msg", "只有管理员可以发布新闻，如果你是管理员，请");
			req.setAttribute("cbw", "【点此登录】");
			req.setAttribute("cbu", "callbackUries#user.do?flag=loginOrRegister#addNews.do?flag=publish");
			req.setAttribute("middlePath", "mResult");
			return am.findForward("goLogin");
		}

		if(user.getUserLevel() != 0){
			req.setAttribute("msg", "你不是管理员，不能发布新闻");
			req.setAttribute("middlePath", "mResult");
			return am.findForward("goResult");
		}

		News news = getNewsFromBrowser(req);
		//Date date = new Date();
		Long times = System.currentTimeMillis();
		String date_s = times+""; // 为了在添加后，得到刚刚添加数据的ID号，这里设置一个与时间相关的属性
		news.setNewsExtra(date_s);
		nsi.addObject(news); // 插入数据库，此时news中的id没有值

		// 下面获取刚刚插入数据的ID值，此时news的id就有值了
		news = (News) nsi.getAnItem("newsExtra", date_s);
		news.setNewsExtra("0");
		nsi.updateObject(news);  //将记录改回原来的样子

		String newsTypes[] = {" ","最新新闻","学院公告","学院新闻"};
		NewsDetail detail = getNewsDetailFromBrowser(req, news.getId(), user.getId());
		detail.setNewsClassify(newsTypes[news.getNewsTypeId()]);
		dsi.addObject(detail);
		
		req.setAttribute("msg", "新闻发布成功，现在可以浏览该新闻了。");
		req.setAttribute("middlePath", "mResult");
		return am.findForward("goResult");
	}

	private News getNewsFromBrowser(HttpServletRequest req){
		News news = new News();
		news.setNewsExtra("0");
		news.setNewsPublishDate(new Date());
		news.setNewsTitle(req.getParameter("newsTitle"));
		news.setNewsTypeId(Integer.parseInt(req.getParameter("newsType")));
		news.setNewsWrittenDate(new Date());
		return news;
	}

	private NewsDetail getNewsDetailFromBrowser(HttpServletRequest req,int newsId,int writerId){
		
		NewsDetail detail = new NewsDetail();
		detail.setNewsAttachPath("0");// 0 for no attach;
		detail.setNewsClassify("0");
		detail.setNewsContent(req.getParameter("newsBody"));
		detail.setNewsFlag(1);
		detail.setNewsId(newsId);
		detail.setNewsPicPath("0");
		detail.setNewsReadTimes(0);
		detail.setNewsWriterId(writerId);
		return detail;
	}
}
