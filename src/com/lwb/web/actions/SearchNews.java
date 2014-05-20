package com.lwb.web.actions;

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
import com.lwb.web.beans.NewsShowPage;

public class SearchNews extends DispatchAction {
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

	public ActionForward searchResult(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res) throws Exception{
		int currentPage = 1;
		int pageSize = 20;
		String currentPage_s = req.getParameter("currentPage");
		String oneDetailNewsId = req.getParameter("newsId");
		if(null != currentPage_s){
			currentPage = Integer.parseInt(currentPage_s);
		}

		String kw = req.getParameter("kw");
		NewsShowPage nsp = new NewsShowPage();
		// 搜索时，不按照新闻类型搜索
		nsp.newsList = nsi.searchItem(kw,0, currentPage, pageSize);
		int totalItems = nsi.searchItemCount(kw);
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
			int ctotalItems = csi.getItemsCount(id);
			nsp.ctotal = ctotalItems/nsp.csize;
			if(ctotalItems%nsp.csize != 0){
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

		req.setAttribute("kw", kw);
		req.setAttribute("newsShow", nsp);
		req.setAttribute("middlePath", "mSearchNews");
		return am.findForward("gotoSearch");
	}
}
