package com.banchan.controller.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardListDao;
import com.banchan.model.BoardList;

public class BoardLiReplyController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pattern = "yyyy/MM/dd HH:mm:ss";
		String regdate = new SimpleDateFormat(pattern).format(new Date());
		
		int depth = Integer.parseInt(request.getParameter("depth"));
		int no = Integer.parseInt(request.getParameter("no"));
		
		if(depth == 3) {
			String message = "답글 깊이는 최대 3까지 입니다.";
			super.setAlertMessage(message);
			new BoardListController().doGet(request, response);
			return;
		}
		
		int replyCount = 0;
		int groupno = Integer.parseInt(request.getParameter("groupno"));
		BoardListDao dao = new BoardListDao();
		
		try {
			replyCount = dao.getReplyCount(groupno);
			
			request.setAttribute("no", no);
			request.setAttribute("regdate", regdate);
			
			super.GotoPage("board/boLiReplyForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject"); 
		String content = request.getParameter("content");
		String regdate = request.getParameter("regdate");
		
		// 필수 파라미터
		int groupno = Integer.parseInt(request.getParameter("groupno"));
		int orderno = Integer.parseInt(request.getParameter("orderno"));
		int depth = Integer.parseInt(request.getParameter("depth"));
		
		BoardList bean = new BoardList();
		
		bean.setBoLiGroupno(groupno); // 그룹 번호 동일
		bean.setBoLiOrderno(orderno + 1); // 정렬 순서 + 1
		bean.setBoLiDepth(depth + 1); // 글의 깊이 + 1
		
		bean.setBoLiWriter(writer);
		bean.setBoLiSubject(subject);
		bean.setBoLiContent(content);
		bean.setBoLiRegdate(regdate);
		
		int cnt = -1;
		BoardListDao dao = new BoardListDao();
		
		try {
			cnt = dao.ReplyData(bean, orderno);
			new BoardListController().doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
