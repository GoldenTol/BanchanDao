package com.banchan.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardQnDao;
import com.banchan.model.BoardQna;


public class BoardQnReplyController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		int boQnDepth = Integer.parseInt(request.getParameter("boQnDepth"));
		
		if(boQnDepth==3) {
			String message = "답글의 깊이는 최대 3까지 입니다.";
			super.setAlertMessage(message);
			new BoardListController().doGet(request, response);
			return;
			
		}
		
		int boQnReplyCount = 0; // 총 답글의 개수
		int boQnGroupno = Integer.parseInt(request.getParameter("boQnGroupno"));
		
		BoardQnDao dao = new BoardQnDao();
		
		try {
			boQnReplyCount = dao.GetReplyCount(boQnGroupno);
			
			if(boQnReplyCount >= 5) {
				String message = "답글의 최대수를 초과하여 답글 작성이 불가능합니다.";
				super.setAlertMessage(message);
				new BoardQnListController().doGet(request, response);
				super.GotoPage("board/boQnList.jsp");
			}else {
				super.GotoPage("board/boQnReplyForm.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String boQnWriter = request.getParameter("boQnWriter");
		String boQnSubject = request.getParameter("boQnSubject");
		String boQnContent = request.getParameter("boQnContent");
		String boQnRegdate = request.getParameter("boQnRegdate");
		
		int boQnGroupno = Integer.parseInt(request.getParameter("boQnGroupno"))  ;
		int boQnOrderno = Integer.parseInt(request.getParameter("boQnOrderno"));
		int boQnDepth = Integer.parseInt(request.getParameter("boQnDepth"));
		
		BoardQna bean = new BoardQna();
		
		bean.setBoQnWriter(boQnWriter);
		bean.setBoQnSubject(boQnSubject);
		bean.setBoQnContent(boQnContent);
		bean.setBoQnRegdate(boQnRegdate);
		bean.setBoQnGroupno(boQnGroupno);
		bean.setBoQnOrderno(boQnOrderno + 1);
		bean.setBoQnDepth(boQnDepth + 1);
		
		int cnt = -1;
		
		BoardQnDao dao = new BoardQnDao();
		
		try {
			cnt = dao.ReplyData(bean, boQnOrderno);
			new BoardQnListController().doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}