package com.banchan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.banchan.model.BoardList;
import com.banchan.model.Member;
import com.banchan.utility.Paging;

public class BoardListDao extends SuperDao{

	public int GetTotalRecordCount(String mode, String keyword) throws Exception{
		String sql = " select count(*) as cnt from boList";
		
		if(mode.equals("all") == false) { // 조건 검색
			sql += " where " + mode + " like '%" + keyword + "%'";
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = -1;
		
		if(conn == null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt;
	}

	public List<BoardList> SelectAll(Paging pageInfo) throws Exception{
		// TopN 구문 ( 게시물 번호 기준 )
		String sql = " select *";
		sql += " from (select boLiNo, boLiWriter, boLiSubject, boLiContent, boLiRegdate, boLiReadhit, boLiRemark, boLiGroupno, boLiOrderno, boLiDepth,";
		sql += " rank() over(order by boLiGroupno desc, boLiOrderno asc, boLiNo desc) as ranking";
		sql += " from boList";

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

		List<BoardList> lists = new ArrayList<>();

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

	public int InsertData(BoardList bList) throws Exception{
		int cnt = -1;
		
		String sql = " insert into boList(boLiNo, boLiWriter, boLiSubject, boLiContent, boLiRegdate, boLiReadhit, boLiRemark, boLiGroupno, boLiOrderno, boLiDepth)";
		sql += " values(myboList.nextval, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), default, ?, myboList.currval, default, default)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn == null) {conn = super.getConnection();}
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bList.getBoLiWriter());
		pstmt.setString(2, bList.getBoLiSubject());
		pstmt.setString(3, bList.getBoLiContent());
		pstmt.setString(4, bList.getBoLiRegdate());
		pstmt.setString(5, bList.getBoLiRemark());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	public int DeleteData(int no) throws Exception{
		int cnt = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn == null) {conn = super.getConnection();}
		conn.setAutoCommit(false);
		
		// 데이터 삭제
		String sql = " delete from boList where boLiNo = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		
		cnt = pstmt.executeUpdate();
		if(pstmt != null) {pstmt.close();}
		
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	public BoardList getData(int no) throws Exception{
		String sql = " select * from boList";
		sql += " where boLiNo = ? ";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		rs = pstmt.executeQuery();
		
		BoardList bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return bean;
	}
	
	private BoardList makeBean(ResultSet rs) throws Exception{
		BoardList bean = new BoardList();
		
		bean.setBoLiNo(rs.getInt("boLiNo"));
		bean.setBoLiWriter(rs.getString("boLiWriter"));
		bean.setBoLiSubject(rs.getString("boLiSubject"));
		bean.setBoLiContent(rs.getString("boLiContent"));
		bean.setBoLiRegdate(rs.getString("boLiRegdate"));
		bean.setBoLiReadhit(rs.getInt("boLiReadhit"));
		bean.setBoLiRemark(rs.getString("boLiRemark"));
		bean.setBoLiGroupno(rs.getInt("boLiGroupno"));
		bean.setBoLiOrderno(rs.getInt("boLiOrderno"));
		bean.setBoLiDepth(rs.getInt("boLiDepth"));
		
		return bean;
	}

	public int UpdateData(BoardList bean) throws Exception{
		int cnt = -1;
		
		String sql = " update boList set boLiWriter = ?, boLiSubject = ?, boLiContent = ?, boLiRegdate = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS')";
		sql += " where boLiNo = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn == null) {conn = super.getConnection();}
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bean.getBoLiWriter());
		pstmt.setString(2, bean.getBoLiSubject());
		pstmt.setString(3, bean.getBoLiContent());
		pstmt.setString(4, bean.getBoLiRegdate());
		pstmt.setInt(5, bean.getBoLiNo());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	public int getReplyCount(int groupno) throws Exception{
		String sql = " select count(*) as cnt from boList where boLiGroupno = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = -1;
		
		if(conn == null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, groupno);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt;
	}

	public int ReplyData(BoardList bean, int orderno) throws Exception{
		int cnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn == null) {conn = super.getConnection();}
		conn.setAutoCommit(false);
		
		String sql1 = " update boList set boLiOrderno = boLiOrderno + 1";
		sql1 += " where boLiGroupno = ? and boLiOrderno > ?";
		
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1, bean.getBoLiGroupno());
		pstmt.setInt(2, orderno);
		
		cnt = pstmt.executeUpdate();
		
		// insert) 해당 bean 객체를 이용하여 답글 작성
		pstmt = null;
		
		String sql2 = " insert into boList(boLiNo, boLiWriter, boLiSubject, boLiContent, boLiRegdate, boLiGroupno, boLiOrderno, boLiDepth)";
		sql2 += " values(myboList.nextval, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), ?, ?, ?)";
		
		pstmt = conn.prepareStatement(sql2);
		pstmt.setString(1, bean.getBoLiWriter());
		pstmt.setString(2, bean.getBoLiSubject());
		pstmt.setString(3, bean.getBoLiContent());
		pstmt.setString(4, bean.getBoLiRegdate());
		pstmt.setInt(5, bean.getBoLiGroupno());
		pstmt.setInt(6, bean.getBoLiOrderno());
		pstmt.setInt(7, bean.getBoLiDepth());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}
}
