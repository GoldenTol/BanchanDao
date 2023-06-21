package com.banchan.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardQnDao;

public class BoardQnDeleteController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		int boQnNo = Integer.parseInt(request.getParameter("boQnNo"));
		BoardQnDao dao = new BoardQnDao();
		int cnt = -1;
		
		try {
			cnt = dao.DeleteData(boQnNo);
			new BoardQnListController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
