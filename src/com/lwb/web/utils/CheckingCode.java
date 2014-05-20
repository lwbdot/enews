package com.lwb.web.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 生成验证码
 * @author llw
 *
 */
public class CheckingCode {
	// 可以出现在验证码图片上的字符
	private char mapTable[] = {
	    //'a','b','c','d','e','f','g','h','i','j','k',
	    //'l','m','n','o','p','q','r','s','t','u','v',
	    //'w','x','y','z',
	    '1','2','3','4','5','6','7','8','9','0'
	};

	/**
	 * 删除验证码
	 * @param codeLenth
	 * @param width
	 * @param height
	 * @param os
	 * @return
	 */
	public String generateCodeImage(int codeLenth, int width, int height, OutputStream os){
		if(width<1)
			width = 60;
		if(height<1)
			height = 20;
		if(codeLenth<4)
			codeLenth = 4;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();  // 获取图形上下文
		g.setColor(new Color(0xdcdcdc));  // 设定背景颜色
		g.fillRect(0, 0, width, height);
		g.setColor(Color.black);// 设置边框颜色
		g.drawRect(0, 0, width-1, height-1);// 画边框
		String codedStr = ""; // 存储验证码字符串
		int randomIndex = 0;
		for (int i = 0; i < codeLenth; i++) {
			randomIndex = (int) (mapTable.length*Math.random());
			codedStr += mapTable[randomIndex];
		}

		// 下面将验证码串显示到图片上
		g.setColor(Color.blue);
		g.setFont(new Font("Atlantic Inline",Font.PLAIN,18));
		String ch = "";
		int spaces = 12; // 字符之间的间隔，严格讲这个应该是随机值，即每2个字符之间的距离应该不一样，这里为了方便就不用随机值了
		for (int i = 0; i < codedStr.length(); i++) {
			ch = codedStr.substring(i,i+1);
			g.drawString(ch, spaces, 20);
			spaces+=12;
		}

		// 下面随机产生10个干扰点
		//g.setColor(Color.black);
		Random random = new Random();
		int x = 0,y = 0;
		for (int i = 0; i < 10; i++) {
			x = random.nextInt(width);
			y = random.nextInt(height);
			g.drawOval(x, y, 3, 3);
		}

		g.dispose(); // 释放图形上下文
		try {  // 输出图片
			ImageIO.write(img, "JPEG", os);
		} catch (IOException e) {
			return "";
		}
		return codedStr;
	}
}
