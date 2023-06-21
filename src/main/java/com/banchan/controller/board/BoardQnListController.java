package com.banchan.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardQnDao;
import com.banchan.model.BoardQna;
import com.banchan.utility.Paging;

public class BoardQnListController extends SuperClass{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		String name = "";
		
		if(mode==null) {mode="all";}
		if(keyword==null) {keyword="";}
		
		BoardQnDao dao = new BoardQnDao();
		List<BoardQna> lists = null;
		boolean isGrid = false;
		
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword);
			String url = super.getUrlInfo("boQnList");
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			System.out.println("필드 검색 파라미터 확인 : ");
			System.out.println(pageInfo.getFlowParameter());
			
			lists = dao.SelectAll(pageInfo);
			
			request.setAttribute("datalist", lists);
			request.setAttribute("pageInfo", pageInfo);
			request.setAttribute("name", name);
			
			String gotopage = "board/boQnList.jsp";
			super.GotoPage(gotopage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
