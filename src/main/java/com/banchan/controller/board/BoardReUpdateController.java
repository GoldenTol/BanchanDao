package com.banchan.controller.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardReDao;
import com.banchan.model.BoardReview;

public class BoardReUpdateController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int boReNo = Integer.parseInt(request.getParameter("boReNo"));
		
		BoardReDao dao = new BoardReDao();
		BoardReview bean = null;
		
		String pattern = "yyyy/MM/dd HH:mm:ss";
		String boReRegdate = new SimpleDateFormat(pattern).format(new Date());
		
		try {
			bean = dao.GetDataByPk(boReNo);
			
			request.setAttribute("bean", bean);
			request.setAttribute("boReRegdate", boReRegdate);
			super.GotoPage("board/boReUpdateForm.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			super.GotoPage("board/boReList.jsp");
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		int cnt = -1;
		
		BoardReview bean = new BoardReview();
		System.out.println(Integer.parseInt(request.getParameter("boReNo")));
		
		BoardReDao dao = new BoardReDao();
		
		String boReWriter = request.getParameter("boReWriter");
		String boReSubject = request.getParameter("boReSubject");
		String boReContent = request.getParameter("boReContent");
		String boReRegdate = request.getParameter("boReRegdate");
		
		bean.setBoReNo(this.getNumberData(request.getParameter("boReNo")));
		bean.setBoReReadhit(this.getNumberData(request.getParameter("boReReadhit")));
		bean.setBoReGroupno(this.getNumberData(request.getParameter("boReGroupno")));
		bean.setBoReOrderno(this.getNumberData(request.getParameter("boReOrderno")));
		bean.setBoReDepth(this.getNumberData(request.getParameter("boReDepth")));
		bean.setBoReWriter(boReWriter);
		bean.setBoReSubject(boReSubject);
		bean.setBoReContent(boReContent);
		bean.setBoReRegdate(boReRegdate);
		
		try {
			cnt = dao.UpdateData(bean);
			
			if(cnt == -1) {
				super.GotoPage("board/boReUpdateForm.jsp");
			}else {
				String gotoPage = super.getUrlInfo("boReList");
				gotoPage += "&pageNumber=" + request.getParameter("pageNumber");
				gotoPage += "&pageSize=" + request.getParameter("pageSize");
				gotoPage += "&mode=" + request.getParameter("mode");
				gotoPage += "&keyword=" + request.getParameter("keyword");
				
				response.sendRedirect(gotoPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
			new BoardUpdateController().doGet(request, response);
		}
	}
	private int getNumberData(String parameter) {
		boolean flag = false;

		// 숫자 값이 존재하지 않을 경우
		flag = parameter == null || parameter.equals("") || parameter.equals("null");

		System.out.println(this.getClass() + " getNumberData method called");

		return !flag ? Integer.parseInt(parameter) : 0;
	}
}
