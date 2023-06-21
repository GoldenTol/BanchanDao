package com.banchan.controller.member;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.MemberDao;
import com.banchan.model.Member;

public class MemberInsertController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pattern = "yyyy/MM/dd";
		String regdate = new SimpleDateFormat(pattern).format(new Date());
		
		request.setAttribute("regdate", regdate);
		
		String gotoPage = "member/meInsertForm.jsp";
		super.GotoPage(gotoPage);
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		String age = request.getParameter("age");
		String family = request.getParameter("family");
		String regdate = request.getParameter("regdate");
		int mpoint = Integer.parseInt(request.getParameter("mpoint"));
		String remark = request.getParameter("remark");
		
		Member bean = new Member();
		
		bean.setId(id);
		bean.setName(name);
		bean.setPassword(password);
		bean.setAddress(address);
		bean.setAge(age);
		bean.setFamily(family);
		bean.setRegdate(regdate);
		bean.setMpoint(mpoint);
		bean.setRemark(remark);
		
		
		MemberDao dao = new MemberDao();
		int cnt = -1;
		
		try {
			cnt = dao.InsertData(bean);
			
			if(cnt==-1) {
				new MemberInsertController().doGet(request, response);
			}else {
				new MemberLoginController().doGet(request, response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			new MemberInsertController().doGet(request, response);
		}
	}
}
