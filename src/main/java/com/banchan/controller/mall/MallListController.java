package com.banchan.controller.mall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.controller.product.ProductListController;
import com.banchan.dao.ProductDao;
import com.banchan.model.CartItem;

public class MallListController extends SuperClass {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String message = null ;
		
		if (super.loginfo==null) {
			message = "로그인이 필요한 서비스입니다.";
			super.setAlertMessage(message);
			super.GotoPage("member/meLoginForm.jsp");
			return ;
		}
		
		if(mycart.GetCartSize()==0) { // 카트가 비어 있으면
			message = "카트 목록이 없어서 상품 목록페이지로 이동합니다.";
			super.setAlertMessage(message);		
			new ProductListController().doGet(request, response);
		}else {
			// cartList는 내 카트의 품목 내역 정보입니다.
			// 상품번호, 구매수량
			 Map<Integer, Integer> cartList = mycart.GetAllCartList();
			 int totalAmount = 0 ; // 총 금액
			 int totalPoint = 0 ; // 총 적립 포인트
			 
			 // 내가 구매하고자 하는 모든 상품 리스트
			 // 상품의 키값으로 구한 상품의 상세내역
			 List<CartItem> orderList = new ArrayList<CartItem>();
			 
			 try {
				 //keySet는 상품 번호들만 저장하고 있는 Set 구조
				 // keySet 상품의  key 값
				 Set<Integer> keySet =  cartList.keySet() ;
				 
				 for(Integer pnum : keySet) { // pnum : 상품번호
					 Integer qty = cartList.get(pnum);
					 ProductDao pdao = new ProductDao();
					 CartItem item = pdao.getCartItem(pnum,qty) ;
					 
					 totalAmount += item.getPrice() * item.getQty();
					 totalPoint += item.getPoint() * item.getQty();
					 
					 orderList.add(item);
				 }
				 // 세션 영역에 정보들을 바인딩합니다.
				 super.session.setAttribute("totalAmount", totalAmount);
				 super.session.setAttribute("totalPoint", totalPoint);
				 super.session.setAttribute("orderList", orderList);
				 
				 System.out.println("총 금액 : " + totalAmount);
				 System.out.println("총 적립 포인트 : " + totalPoint);
				 System.out.println("주문 상품 개수 : " + orderList.size());
				 
				 super.GotoPage("member/cartList.jsp");
				 
			} catch (Exception e) {
				e.printStackTrace();
			}			 			
		}
	}
}
