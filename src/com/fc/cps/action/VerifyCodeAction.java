package com.fc.cps.action;

import com.fc.cps.global.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/VerifyCodeAction")
public class VerifyCodeAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String codeNumber = "";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BufferedImage bufferedImage = new BufferedImage(VerifyCodeConstants.getImageWidth(), VerifyCodeConstants.getImageHeight(), BufferedImage.TYPE_INT_RGB);
		
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(createRandomColor(200, 250));
		graphics.fillRect(0, 0, VerifyCodeConstants.getImageWidth(), VerifyCodeConstants.getImageHeight());
		
		for(int i = 0; i < 4; i++)
			drawCode(graphics, i);
		drawNoise(graphics, 12);
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		ImageIO.write(bufferedImage, "GIF", servletOutputStream);
		
		servletOutputStream.close();
		
		System.out.println("执行CodeAction方法，生成的验证码为："+codeNumber);
		VerifyCodeConstants.setCodeNumber(codeNumber);
		codeNumber = "";
	}
	
	private void drawCode(Graphics graphics, int i) {
		Random random = new Random();
		int subBegin = random.nextInt(VerifyCodeConstants.getImageChar().length());
		String number = VerifyCodeConstants.getImageChar().substring(subBegin, subBegin+1);
		graphics.setFont(VerifyCodeConstants.getCodeFont()[i]);
		graphics.setColor(VerifyCodeConstants.getColor()[i]);
		graphics.drawString(number, 20 + i * 20, 30);
		codeNumber += number;
	}
	
	private Color createRandomColor(int fc, int bc) {// 给定范围获得随机颜色
		// 随机对象
		Random random = new Random();
		// 随机初始数值不得大于255
		if (fc > 255)
			fc = 255;
		// 随机初始数值不得大于255
		if (bc > 255)
			bc = 255;
		// 产生随机红蓝绿色调
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	
	private void drawNoise(Graphics graphics, int lineNumber) {
		// 干扰线颜色
		graphics.setColor(createRandomColor(160, 200));
		for (int i = 0; i < lineNumber; i++) {
			// 线的启示X轴(只在80,20的范围内随机，由于从零开始，所以要加一)
			int pointX1 = 1 + (int) (Math.random() * 120);
			// 线的启示Y轴(只在80,20的范围内随机，由于从零开始，所以要加一)
			int pointY1 = 1 + (int) (Math.random() * 40);
			// 线的终止X轴(只在80,20的范围内随机，由于从零开始，所以要加一)
			int pointX2 = 1 + (int) (Math.random() * 120);
			// 线的终止Y轴(只在80,20的范围内随机，由于从零开始，所以要加一)
			int pointY2 = 1 + (int) (Math.random() * 40);
			graphics.drawLine(pointX1, pointY1, pointX2, pointY2);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
