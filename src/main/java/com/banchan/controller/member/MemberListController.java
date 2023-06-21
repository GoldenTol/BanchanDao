package com.banchan.controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.MemberDao;
import com.banchan.model.Member;
import com.banchan.utility.Paging;

public class MemberListController extends SuperClass{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String mode = request.getParameter("mode");
		String keyword = request.getParameter("keyword");
		
		MemberDao dao = new MemberDao();
		List<Member> lists = null;
		boolean isGrid = false;
		
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword);
			String url = request.getContextPath() + "/bMall?command=meList";
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			
			lists = dao.SelectAll(pageInfo);
			
			request.setAttribute("datalist", lists);
			request.setAttribute("pageInfo", pageInfo);
			
			String gotopage = "member/meList.jsp";
			super.GotoPage(gotopage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
