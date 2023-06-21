package com.banchan.model;

public class Pay {
	int prNum;
	String prDate;
	String address;
	String prName;
	int prAllPrice;
	
	public Pay() {
		
	}
	
	public int getPrNum() {
		return prNum;
	}
	public void setPrNum(int prNum) {
		this.prNum = prNum;
	}
	public String getPrDate() {
		return prDate;
	}
	public void setPrDate(String prDate) {
		this.prDate = prDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrName() {
		return prName;
	}
	public void setPrName(String prName) {
		this.prName = prName;
	}
	public int getPrAllPrice() {
		return prAllPrice;
	}
	public void setPrAllPrice(int prAllPrice) {
		this.prAllPrice = prAllPrice;
	}
	@Override
	public String toString() {
		return "Pay [prNum=" + prNum + ", prDate=" + prDate + ", address=" + address + ", prName=" + prName
				+ ", prAllPrice=" + prAllPrice + "]";
	}
}
