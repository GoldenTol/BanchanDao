package com.banchan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SuperController {
	// 각 Controller들의 공통 메소드
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
