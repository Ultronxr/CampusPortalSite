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
		graphics.setColor(getRandColor());
		graphics.fillRect(0, 0, VerifyCodeConstants.getImageWidth(), VerifyCodeConstants.getImageHeight());
		
		for(int i = 0; i < 4; i++)
			drawCode(graphics, i);
		
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		ImageIO.write(bufferedImage, "GIF", servletOutputStream);
		
		servletOutputStream.close();
		
		System.out.println("执行CodeAction方法，生成的验证码为："+codeNumber);
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
	
	private Color getRandColor() {
		Random random = new Random();
		int rRandom = random.nextInt(255),
			gRandom = random.nextInt(255),
			bRandom = random.nextInt(255); //生成0-255之间的随机数
		
		return new Color(rRandom, gRandom, bRandom);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
