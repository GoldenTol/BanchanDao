package com.banchan.controller.mall;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;

public class MallDetailController extends SuperClass{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
			
		if(loginfo==null) {
			super.youNeededLogin();
			return ;
		}
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
