package com.banchan.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.banchan.model.BoardReview;
import com.banchan.utility.Paging;

public class BoardReDao extends SuperDao{

	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검색할 키워드 : " + keyword + "\n");
		
		String sql = " select count(*) as cnt from boReview ";
		
		if(mode.equals("all") == false) { // 전체 보기 모드가 아니면
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		
		System.out.println(sql);
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		int cnt = -1;
		
		if(conn==null) {conn = super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return cnt;
	}

	public List<BoardReview> SelectAll(Paging pageInfo) throws Exception {
		String sql = " select *";
		sql += " from (select boReNo, boReWriter, boReSubject, boReContent, boReRegdate, boReReadhit, boReRemark, boReGroupno, boReOrderno, boReDepth,";
		sql += " rank() over(order by boReGroupno desc, boReOrderno asc) as ranking";
		sql += " from boReview";
		
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
		
		if (mode.equals("all") == false) { // 조건 검색
			sql += " where " + mode + " like '%" + keyword + "%'";
		}

		sql += " )";
		sql += " where ranking between ? and ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BoardReview> lists = new ArrayList<>();

		if (conn == null) {
			conn = super.getConnection();
		}
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());

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

	private BoardReview makeBean(ResultSet rs) throws Exception {
		BoardReview bean = new BoardReview();
		
		bean.setBoReNo(rs.getInt("boReNo"));
		bean.setBoReWriter(rs.getString("boReWriter"));
		bean.setBoReSubject(rs.getString("boReSubject"));
		bean.setBoReContent(rs.getString("boReContent"));
		bean.setBoReRegdate(rs.getString("boReRegdate"));
		bean.setBoReReadhit(Integer.parseInt(rs.getString("boReReadhit")));
		bean.setBoReRemark(rs.getString("boReRemark"));
		bean.setBoReGroupno(Integer.parseInt(rs.getString("boReGroupno")));
		bean.setBoReOrderno(Integer.parseInt(rs.getString("boReOrderno")));
		bean.setBoReDepth(Integer.parseInt(rs.getString("boReDepth")));
		
		return bean;
	}

	public int InsertData(BoardReview bean) throws Exception {
		String sql = " insert into boReview(boReNo, boReWriter, boReSubject, boReContent, boReRegdate, boReReadhit, boReRemark, boReGroupno, boReOrderno, boReDepth)";
		sql += " values(myboReview.nextval, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), default, ?, myboReview.currval, default, default)";
		
		int cnt =-1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn == null) {conn = super.getConnection();}
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bean.getBoReWriter());
		pstmt.setString(2, bean.getBoReSubject());
		pstmt.setString(3, bean.getBoReContent());
		pstmt.setString(4, bean.getBoReRegdate());
		pstmt.setString(5, bean.getBoReRemark());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	public BoardReview GetDataByPk(int boReNo) throws Exception {
		System.out.println("찾고자 하는 글번호 : " + boReNo);
		
		String sql = " select * from boReview";
		sql += " where boReNo = ? ";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boReNo);
		rs = pstmt.executeQuery();
		
		BoardReview bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
			
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		
		return bean;
	}

	public int UpdateReadhit(int boReNo) throws Exception {
		int cnt = -1 ;
		
		String sql = " update boReview set boReRegdate = boReRegdate + 1 where boReNo = ?" ;

		
		Connection conn = null ;
		PreparedStatement pstmt = null ;		
		if(conn==null) {conn=super.getConnection();}		
		conn.setAutoCommit(false); 		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, boReNo) ;
		
		cnt = pstmt.executeUpdate() ;		
		conn.commit();		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}		
		return cnt ;
	}

	public int ReplyData(BoardReview bean, int boReOrderno) throws Exception {
		System.out.println(bean);
		
		int cnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		if(conn==null) {conn=super.getConnection();} 
		conn.setAutoCommit(false);
		
		String sql1 = " update boReview set boReOrderno = boReOrderno +1";
		sql1 += " where boReGroupno = ? and boReOrderno > ?";
		
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1, bean.getBoReGroupno());
		pstmt.setInt(2, boReOrderno);
		
		cnt = pstmt.executeUpdate();
		pstmt=null;
		
		String sql2 = " insert into boReview(boReNo, boReWriter, boReSubject, boReContent, boReRegdate, boReGroupno, boReOrderno, boReDepth)";
		sql2 += " values(myboReview.nextval, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), ?, ?, ?)";
			
		pstmt = conn.prepareStatement(sql2);
		
		pstmt.setString(1, bean.getBoReWriter());
		pstmt.setString(2, bean.getBoReSubject());
		pstmt.setString(3, bean.getBoReContent());
		pstmt.setString(4, bean.getBoReRegdate());
		pstmt.setInt(5, bean.getBoReGroupno());
		pstmt.setInt(6, bean.getBoReOrderno());
		pstmt.setInt(7, bean.getBoReDepth());
			
		cnt = pstmt.executeUpdate();
		conn.commit();
			
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}

		return cnt;
	}

	public int GetReplyCount(int boReGroupno) throws Exception {
		System.out.println("검색할 boReOrderno : " + boReGroupno);
		
		String sql = " select count(*) as cnt from boReview where boReGroupno = ?";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		int cnt = -1;
		
		if(conn==null) {conn = super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, boReGroupno);
		rs = pstmt.executeQuery();

		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return cnt;
	}

	public int UpdateData(BoardReview bean) throws Exception {
		System.out.println(bean);
		
		int cnt = -1;
			
		String sql = " update boReview set";
		sql += " boReWriter = ?, boReSubject = ?, boReContent = ?, boReRegdate = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), boReReadhit = ?, boReGroupno = ?, boReOrderno = ?, boReDepth = ?";
		sql += " where boReNo = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null; 
		
		if(conn==null) {conn=super.getConnection();} 
			
		conn.setAutoCommit(false);
			
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getBoReWriter());
		pstmt.setString(2, bean.getBoReSubject());
		pstmt.setString(3, bean.getBoReContent());
		pstmt.setString(4, bean.getBoReRegdate());
		pstmt.setInt(5, bean.getBoReReadhit());
		pstmt.setInt(6, bean.getBoReGroupno());
		pstmt.setInt(7, bean.getBoReOrderno());
		pstmt.setInt(8, bean.getBoReDepth());
		pstmt.setInt(9, bean.getBoReNo());
			
		cnt = pstmt.executeUpdate();
		conn.commit();
			
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}

		return cnt;
	}

	public int DeleteData(int boReNo) throws Exception {
		System.out.println("삭제할 게시글 번호 : " + boReNo);
		
		int cnt = -1;
		
		String sql = " delete from boReview where boReNo = ?";
	
		Connection conn = null;
		PreparedStatement pstmt = null; 
		if(conn==null) {conn=super.getConnection();} 
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, boReNo);
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		return cnt;
	}
}
