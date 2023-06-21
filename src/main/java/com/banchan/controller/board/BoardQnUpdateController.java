package com.banchan.controller.board;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardQnDao;
import com.banchan.model.BoardQna;

public class BoardQnUpdateController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int boQnNo = 0;
		BoardQnDao dao = null;
		BoardQna bean = null;
		
		try {
			dao = new BoardQnDao();
			boQnNo = Integer.parseInt(request.getParameter("boQnNo"));
			
			bean = dao.GetDataByPk(boQnNo);
			
			if(bean!=null) {
				request.setAttribute("bean", bean);	
			}
			super.GotoPage("board/boQnUpdateForm.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			super.GotoPage("board/boQnList.jsp");
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		BoardQna bean = new BoardQna();
		System.out.println(Integer.parseInt(request.getParameter("boQnNo")));
		
		bean.setBoQnNo(Integer.parseInt(request.getParameter("boQnNo")));
		bean.setBoQnWriter(request.getParameter("boQnWriter"));
		bean.setBoQnSubject(request.getParameter("boQnSubject"));
		bean.setBoQnContent(request.getParameter("boQnContent"));
		bean.setBoQnRegdate(request.getParameter("boQnRegdate")); // 작성일자
		bean.setBoQnReadhit(Integer.parseInt(request.getParameter("boQnReadhit"))); // 조회수
		bean.setBoQnRemark(request.getParameter("boQnRemark"));
		bean.setBoQnGroupno(Integer.parseInt(request.getParameter("boQnGroupno"))); // 게시글 그룹 번호
		bean.setBoQnOrderno(Integer.parseInt(request.getParameter("boQnOrderno"))); // 게시할 순번
		bean.setBoQnDepth(Integer.parseInt(request.getParameter("boQnDepth")));
		
		BoardQnDao dao = new BoardQnDao();
		int cnt = -1;
		
		try {
			cnt = dao.UpdateData(bean);
			if(cnt==-1) {
				super.GotoPage("board/boQnUpdateForm.jsp");
			}else {
				String gotopage = super.getUrlInfo("boQnList");
				gotopage += "&pageNumber=" + request.getParameter("pageNumber");
				gotopage += "&pageSize=" + request.getParameter("pageSize");
				gotopage += "&mode=" + request.getParameter("mode");
				gotopage += "&keyword=" + request.getParameter("keyword");
				
				response.sendRedirect(gotopage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
