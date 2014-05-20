package com.lwb.web.actions;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.lwb.ssh.domain.UserInfo;
import com.lwb.web.forms.SliderFiles;

public class AddSliders extends DispatchAction {
	public ActionForward toAdd(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res)
	        throws Exception{
		String msg = "";
		if(!adminRightCheck(req)){
			msg = "你未登陆或者你不是管理员，不可以更换图片。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}
		req.setAttribute("middlePath", "mMySpaceChangeSliders");
		return am.findForward("gotoAddSliders");
	}

	public ActionForward adding(ActionMapping am, ActionForm af,
			HttpServletRequest req, HttpServletResponse res)
	        throws Exception{
		String msg = null;
		if(!adminRightCheck(req)){
			msg = "你未登陆或者你不是管理员，不可以更换图片。";
			req.setAttribute("middlePath", "mResult");
			req.setAttribute("msg", msg);
			return am.findForward("goResult");
		}

		// 当由Spring接管后，Struts中无法取得Servlet上下文，所以不能用JSPsmartUpload上传文件，只有自己实现上传功能
		ServletConfig config = this.getServlet().getServletConfig();
		msg = uploadAndSaveFileByJspSmartUpload(config, req, res);
		String mp = "mResult";
		if(null == msg){
			msg = "图片上传成功，现在可以在主页查看了.如果主页没有显示新上传的图片，请按F5键刷新";
		}else {
			mp = "mMySpaceChangeSliders";
		}

		req.setAttribute("msg", msg);
		req.setAttribute("middlePath", mp);
		return am.findForward("goResult");
	}

	private String uploadAndSaveFileByJspSmartUpload(ServletConfig config, HttpServletRequest req, HttpServletResponse res){
		SmartUpload addImg = new SmartUpload();
		String imgSavedPath = "images/slides/";
		String imgNames[] = {"s1","s2","s3","s4","s5"};
		try {
			addImg.initialize(config, req, res);// 初始化文件上传组件
			addImg.setMaxFileSize(1024*1024);//限制图片为1MB
			addImg.setTotalMaxFileSize(6*1024*1024);//上传文件的总大小为6MB
			addImg.setAllowedFilesList("jpg,JPG");//运行上传文件的后缀名
			//addImg.setDeniedFilesList("exe,bat,jsp,htm,html,pl,,mp4,avi,txt");//不允许上传的文件类型

			addImg.upload();//上传文件

			File file = null;
			String fileName=null;
			String fileExtension=null;
			String fileSavedPath=null;
			java.io.File file2 = null;
			for (int i = 0; i < 5; i++) {// 最多可上传5个文件
				file = addImg.getFiles().getFile(i);
				if(file.isMissing() == false){
					fileExtension = file.getFileExt();
					fileName = imgNames[i];
					fileSavedPath = imgSavedPath+fileName+"."+fileExtension;

					file2 = new java.io.File(fileSavedPath);
					if(file2.exists()){// 删除同名文件
						file2.delete();
					}

					file.saveAs(fileSavedPath);// 保存文件
					//从Form表单中获取除文件外的其他信息
					//这里不能直接使用request，只能从addImg对象中获取
					//Request myreq = addImg.getRequest();// 这里不需要获取其他信息
				}
			}
		} catch (Exception e) {
			return "上传图片失败，原因是："+e.toString()+"，请重新添加";
		}
		return null;
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
}
