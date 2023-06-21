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

public class BoardUpdateController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		BoardListDao bDao = new BoardListDao();
		BoardList bean = null;
		
		String pattern = "yyyy/MM/dd HH:mm:ss";
		String regdate = new SimpleDateFormat(pattern).format(new Date());
		
		try {
			bean = bDao.getData(no);
			
			request.setAttribute("bean", bean);
			request.setAttribute("regdate", regdate);
			super.GotoPage("board/boLiUpdateForm.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		int cnt = -1;
		
		BoardList bean = new BoardList();
		BoardListDao bDao = new BoardListDao();
		
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String regdate = request.getParameter("regdate");
		
		bean.setBoLiNo(this.getNumberData(request.getParameter("no")));
		bean.setBoLiReadhit(this.getNumberData(request.getParameter("readhit")));
		bean.setBoLiGroupno(this.getNumberData(request.getParameter("groupno")));
		bean.setBoLiOrderno(this.getNumberData(request.getParameter("orderno")));
		bean.setBoLiDepth(this.getNumberData(request.getParameter("depth")));
		bean.setBoLiWriter(writer);
		bean.setBoLiSubject(subject);
		bean.setBoLiContent(content);
		bean.setBoLiRegdate(regdate);
		
		try {
			cnt = bDao.UpdateData(bean);
			
			if(cnt == -1) {
				new BoardUpdateController().doGet(request, response);
			}else {
				String gotoPage = super.getUrlInfo("boList");
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
	
	// 숫자 값 데이터 검사
	private int getNumberData(String parameter) {
		boolean flag = false;

		// 숫자 값이 존재하지 않을 경우
		flag = parameter == null || parameter.equals("") || parameter.equals("null");

		System.out.println(this.getClass() + " getNumberData method called");

		return !flag ? Integer.parseInt(parameter) : 0;
	}
}
