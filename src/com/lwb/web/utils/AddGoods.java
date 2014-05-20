package com.lwb.web.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;

public class AddGoods extends HttpServlet {
	private String imgSavedPath = "images/goods/";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String message = null;
		String nextPage = "jspseller/home.jsp";
		String rightPage = null;
		
		//Goods goods = null;
		
		//下面初始化文件上传组件，进行商品图片文件的上传工作
		SmartUpload addImg = new SmartUpload();
		ServletConfig config = getServletConfig();
		addImg.initialize(config, request, response);
		
		//正式添加图片文件
		try {
			addImg.setMaxFileSize(1024*1024);//限制图片为1MB
			addImg.setTotalMaxFileSize(10*1024*1024);//上传文件的重大小为10MB
			addImg.setAllowedFilesList("jpg,gif,png");//运行上传文件的后缀名
			addImg.setDeniedFilesList("exe,bat,jsp,htm,html,pl,,mp4,avi,txt");//不允许上传的文件类型
			
			addImg.upload();//上传文件

			File file = addImg.getFiles().getFile(0);
			if(file.isMissing()){
				;
			}else{
				String fileName=null,fileExtension=null;
				String fileSavedPath=null;
				fileExtension = file.getFileExt();
				fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				fileSavedPath = imgSavedPath+fileName+"."+fileExtension;
				file.saveAs(fileSavedPath);

				//从Form表单中获取除文件外的其他信息
				//这里不能直接使用request，只能从addImg对象中获取
				Request myrequest = addImg.getRequest();
				//goods = getGoodsInfo(myrequest,fileSavedPath);
			}
		} catch (Exception e) {
			System.out.println("添加失败，原因是："+e.toString());
			message = "添加商品图片发生未知错误，请重新添加";
			nextPage = "jspseller/home.jsp";
			request.setAttribute("rightPage", "result.jsp");
			request.setAttribute("message", message);
			request.getRequestDispatcher(nextPage).forward(request, response);
			return;
		}
		
		//图片文件已经保存完成，下面添加商品信息到数据库
		//GoodsSvc worker = new GoodsSvc();
		//if(worker.insertItem(goods)){
		//	message = "添加商品成功";
		//}else {
		//	message = "添加商品到数据库时失败";
		//}
		rightPage = "result.jsp";
		request.setAttribute("message", message);
		request.setAttribute("rightPage", rightPage);
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
/*	
	private Goods getGoodsInfo(Request request,String savedPath){
		Goods goods = new Goods();
		//JSPSmartLoad默认使用GBK编码，此时会导致乱码，下面要将含中文的字符串进行转码
		String temp1 = request.getParameter("goodsName");
		String temp2 = request.getParameter("goodsDetail");
		String goodsName=null,goodsDetail=null;
		byte[] bb = null;
		try {
			bb = temp1.getBytes("gbk");
			goodsName = new String(bb, "utf-8");
			bb = temp2.getBytes("gbk");
			goodsDetail = new String(bb, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		//转码完成后，由于JSPSmartLoad只能保证偶数个字符的串不乱码
		//当串包含奇数个字符时，在浏览器JS端强制在末尾加上“x”，所以在这里需要去掉最后一个“x”号
		//goodsName = getStringWithoutStar(goodsName);
		//goodsDetail = getStringWithoutStar(goodsDetail);

		goods.setExtraId(0+"");
		goods.setGoodsClass(request.getParameter("goodsClass"));
		goods.setGoodsCount(Integer.parseInt(request.getParameter("goodsCount")));
		goods.setGoodsDetail(goodsDetail);
		goods.setGoodsName(goodsName);
		goods.setGoodsPrice(Float.parseFloat(request.getParameter("goodsPrice")));
		goods.setImagePath(savedPath);
		//goods.setSoldCount(0);
		goods.setSoldCount(Integer.parseInt(request.getParameter("soldCount")));
		
		return goods;
	}
*/
	private String getStringWithoutStar(String str){
		String star = "x";
		String lastChar = str.substring((str.length()-1),str.length());
		String target = str;
		if (star.equals(lastChar)) {//最后一个字符是“x”
			target = str.substring(0,str.length()-1);
		}
		return target;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
