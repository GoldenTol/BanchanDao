package com.banchan.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.utility.MyUtility;
import com.oreilly.servlet.MultipartRequest;

//url 설정 및 초기 파라미터 파일 설정
@WebServlet(urlPatterns = {"/bMall"}, initParams = {
		@WebInitParam(name="txtSetting", value="/WEB-INF/settings.txt"),
		@WebInitParam(name="todoList", value="/WEB-INF/todoList.txt")
})
public class FrontController extends HttpServlet{
	private String txtSetting = null;
	private String todoList = null;
	
	private String realPath = null; // 이미지가 업로드 되는 웹 서버 상의 경로
	 
	private ServletContext application = null;
	
	// for settings.txt
	private Map<String, String> settingMap = null;
	
	// for todoList.txt
	private Map<String, SuperController> todoListMap = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println(this.getClass() + "init() 메소드 호출됨");
		
		// 초기화 파라미터 값 가져오기
		this.txtSetting = config.getInitParameter("txtSetting");
		System.out.println("setting file name : " + this.txtSetting);
		
		this.todoList = config.getInitParameter("todoList");
		System.out.println("controller file name : " + this.todoList);
	
		this.application = config.getServletContext();
	
		// 파라미터 파일 물리적 경로 얻기
		String txtSettingFile = this.application.getRealPath(txtSetting);
		System.out.println("setting fullpath name : " + txtSettingFile);
		
		System.out.println();
		
		String todoListFile = this.application.getRealPath(todoList);
		System.out.println("controller fullpath name : " + todoListFile);
		
		// Map 구조 변환
		this.settingMap = MyUtility.getSettingMap(txtSettingFile);
		System.out.println("setting file element size : " + this.settingMap.size());
		
		this.application.setAttribute("map", this.settingMap);
		
		this.todoListMap = MyUtility.getTodoListMap(todoListFile);
		System.out.println("controller file element size : " + this.todoListMap.size());
		
		// 업로드될 이미지 파일 절대 경로
		String imsiPath = this.settingMap.get("uploadPath");
		
		this.realPath = this.application.getRealPath(imsiPath);
		System.out.println("image upload realPath : " + realPath);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			this.doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8"); // 한글 깨짐 방지
		
		// 현재 값
		String command = request.getParameter("command");
		
		// 상품 등록일 경우 ( 멀티 파트 구조)
		if(command == null) {
			System.out.println("file upload event invoked");
			MultipartRequest mr = MyUtility.getMultipartRequest(request, realPath);
			
			if(mr != null) {
				command = mr.getParameter("command");
				
				MyUtility.deleteOldImageFile(realPath, mr);
				
				// 파일 업로드 객체를 requestScope에 저장
				request.setAttribute("mr", mr);
			}else {
				System.out.println("Multipart error");
			}
		}
		
		System.out.println("command is [" + command + "]");
		
		// 맵 구조 참조 후 함수 호출
		SuperController controller = this.todoListMap.get(command);
		
		if(controller != null) {
			// 전달 방식
			String method = request.getMethod().toLowerCase();
			
			if(method.equals("get")) {
				System.out.println(controller.toString() + " get method called");
				controller.doGet(request, response);
			}else {
				System.out.println(controller.toString() + " post method called");
				controller.doPost(request, response);
			}
		}else {
			System.out.println("request command is not found");
		}
	}
}
