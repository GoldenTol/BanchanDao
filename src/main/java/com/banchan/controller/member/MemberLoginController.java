package com.banchan.controller.member;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.HomeController;
import com.banchan.controller.SuperClass;
import com.banchan.model.Member;
import com.banchan.dao.MemberDao;

public class MemberLoginController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String gotoPage = "member/meLoginForm.jsp";
		super.GotoPage(gotoPage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		MemberDao dao = new MemberDao();
		
		Member bean = null;
		
		try {
			bean = dao.SelectData(id, password);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		String gotoPage = "member/meLoginForm.jsp";
		
		if(bean==null) {
			String message = "로그인 정보가 잘못되었습니다.";
			super.setAlertMessage(message);
			super.GotoPage(gotoPage);
		}else {
			super.session.setAttribute("loginfo", bean);
			new HomeController().doGet(request, response);
		}
	}
}
