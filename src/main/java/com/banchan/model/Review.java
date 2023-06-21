package com.banchan.model;

public class Review {
	private int num;
	private String writer;
	private String subject;
	private String comments;
	private String image01;
	private String category;
	private String inputdate;
	private double grade;
	private int groupno;
	
	public Review() {
	}

	@Override
	public String toString() {
		return "Review [num=" + num + ", writer=" + writer + ", subject=" + subject + ", comments=" + comments
				+ ", image01=" + image01 + ", category=" + category + ", inputdate=" + inputdate + ", grade=" + grade
				+ ", groupno=" + groupno + "]";
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getImage01() {
		return image01;
	}

	public void setImage01(String image01) {
		this.image01 = image01;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getInputdate() {
		return inputdate;
	}

	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}

	public int getGroupno() {
		return groupno;
	}

	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
}
