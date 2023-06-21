package com.banchan.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.MemberDao;
import com.banchan.model.Member;

public class MemberDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String id = super.loginfo.getId();
		
		MemberDao dao = new MemberDao();
		
		Member bean = null;
		
		try {
			bean = dao.getDataByPk(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	
		if(bean==null) {
			super.setAlertMessage("잘못된 회원 정보입니다.");
			super.GotoPage("home.jsp");
		}else {
			request.setAttribute("bean", bean);
			super.GotoPage("member/meDetail.jsp");
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
}
