package com.banchan.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardReDao;

public class BoardReDeleteController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int boReNo = Integer.parseInt(request.getParameter("boReNo"));
		
		BoardReDao dao = dao = new BoardReDao();
		
		int cnt = -1;
		
		try {
			cnt = dao.DeleteData(boReNo);
			
			if(cnt==-1) {
				new BoardReListController().doGet(request, response);
			}else {
				new BoardReListController().doGet(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
