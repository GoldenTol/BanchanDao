package com.banchan.model;

public class BoardReview {
	private int boReNo;
	private String boReWriter;
	private String boReSubject;
	private String boReContent;
	private String boReRegdate;
	private int boReReadhit;
	private String boReRemark;
	private int boReGroupno;
	private int boReOrderno;
	private int boReDepth;
	
	public BoardReview() {
	}

	public BoardReview(int boReNo, String boReWriter, String boReSubject, String boReContent, String boReRegdate,
			int boReReadhit, String boReRemark, int boReGroupno, int boReOrderno, int boReDepth) {
		super();
		this.boReNo = boReNo;
		this.boReWriter = boReWriter;
		this.boReSubject = boReSubject;
		this.boReContent = boReContent;
		this.boReRegdate = boReRegdate;
		this.boReReadhit = boReReadhit;
		this.boReRemark = boReRemark;
		this.boReGroupno = boReGroupno;
		this.boReOrderno = boReOrderno;
		this.boReDepth = boReDepth;
	}

	@Override
	public String toString() {
		return "BoardReview [boReNo=" + boReNo + ", boReWriter=" + boReWriter + ", boReSubject=" + boReSubject
				+ ", boReContent=" + boReContent + ", boReRegdate=" + boReRegdate + ", boReReadhit=" + boReReadhit
				+ ", boReRemark=" + boReRemark + ", boReGroupno=" + boReGroupno + ", boReOrderno=" + boReOrderno
				+ ", boReDepth=" + boReDepth + "]";
	}

	public int getBoReNo() {
		return boReNo;
	}

	public void setBoReNo(int boReNo) {
		this.boReNo = boReNo;
	}

	public String getBoReWriter() {
		return boReWriter;
	}

	public void setBoReWriter(String boReWriter) {
		this.boReWriter = boReWriter;
	}

	public String getBoReSubject() {
		return boReSubject;
	}

	public void setBoReSubject(String boReSubject) {
		this.boReSubject = boReSubject;
	}

	public String getBoReContent() {
		return boReContent;
	}

	public void setBoReContent(String boReContent) {
		this.boReContent = boReContent;
	}

	public String getBoReRegdate() {
		return boReRegdate;
	}

	public void setBoReRegdate(String boReRegdate) {
		this.boReRegdate = boReRegdate;
	}

	public int getBoReReadhit() {
		return boReReadhit;
	}

	public void setBoReReadhit(int boReReadhit) {
		this.boReReadhit = boReReadhit;
	}

	public String getBoReRemark() {
		return boReRemark;
	}

	public void setBoReRemark(String boReRemark) {
		this.boReRemark = boReRemark;
	}

	public int getBoReGroupno() {
		return boReGroupno;
	}

	public void setBoReGroupno(int boReGroupno) {
		this.boReGroupno = boReGroupno;
	}

	public int getBoReOrderno() {
		return boReOrderno;
	}

	public void setBoReOrderno(int boReOrderno) {
		this.boReOrderno = boReOrderno;
	}

	public int getBoReDepth() {
		return boReDepth;
	}

	public void setBoReDepth(int boReDepth) {
		this.boReDepth = boReDepth;
	}
}
