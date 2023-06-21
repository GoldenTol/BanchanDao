package com.banchan.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardReDao;
import com.banchan.model.BoardReview;
import com.banchan.utility.Paging;

public class BoardReController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		if(mode == null) {mode = "all";}
		if(keyword == null) {keyword = "";}
		
		BoardReDao dao = new BoardReDao();
		List<BoardReview> lists = null;
boolean isGrid = false;
		
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword);
			String url = request.getContextPath() + "/bMall?command=boList";
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
		
			lists = dao.SelectAll(pageInfo);
			
			request.setAttribute("lists", lists);
			request.setAttribute("pageInfo", pageInfo);
			
			String gotoPage = "board/boReList.jsp";
			super.GotoPage(gotoPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
