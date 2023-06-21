package com.banchan.controller.cart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.model.CartItem;

public class CartListController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String message = null;
					
		Map<Integer, Integer> cartList = mycart.GetAllCartList();
		int totalAmount = 0;
		
		List<CartItem> orderList = new ArrayList<>();
		try {
			Set<Integer> keySet = cartList.keySet();
			
			for(Integer prNum : keySet) {
				Integer qty = cartList.get(prNum);
				
			}
			
			System.out.println("orderList : " + orderList);
			
			super.session.setAttribute("totalAmount", totalAmount);
			super.session.setAttribute("orderList", orderList);
			
			super.GotoPage("cart/cartList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
