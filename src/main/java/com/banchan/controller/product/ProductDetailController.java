package com.banchan.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.ProductDao;
import com.banchan.model.Product;

public class ProductDetailController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int num = Integer.parseInt(request.getParameter("num")) ;
		ProductDao dao = new ProductDao();
		System.out.println(num);
		
		try {
			Product bean = dao.GetDataByPk(num);
			request.setAttribute("bean", bean); 
			super.GotoPage("product/prDetail.jsp"); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
	}

}
