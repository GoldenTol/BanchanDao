package com.banchan.controller.product;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.ProductDao;
import com.banchan.model.Product;
import com.banchan.utility.Paging;


public class ProductListController extends SuperClass {
	@Override // 상품 목록을 조회합니다.
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String pageNumber = request.getParameter("pageNumber") ;
		String pageSize = request.getParameter("pageSize") ;
		String mode = request.getParameter("mode") ;
		String keyword = request.getParameter("keyword") ;
		boolean isGrid = true ;
		
		ProductDao dao = new ProductDao();
		List<Product> lists = null ;
		try {
			int totalCount = dao.GetTotalRecordCount(mode, keyword) ;
			String url = super.getUrlInfo("prListAll");
			
			Paging pageInfo = new Paging(pageNumber, pageSize, totalCount, url, mode, keyword, isGrid);
			System.out.println(this.getClass());
			System.out.println(pageInfo); 

			// lists = dao.SelectAll(); // 이전 버전
			lists = dao.SelectAll(pageInfo);	
			
			request.setAttribute("datalist", lists);
			request.setAttribute("pageInfo", pageInfo); 
			
			super.GotoPage("product/prListAll.jsp"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
