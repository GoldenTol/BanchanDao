package com.banchan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.banchan.mall.CartManager;
import com.banchan.model.Member;


// 하위 컨트롤러들의 공통 기능 ( 추상 클래스 )
public abstract class SuperClass implements SuperController{
	private HttpServletRequest request = null;
	private HttpServletResponse response = null;
	
	protected HttpSession session = null;
	protected Member loginfo = null; // 로그인 여부 파악 변수
	protected CartManager mycart = null;
	
	private String contextPath = null;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.loginfo = (Member)this.session.getAttribute("loginfo");
		
		this.mycart = (CartManager)this.session.getAttribute("myCart");
		if(this.mycart == null) {
			this.mycart = new CartManager();
		}
		
		this.contextPath = request.getContextPath();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
		this.loginfo = (Member)this.session.getAttribute("loginfo");
		
		this.mycart = (CartManager)this.session.getAttribute("myCart");
		if(this.mycart == null) {
			this.mycart = new CartManager();
		}
		
		this.contextPath = request.getContextPath();
	}

	public void GotoPage(String gotoPage) {
		// 요청한 페이지로 이동
		RequestDispatcher dispatcher = null;
		try {
			dispatcher = request.getRequestDispatcher(gotoPage);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setAlertMessage(String message) {
		// session 영역에 alertMsg라는 이름으로 사용자에게 경고/오류/주의 문구 표시 ( common.jsp )
		this.session.setAttribute("alertMsg", message);
	}

	protected String getUrlInfo(String todoCommand) {
		String urlPrefix = contextPath + "/bMall?command=";
		return urlPrefix + todoCommand;
	}
	
	public void youNeededLogin() {
		String message = "로그인이 필요한 서비스 입니다.";
		this.setAlertMessage(message);
		this.GotoPage("member/meLoginForm.jsp");
		
	}
}
