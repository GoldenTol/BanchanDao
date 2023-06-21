package com.banchan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/payment/verify/{imp_uid}"})
public class PayController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		PaymentCheck pChk = new PaymentCheck();
		String token = pChk.getImportToken();
		String mid = request.getParameter("mid");
		
		System.out.println("token : " + token);
		System.out.println("mid : " + mid);
		
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		
	}
}
