package com.banchan.mall;

public class ProductTest {
	private int prNum;
	private String prName;
	private String prImage01;
	private int prStock;
	private int prPrice;
	
	public ProductTest() {
		// TODO Auto-generated constructor stub
	}

	public int getPrNum() {
		return prNum;
	}

	public void setPrNum(int prNum) {
		this.prNum = prNum;
	}

	public String getPrName() {
		return prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public String getPrImage01() {
		return prImage01;
	}

	public void setPrImage01(String prImage01) {
		this.prImage01 = prImage01;
	}

	public int getPrStock() {
		return prStock;
	}

	public void setPrStock(int prStock) {
		this.prStock = prStock;
	}

	public int getPrPrice() {
		return prPrice;
	}

	public void setPrPrice(int prPrice) {
		this.prPrice = prPrice;
	}

	@Override
	public String toString() {
		return "ProductTest [prNum=" + prNum + ", prName=" + prName + ", prImage01=" + prImage01 + ", prStock="
				+ prStock + ", prPrice=" + prPrice + "]";
	}
}
