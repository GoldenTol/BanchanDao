package com.banchan.controller.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardReDao;
import com.banchan.model.BoardReview;

public class BoardReInsertController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pattern = "yyyy/MM/dd HH:mm:ss";
		String regdate = new SimpleDateFormat(pattern).format(new Date());
		
		request.setAttribute("regdate", regdate);
		
		super.GotoPage("board/boReInsertForm.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String boReWriter = request.getParameter("boReWriter");
		String boReSubject = request.getParameter("boReSubject");
		String boReContent = request.getParameter("boReContent");
		String boReRegdate = request.getParameter("boReRegdate");
		
		System.out.println("boReRegdate : " + boReRegdate);
		
		String boReRemark = request.getParameter("boReRemark");
		
		BoardReDao dao = new BoardReDao();
		
		BoardReview bean = new BoardReview();
		
		bean.setBoReWriter(boReWriter);
		bean.setBoReSubject(boReSubject);
		bean.setBoReContent(boReContent);
		bean.setBoReRegdate(boReRegdate);
		bean.setBoReRemark(boReRemark);
		
		int cnt = -1;
		
		try {
			cnt = dao.InsertData(bean);
			if(cnt == -1) {
			new BoardReInsertController().doGet(request, response);
			
			}else {
				new BoardReListController().doGet(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
