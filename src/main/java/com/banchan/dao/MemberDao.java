package com.banchan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banchan.model.Member;
import com.banchan.utility.MyUtility;
import com.banchan.utility.Paging;

public class MemberDao extends SuperDao{

	public Member getDataByPk(String id) throws Exception {
		System.out.println("찾고자 하는 아이디 : " + id);
		String sql = " select * from members";
		sql += " where id = ? ";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		
		Member bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
			
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return bean;
	}

	private Member makeBean(ResultSet rs) throws Exception {
		Member bean = new Member();
		
		bean.setId(rs.getString("id"));
		bean.setName(rs.getString("name"));
		bean.setPassword(rs.getString("password"));
		bean.setAddress(rs.getString("address"));
		bean.setAge(rs.getString("age"));
		bean.setFamily(rs.getString("family"));
		bean.setRegdate(rs.getString("regdate"));
		bean.setMpoint(rs.getInt("mpoint"));
		bean.setRemark(rs.getString("remark"));
		
		return bean;
	}

	public Member SelectData(String id, String password) throws Exception {
		System.out.println(id + "/" + password);
		String sql = " select * from members";
		sql += " where id = ? and password = ?";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		
		rs = pstmt.executeQuery();
		
		Member bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return bean;
	}

	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검색할 키워드 : " + keyword);
		
		String sql = " select count(*) as cnt from members ";
		// 1) sql developer에서 데이터를 가져와서
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		int cnt = -1;
		// 3) int cnt값이 sql developer에서 가져온 값으로 바뀌어 출력
		
		if(conn==null) {conn = super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if(rs.next()) {
			cnt = rs.getInt("cnt");
			// 2) cnt에 값을 담고
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return cnt;
	}

	public List<Member> SelectAll(Paging pageInfo) throws Exception {
		String sql = " select * from members order by regdate desc";
		
		List<Member> lists = new ArrayList<Member>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if(rs != null) {rs.close();}
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		

		return lists;
	}
	
	public int InsertData(Member bean) throws Exception {
		System.out.println(bean);
		
		int cnt = -1;
		
		String sql = " insert into members(id, name, password, address, age, family, regdate, mpoint,remark)";
		sql += " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
	
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false);
		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getId());
		pstmt.setString(2, bean.getName());
		pstmt.setString(3, bean.getPassword());
		pstmt.setString(4, bean.getAddress());
		pstmt.setString(5, bean.getAge());
		pstmt.setString(6, bean.getFamily());
		pstmt.setString(7, bean.getRegdate());
		pstmt.setInt(8, bean.getMpoint());
		pstmt.setString(9, bean.getRemark());
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}
	
	public int DeleteData(String id) throws Exception{
		System.out.println("탈퇴한 아이디 " + id);
	      
		int cnt = -1;
	      
		Connection conn = null;
		PreparedStatement pstmt = null;

		if(conn==null) {conn=super.getConnection();}
		conn.setAutoCommit(false);
	      
		String sql = "";
	      
		Member bean = this.getDataByPk(id);
		String remark = MyUtility.getCurrentTime() + bean.getName() + "(아이디 : " + id + ")님이 탈퇴를 하였습니다.";
			      
		sql = " update boQna set boQnRemark = ? where boQnWriter = ?";
	      
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, remark);
		pstmt.setString(2, id);
	      
		cnt = pstmt.executeUpdate();
	      
		sql = " delete from members where id = ?";
	      
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
	      
		cnt = pstmt.executeUpdate();
	      
		conn.commit();
	      
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
	      
		return cnt;
	}

}
