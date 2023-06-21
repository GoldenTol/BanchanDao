package com.banchan.controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;

public class CartInsertController extends SuperClass{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);

		String message = null;
		
		//int prStock = Integer.parseInt(request.getParameter("prStock"));
		//int prQty = Integer.parseInt(request.getParameter("prQty"));
		int prStock = 80;
		int prQty = 5;
		
		if(prStock < prQty) {
			message = "재고 수량 부족";
			super.setAlertMessage(message);
			
			// 상품 페이지 이동
		}else {
			//int num = Integer.parseInt(request.getParameter("num"));
			int num = 3;
			super.mycart.AddCart(num, prQty);
			super.session.setAttribute("myCart", mycart);
			
			new CartListController().doGet(request, response);
		}
	}
}
