package com.banchan.model;

public class Order {
	private int oid ;
	private String mid ;
	private String orderDate ;
	private String remark ;
	private String orderName ;
	private int orderAllPrice ;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public int getOrderAllPrice() {
		return orderAllPrice;
	}
	public void setOrderAllPrice(int orderAllPrice) {
		this.orderAllPrice = orderAllPrice;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", mid=" + mid + ", orderDate=" + orderDate + ", remark=" + remark + ", orderName="
				+ orderName + ", orderAllPrice=" + orderAllPrice + "]";
	}
	public Order(int oid, String mid, String orderDate, String remark, String orderName, int orderAllPrice) {
		super();
		this.oid = oid;
		this.mid = mid;
		this.orderDate = orderDate;
		this.remark = remark;
		this.orderName = orderName;
		this.orderAllPrice = orderAllPrice;
	}
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
}
