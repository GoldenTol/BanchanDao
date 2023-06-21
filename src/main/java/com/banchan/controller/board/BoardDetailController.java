package com.banchan.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardListDao;
import com.banchan.model.BoardList;

public class BoardDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardListDao bDao = new BoardListDao();
		BoardList bean = null;
		
		try {
			bean = bDao.getData(no);
			
			if(bean == null) {
				new BoardListController().doGet(request, response);
			}else {
				request.setAttribute("bean", bean);
				super.GotoPage("board/boLiDetail.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
