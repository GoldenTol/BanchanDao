package com.banchan.controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.controller.product.ProductDetailController;
import com.banchan.dao.ReviewDao;
import com.banchan.model.Review;

public class ReviewInsertController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		Review rebean = new Review();
		ReviewDao redao = new ReviewDao();

		rebean.setGroupno(Integer.parseInt(request.getParameter("num"))); // 상품 번호 받는곳
		rebean.setWriter(request.getParameter("writer")); 
		rebean.setSubject(request.getParameter("subject"));
		rebean.setComments(request.getParameter("comments"));
		rebean.setImage01(request.getParameter("image01"));
		rebean.setCategory(request.getParameter("category"));
		rebean.setGrade(Double.parseDouble(request.getParameter("grade")));

				
		int cnt = -1;

		try {
			cnt = redao.InsertData(rebean);
			
			new ProductDetailController().doGet(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
