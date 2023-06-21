package com.banchan.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardQnDao;
import com.banchan.model.BoardQna;

public class BoardQnDetailController extends SuperClass{
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		if(loginfo==null) {
			super.setAlertMessage("로그인을 해주세요.");
			super.GotoPage("member/meLoginForm.jsp");
			return;
		}
		
		int boQnNo = Integer.parseInt(request.getParameter("boQnNo"));
		BoardQnDao dao = new BoardQnDao();
		BoardQna bean = null;
		String name = "";
		
		int cnt = -1;
		
		try {
			bean = dao.GetDataByPk(boQnNo);
			request.setAttribute("name", name);
			
			if(loginfo.getId().equals(bean.getBoQnWriter())==false) {
				cnt = dao.UpdateReadhit(boQnNo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(bean==null){
			super.setAlertMessage("잘못된 게시물 정보입니다.");
			super.GotoPage("home.jsp");
		}else {
			request.setAttribute("bean", bean) ;
			super.GotoPage("board/boQnDetailForm.jsp"); 
		}
	}
}
