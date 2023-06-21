package com.banchan.controller.mall;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.controller.product.ProductListController;
import com.banchan.dao.MallDao;
import com.banchan.model.Order;

public class MallHistoryController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		if(loginfo==null) {
			super.youNeededLogin();
			return ;
		}
		
		MallDao madao = new MallDao();
						
		try {
			List<Order> lists = madao.getHistory(super.loginfo.getId());
			if(lists.size()==0) {
				super.setAlertMessage("이전 쇼핑 내역이 존재하지 않습니다.");
				new ProductListController().doGet(request, response);
			}else {
				request.setAttribute("lists", lists);
				super.GotoPage("mall/maHistory.jsp");
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
