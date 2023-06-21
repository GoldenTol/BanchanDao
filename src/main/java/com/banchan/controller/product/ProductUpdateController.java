package com.banchan.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.ProductDao;
import com.banchan.model.Product;
import com.oreilly.servlet.MultipartRequest;

public class ProductUpdateController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		int num = 0 ;
		ProductDao dao = null ;
		Product bean = null ;
		try {
			dao = new ProductDao();
			num = Integer.parseInt(request.getParameter("num"));//수정할 상품 번호
			//수정 시 꼭 필요한 문장 
			bean = dao.GetDataByPk(num);
			if(bean!=null) {
				request.setAttribute("bean", bean);
			}
			super.GotoPage("product/prUpdateForm.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			super.GotoPage("product/prListAll.jsp");
		}
	}

	
	
	
	@Override	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		MultipartRequest mr = (MultipartRequest)request.getAttribute("mr");
		
		Product bean = new  Product();
		
		//상품 등록과는 다르게 수정은 상품 번호를 챙겨야 합니다.
		bean.setNum(this.getNumberData(mr.getParameter("num")));
		
		bean.setName(mr.getParameter("name"));
		bean.setLimtdate(mr.getParameter("limtdate"));
		bean.setComments(mr.getParameter("comments"));		
		bean.setRemark(mr.getParameter("remark"));
		bean.setCategory(mr.getParameter("category"));
		bean.setInputdate(mr.getParameter("inputdate"));
		
		bean.setImage01(mr.getFilesystemName("image01"));
	
		bean.setKcal(this.getNumberData(mr.getParameter("kcal")));
		bean.setStock(this.getNumberData(mr.getParameter("stock")));
		bean.setPrice(this.getNumberData(mr.getParameter("price")));
		bean.setPoint(this.getNumberData(mr.getParameter("point")));
		bean.setGram(this.getNumberData(mr.getParameter("gram")));
		bean.setAvg(this.getNumberData(mr.getParameter("avg")));
		
		ProductDao dao = new ProductDao();
		int cnt = -1 ;
		try {
			cnt = dao.UpdateDate(bean);
			
			if(cnt==-1) {
				super.GotoPage("product/prUpdateForm.jsp");
			}else {
				String gotopage = super.getUrlInfo("prListAll");
				gotopage += "&pageNumber=" + mr.getParameter("pageNumber");
				gotopage += "&pageSize=" + mr.getParameter("pageSize");
				gotopage += "&moder=" + mr.getParameter("moder");
				gotopage += "&keyword=" + mr.getParameter("keyword");
				
				response.sendRedirect(gotopage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int getNumberData(String parameter) {
		boolean flag = false ;
		
		flag = parameter== null || parameter.equals("") || parameter.equals("null") ;
		
		System.out.println(this.getClass() + "getNumberData method called");
		
		return !flag ? Integer.parseInt(parameter) : 0;
				
		
	}
}
