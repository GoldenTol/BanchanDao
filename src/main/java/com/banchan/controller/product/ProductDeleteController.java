package com.banchan.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.ProductDao;

public class ProductDeleteController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			super.doGet(request, response);
	
			int num = Integer.parseInt(request.getParameter("num"));
			ProductDao dao = new ProductDao();
			int cnt = -1 ;
			
			try {
				cnt = dao.DeleteData(num);
				
				new ProductListController().doGet(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

}
