package com.banchan.controller.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardReDao;
import com.banchan.model.BoardReview;

public class BoardReReplyController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pattern = "yyyy/MM/dd HH:mm:ss";
		String boReRegdate = new SimpleDateFormat(pattern).format(new Date());
		
		int boReDepth = Integer.parseInt(request.getParameter("boReDepth"));
		int boReNo = Integer.parseInt(request.getParameter("boReNo"));
		
		if(boReDepth == 3) {
			String message = "답글 깊이는 최대 3까지 입니다.";
			super.setAlertMessage(message);
			new BoardListController().doGet(request, response);
			return;
		}
		
		int boReReplyCount = 0;
		int boReGroupno = Integer.parseInt(request.getParameter("boReGroupno"));
	
		BoardReDao dao = new BoardReDao();

		try {
			boReReplyCount = dao.GetReplyCount(boReGroupno);
		
			request.setAttribute("boReNo", boReNo);
			request.setAttribute("boReRegdate", boReRegdate);
			
			super.GotoPage("board/boReReplayForm.jsp");
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String boReWriter = request.getParameter("boReWriter");
		String boReSubject = request.getParameter("boReSubject");
		String boReContent = request.getParameter("boReContent");
		String boReRegdate = request.getParameter("boReRegdate");
		
		int boReGroupno = Integer.parseInt(request.getParameter("boReGroupno"));
		int boReOrderno = Integer.parseInt(request.getParameter("boReOrderno"));
		int boReDepth = Integer.parseInt(request.getParameter("boReDepth"));
		
		BoardReview bean = new BoardReview();
		
		bean.setBoReGroupno(boReGroupno);
		bean.setBoReOrderno(boReOrderno +1);
		bean.setBoReDepth(boReDepth +1);
		
		bean.setBoReWriter(boReWriter);
		bean.setBoReSubject(boReSubject);
		bean.setBoReContent(boReContent);
		bean.setBoReRegdate(boReRegdate);
		
		int cnt = -1;
		BoardReDao dao = new BoardReDao();
		
		try {
			cnt = dao.ReplyData(bean, boReOrderno);
			new BoardReListController().doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
