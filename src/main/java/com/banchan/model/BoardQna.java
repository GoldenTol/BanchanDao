package com.banchan.model;

public class BoardQna {
	private int boQnNo;
	private String boQnWriter;
	private String boQnSubject;
	private String boQnContent; 
	private String boQnRegdate;
	private int boQnReadhit; // 조회수
	private String boQnRemark;
	private int boQnGroupno; // 게시글 그룹 번호
	private int boQnOrderno; // 게시할 순번
	private int boQnDepth; // 글의 깊이
	
	public BoardQna() {
		// TODO Auto-generated constructor stub
	}

	public BoardQna(int boQnNo, String boQnWriter, String boQnSubject, String boQnContent, String boQnRegdate,
			int boQnReadhit, String boQnRemark, int boQnGroupno, int boQnOrderno, int boQnDepth) {
		super();
		this.boQnNo = boQnNo;
		this.boQnWriter = boQnWriter;
		this.boQnSubject = boQnSubject;
		this.boQnContent = boQnContent;
		this.boQnRegdate = boQnRegdate;
		this.boQnReadhit = boQnReadhit;
		this.boQnRemark = boQnRemark;
		this.boQnGroupno = boQnGroupno;
		this.boQnOrderno = boQnOrderno;
		this.boQnDepth = boQnDepth;
	}

	@Override
	public String toString() {
		return "BoardQna [boQnNo=" + boQnNo + ", boQnWriter=" + boQnWriter + ", boQnSubject=" + boQnSubject
				+ ", boQnContent=" + boQnContent + ", boQnRegdate=" + boQnRegdate + ", boQnReadhit=" + boQnReadhit
				+ ", boQnRemark=" + boQnRemark + ", boQnGroupno=" + boQnGroupno + ", boQnOrderno=" + boQnOrderno
				+ ", boQnDepth=" + boQnDepth + "]";
	}

	public int getBoQnNo() {
		return boQnNo;
	}

	public void setBoQnNo(int boQnNo) {
		this.boQnNo = boQnNo;
	}

	public String getBoQnWriter() {
		return boQnWriter;
	}

	public void setBoQnWriter(String boQnWriter) {
		this.boQnWriter = boQnWriter;
	}

	public String getBoQnSubject() {
		return boQnSubject;
	}

	public void setBoQnSubject(String boQnSubject) {
		this.boQnSubject = boQnSubject;
	}

	public String getBoQnContent() {
		return boQnContent;
	}

	public void setBoQnContent(String boQnContent) {
		this.boQnContent = boQnContent;
	}

	public String getBoQnRegdate() {
		return boQnRegdate;
	}

	public void setBoQnRegdate(String boQnRegdate) {
		this.boQnRegdate = boQnRegdate;
	}

	public int getBoQnReadhit() {
		return boQnReadhit;
	}

	public void setBoQnReadhit(int boQnReadhit) {
		this.boQnReadhit = boQnReadhit;
	}

	public String getBoQnRemark() {
		return boQnRemark;
	}

	public void setBoQnRemark(String boQnRemark) {
		this.boQnRemark = boQnRemark;
	}

	public int getBoQnGroupno() {
		return boQnGroupno;
	}

	public void setBoQnGroupno(int boQnGroupno) {
		this.boQnGroupno = boQnGroupno;
	}

	public int getBoQnOrderno() {
		return boQnOrderno;
	}

	public void setBoQnOrderno(int boQnOrderno) {
		this.boQnOrderno = boQnOrderno;
	}

	public int getBoQnDepth() {
		return boQnDepth;
	}

	public void setBoQnDepth(int boQnDepth) {
		this.boQnDepth = boQnDepth;
	}
}
