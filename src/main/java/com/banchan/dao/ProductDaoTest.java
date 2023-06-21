package com.banchan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.banchan.mall.ProductTest;
import com.banchan.model.CartItem;

public class ProductDaoTest extends SuperDao{
	private ProductTest getDataByPk(Integer prNum) throws Exception{
		// 상품 객체 반환
		System.out.println("찾고자 하는 상품 번호 : " + prNum);

		String sql = " select * from products";
		sql += " where prNum = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (conn == null) {
			conn = super.getConnection();
		}

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, prNum);

		rs = pstmt.executeQuery();

		ProductTest bean = null;

		if (rs.next()) {
			bean = this.makeBean(rs);
		}

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return bean;
	}

	public CartItem getCartItem(Integer prNum, Integer qty) throws Exception{
		ProductTest bean = this.getDataByPk(prNum);
		
		CartItem item = new CartItem();
		
		item.setMid(null);
		item.setPrname(bean.getPrName());
		item.setPrNum(bean.getPrNum());
		item.setPrPrice(bean.getPrPrice());
		item.setPrQty(bean.getPrStock());
		item.setPrStock(bean.getPrStock());
		
		return item;
	}
	
	private ProductTest makeBean(ResultSet rs) throws Exception{
		ProductTest bean = new ProductTest();
		bean.setPrNum(rs.getInt("prNum"));
		bean.setPrName(rs.getString("prName"));
		bean.setPrImage01(rs.getString("prImage01"));
		bean.setPrPrice(rs.getInt("prPrice"));
		bean.setPrStock(rs.getInt("prStock"));
		
		return bean;
	}
}
