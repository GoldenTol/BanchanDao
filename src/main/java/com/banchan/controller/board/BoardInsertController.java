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

public class BoardInsertController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pattern = "yyyy/MM/dd HH:mm:ss";
		String regdate = new SimpleDateFormat(pattern).format(new Date());
		
		request.setAttribute("regdate", regdate);
		
		super.GotoPage("board/boLiInsertForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String regdate = request.getParameter("regdate");
		
		System.out.println("regdate : " + regdate);
		
		String remark = request.getParameter("remark");

		BoardListDao bDao = new BoardListDao();
		BoardList bList = new BoardList();
		bList.setBoLiWriter(writer);
		bList.setBoLiSubject(subject);
		bList.setBoLiContent(content);
		bList.setBoLiRegdate(regdate);
		bList.setBoLiRemark(remark);
		
		int cnt = -1;
		
		try {
			cnt = bDao.InsertData(bList);
			
			if(cnt == -1) {
				new BoardInsertController().doGet(request, response);
			}else {
				new BoardListController().doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
