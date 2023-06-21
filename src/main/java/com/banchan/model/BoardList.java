package com.banchan.model;

public class BoardList {
	private int boLiNo;
	private String boLiWriter;
	private String boLiSubject;
	private String boLiContent;
	private String boLiRegdate;
	private int boLiReadhit; 
	private String boLiRemark;
	private int boLiGroupno;
	private int boLiOrderno;
	private int boLiDepth;
	
	public BoardList() {

	}
	
	public int getBoLiNo() {
		return boLiNo;
	}
	public void setBoLiNo(int boLiNo) {
		this.boLiNo = boLiNo;
	}
	public String getBoLiWriter() {
		return boLiWriter;
	}
	public void setBoLiWriter(String boLiWriter) {
		this.boLiWriter = boLiWriter;
	}
	public String getBoLiSubject() {
		return boLiSubject;
	}
	public void setBoLiSubject(String boLiSubject) {
		this.boLiSubject = boLiSubject;
	}
	public String getBoLiContent() {
		return boLiContent;
	}
	public void setBoLiContent(String boLiContent) {
		this.boLiContent = boLiContent;
	}
	public String getBoLiRegdate() {
		return boLiRegdate;
	}
	public void setBoLiRegdate(String boLiRegdate) {
		this.boLiRegdate = boLiRegdate;
	}
	public int getBoLiReadhit() {
		return boLiReadhit;
	}
	public void setBoLiReadhit(int boLiReadhit) {
		this.boLiReadhit = boLiReadhit;
	}
	public String getBoLiRemark() {
		return boLiRemark;
	}
	public void setBoLiRemark(String boLiRemark) {
		this.boLiRemark = boLiRemark;
	}
	public int getBoLiGroupno() {
		return boLiGroupno;
	}
	public void setBoLiGroupno(int boLiGroupno) {
		this.boLiGroupno = boLiGroupno;
	}
	public int getBoLiOrderno() {
		return boLiOrderno;
	}
	public void setBoLiOrderno(int boLiOrderno) {
		this.boLiOrderno = boLiOrderno;
	}
	public int getBoLiDepth() {
		return boLiDepth;
	}
	public void setBoLiDepth(int boLiDepth) {
		this.boLiDepth = boLiDepth;
	}
	@Override
	public String toString() {
		return "BoardList [boLiNo=" + boLiNo + ", boLiWriter=" + boLiWriter + ", boLiSubject=" + boLiSubject
				+ ", boLiContent=" + boLiContent + ", boLiRegdate=" + boLiRegdate + ", boLiReadhit=" + boLiReadhit
				+ ", boLiRemark=" + boLiRemark + ", boLiGroupno=" + boLiGroupno + ", boLiOrderno=" + boLiOrderno
				+ ", boLiDepth=" + boLiDepth + "]";
	}
	
	
	
	
}
