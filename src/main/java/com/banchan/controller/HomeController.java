package com.banchan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//홍 컨트롤러
public class HomeController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request,response);
		String gotopage = "common/home.jsp";
		
		super.GotoPage(gotopage);
	}
}
