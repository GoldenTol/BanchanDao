package com.banchan.model;

public class Category {
	private String korname ; // 한글 이름
	
	public Category() {
		
	}

	
	public String getKorname() {
		return korname;
	}


	public void setKorname(String korname) {
		this.korname = korname;
	}


	@Override
	public String toString() {
		return "Category [korname=" + korname + "]";
	}
	
	
	
}
