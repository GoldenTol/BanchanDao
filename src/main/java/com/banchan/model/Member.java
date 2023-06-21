package com.banchan.model;

public class Member {
	private String id;
	private String name;
	private String password;
	private String address;
	private String age;
	private String family;
	private String regdate; /* 날짜이지만 문자열로 처리 */
	private int mpoint;
	private String remark;
	
	public Member(String id, String name, String password, String address, String age, String family, String regdate,
			int mpoint, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.age = age;
		this.family = family;
		this.regdate = regdate;
		this.mpoint = mpoint;
		this.remark = remark;
	}
	
	public Member() {
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getMpoint() {
		return mpoint;
	}
	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", address=" + address + ", age="
				+ age + ", family=" + family + ", regdate=" + regdate + ", mpoint=" + mpoint + ", remark=" + remark
				+ "]";
	}
}
