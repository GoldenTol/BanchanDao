package com.banchan.model;

public class Product {
	private int num;
	private String name;
	private String comments;
	private String image01;
	private int stock;
	private int price;
	private int point;
	private String category;
	private String inputdate;
	private String limtdate;
	private int kcal;
	private int gram;
	private String best ;
	private double avg;	//평균을 저장해줄 인스턴스 변수.
	private String remark ;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
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
	public String getLimtdate() {
		return limtdate;
	}
	public void setLimtdate(String limtdate) {
		this.limtdate = limtdate;
	}
	public int getKcal() {
		return kcal;
	}
	public void setKcal(int kcal) {
		this.kcal = kcal;
	}
	public int getGram() {
		return gram;
	}
	public void setGram(int gram) {
		this.gram = gram;
	}
	public String getBest() {
		return best;
	}
	public void setBest(String best) {
		this.best = best;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Product(int num, String name, String comments, String image01, int stock, int price, int point,
			String category, String inputdate, String limtdate, int kcal, int gram, String best, double avg,
			String remark) {
		super();
		this.num = num;
		this.name = name;
		this.comments = comments;
		this.image01 = image01;
		this.stock = stock;
		this.price = price;
		this.point = point;
		this.category = category;
		this.inputdate = inputdate;
		this.limtdate = limtdate;
		this.kcal = kcal;
		this.gram = gram;
		this.best = best;
		this.avg = avg;
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Product [num=" + num + ", name=" + name + ", comments=" + comments + ", image01=" + image01 + ", stock="
				+ stock + ", price=" + price + ", point=" + point + ", category=" + category + ", inputdate="
				+ inputdate + ", limtdate=" + limtdate + ", kcal=" + kcal + ", gram=" + gram + ", best=" + best
				+ ", avg=" + avg + ", remark=" + remark + "]";
	}
	
	
	public Product() {
		// TODO Auto-generated constructor stub
	}
	

}
