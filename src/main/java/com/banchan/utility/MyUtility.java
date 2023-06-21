package com.banchan.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.banchan.controller.SuperController;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MyUtility {

	public static Map<String, String> getSettingMap(String workingFile) {
		// workingFile을 이용하여 자바의 맵 형식으로 반환
		Map<String, String> map = new HashMap<>();
		Properties prop = null;
		
		prop = getProrpertiesData(workingFile);
		
		Enumeration<Object> keys = prop.keys();
		while(keys.hasMoreElements()) {
			String key = keys.nextElement().toString();
			String value = prop.getProperty(key);
			map.put(key, value);
		}
		
		return map;
	}
	
	private static Properties getProrpertiesData(String workingFile) {
		// 스트림을 이용하여 프로퍼티 목록 반환
		FileInputStream fis = null;
		Properties properties = null;
		
		try {
			fis = new FileInputStream(workingFile);
			InputStreamReader reader = new InputStreamReader(fis, "UTF-8"); // 한글 깨짐 방지
			
			properties = new Properties();
			properties.load(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {fis.close();}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		System.out.println("properties.toString()");
		System.out.println(properties.toString());
		
		return properties;
	}

	public static Map<String, SuperController> getTodoListMap(String workingFile) {
		// 쇼핑몰에서 사용할 todoList를 맵 형식으로 반환
		Map<String, SuperController> map = new HashMap<String, SuperController>();
		Properties prop = null;
		
		prop = getProrpertiesData(workingFile);
		
		Enumeration<Object> enunTrans = prop.keys();
		
		while(enunTrans.hasMoreElements()) {
			String command = enunTrans.nextElement().toString();
			String className = prop.getProperty(command);
			System.out.println(command + "/" + className);
		
			try {
				Class<?> handleClass = Class.forName(className);
	
				SuperController instance = (SuperController)handleClass.newInstance();
				
				map.put(command, instance);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return map;
	}

	public static MultipartRequest getMultipartRequest(HttpServletRequest request, String realPath) {
		// 이미지 파일 업로드를 위한 멀티 파트 객체 생성 후 반환
		MultipartRequest mr = null;
		int maxPostSize = 10 * 1024 * 1024; 
		final String ENCODING = "UTF-8";
		
		try {
			mr = new MultipartRequest(
					request,
					realPath,
					maxPostSize,
					ENCODING,
					new DefaultFileRenamePolicy()); // 파일 중복 방지
		} catch (Exception e) {
			e.printStackTrace();
		}
		   
		return mr;
	}

	public static void deleteOldImageFile(String realPath, MultipartRequest mr) {
		// 상품 수정 시 과거에 업로드한 이미지들을 웹 서버에서 삭제
		String[] deleteImage = 
			{
				mr.getParameter("deleteImage01"), 
				mr.getParameter("deleteImage02"), 
				mr.getParameter("deleteImage03")
			};
		
		for (int i = 0; i < deleteImage.length; i++) {
			if(deleteImage[i] != null) {
				String deleteFile = realPath + "\\" + deleteImage[i];
				File delFile = new File(deleteFile);
				if(delFile.delete()) {
					System.out.println(deleteFile + " file delete success");
				}
			}
		}
	}

	public static String getCurrentTime() {
	    String pattern = "yyyy년 MM월 dd일 hh시";
	    return new SimpleDateFormat(pattern).format(new Date()) + " ";
	}
}

