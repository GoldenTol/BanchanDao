
package com.banchan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.banchan.model.Product;
import com.banchan.model.Review;

public class ReviewDao extends SuperDao {

	private Review makeBean(ResultSet rs) throws Exception {
		Review bean = new Review();
		double result = 0.0; 

		bean.setNum(rs.getInt("num"));
		bean.setWriter(rs.getString("writer"));
		bean.setSubject(rs.getString("subject"));
		bean.setComments(rs.getString("comments"));
		bean.setImage01(rs.getString("image01"));
		bean.setCategory(rs.getString("category"));
		bean.setInputdate(String.valueOf(rs.getDate("inputdate")));
		//리뷰 등급 항목은 0.0~10.0까지 입력 받으며 퍼센트 값으로 환산해서 넣어줍니다.
		bean.setGrade(rs.getDouble("grade")*10);	
		bean.setGroupno(rs.getInt("groupno"));

		return bean;
	}

	public List<Review> GetReviewList(int num) throws Exception {
		// 해당 상품 번호를 이용하여 리뷰 bean 객체 리스트를 반환해 줍니다.
		System.out.println("찾고자 하는 상품 번호: " + num);

		String sql = " select * from review";
		sql += " where groupno = ? ";

		List<Review> lists = new ArrayList<Review>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (conn == null) {
			conn = super.getConnection();
		}

		pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, num);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			lists.add(this.makeBean(rs));
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

		return lists;
	}

	public int InsertData(Review rebean) throws Exception {
		// 데이터베이스에 리뷰 데이터를 insert 합니다. 넘겨 받은 상품 번호와 그룹번호를 연동합니다.
		int cnt = -1;
		System.out.println(rebean);

		String sql = " insert into review(num, writer, subject, comments, image01, category, inputdate,grade,groupno)";
		sql += " values(mypreview.nextval, ?, ?, ?, ?, ? ,sysdate,?,? )";

		Connection conn = null;
		PreparedStatement pstmt = null;

		if (conn == null) {
			conn = super.getConnection();
		}
		conn.setAutoCommit(false);

		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, rebean.getWriter());
		pstmt.setString(2, rebean.getSubject());
		pstmt.setString(3, rebean.getComments());
		pstmt.setString(4, rebean.getImage01());
		pstmt.setString(5, rebean.getCategory());
		pstmt.setDouble(6, rebean.getGrade());
		pstmt.setInt(7, rebean.getGroupno());

		cnt = pstmt.executeUpdate();

		conn.commit();

		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		return cnt;
	
	}
}
