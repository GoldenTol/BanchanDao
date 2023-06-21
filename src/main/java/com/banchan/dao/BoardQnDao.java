package com.banchan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.banchan.model.BoardQna;
import com.banchan.utility.Paging;


public class BoardQnDao extends SuperDao{

	public int InsertData(BoardQna bean) throws Exception {
		System.out.println(bean);
		
		int cnt = -1;
		
		String sql = " insert into boQna(boQnNo, boQnWriter, boQnSubject, boQnContent, boQnRegdate, boQnReadhit, boQnRemark, boQnGroupno, boQnOrderno, boQnDepth)";
		sql += " values(myboQna.nextval, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), default, ?, myboQna.currval, default, default)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn==null) {conn=super.getConnection();} 
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getBoQnWriter());
		pstmt.setString(2, bean.getBoQnSubject());
		pstmt.setString(3, bean.getBoQnContent());
		pstmt.setString(4, bean.getBoQnRegdate());
		pstmt.setString(5, bean.getBoQnRemark());
		
		cnt = pstmt.executeUpdate();
		
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}

		return cnt;
	}

	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		System.out.println("검색할 필드명 : " + mode);
		System.out.println("검색할 키워드 : " + keyword + "\n");
		
		String sql = " select count(*) as cnt from boQna ";
		
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

	public List<BoardQna> SelectAll(Paging pageInfo) throws Exception {
		String sql = " select *";
		sql += " from(select boQnNo, boQnWriter, boQnSubject, boQnContent, boQnRegdate, boQnReadhit, boQnRemark, boQnGroupno, boQnOrderno, boQnDepth,";
		sql += " rank() over(order by boQnGroupno desc, boQnOrderno asc) as ranking";
		sql += " from boQna";
		
		String mode = pageInfo.getMode();
		String keyword = pageInfo.getKeyword();
		
		if(mode.equals("all") == false) { 
			sql += " where " + mode + " like '%" + keyword + "%'";
			
		}
		sql += " )";
		sql += " where ranking between ? and ?";
		
		List<BoardQna> lists =new ArrayList<BoardQna>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, pageInfo.getBeginRow());
		pstmt.setInt(2, pageInfo.getEndRow());
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		
		return lists;
	}

	private BoardQna makeBean(ResultSet rs) throws Exception {
		BoardQna bean = new BoardQna();
		
		bean.setBoQnNo(rs.getInt("boQnNo"));
		bean.setBoQnWriter(rs.getString("boQnWriter"));
		bean.setBoQnSubject(rs.getString("boQnSubject"));
		bean.setBoQnContent(rs.getString("boQnContent"));
		bean.setBoQnRegdate(rs.getString("boQnRegdate"));
		bean.setBoQnReadhit(rs.getInt("boQnReadhit"));
		bean.setBoQnRemark(rs.getString("boQnRemark"));
		bean.setBoQnGroupno(rs.getInt("boQnGroupno"));
		bean.setBoQnOrderno(rs.getInt("boQnOrderno"));
		bean.setBoQnDepth(rs.getInt("boQnDepth"));
		
		
		return bean;
	}

	public BoardQna GetDataByPk(int boQnNo) throws Exception {
		System.out.println("찾고자 하는 글번호 : " + boQnNo);
		
		String sql = " select * from boQna";
		sql += " where boQnNo = ? ";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		
		if(conn==null) {conn = super.getConnection();}
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boQnNo);
		rs = pstmt.executeQuery();
		
		BoardQna bean = null;
		
		if(rs.next()) {
			bean = this.makeBean(rs);
			
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		
		return bean;
	}

	public int UpdateReadhit(int boQnNo) throws Exception {
		int cnt = -1 ;
		String sql = " update boQna set boQnRegdate = boQnRegdate + 1 where boQnNo = ?" ; 

		
		Connection conn = null ;
		PreparedStatement pstmt = null ;		
		if(conn==null) {conn=super.getConnection();}		
		conn.setAutoCommit(false); 		
		pstmt=conn.prepareStatement(sql);
		
		pstmt.setInt(1, boQnNo) ;
		
		cnt = pstmt.executeUpdate() ;		
		conn.commit();		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}		
		return cnt ;
	}

	public int UpdateData(BoardQna bean) throws Exception{
		System.out.println(bean);
		
		int cnt = -1;
			
		String sql = " update boQna set";
		sql += " boQnWriter = ?, boQnSubject = ?, boQnContent = ?, boQnRegdate = TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), boQnReadhit = ?, boQnGroupno = ?, boQnOrderno = ?, boQnDepth = ?";
		sql += " where boQnNo = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null; 
		
		if(conn==null) {conn=super.getConnection();} 
			
		conn.setAutoCommit(false);
			
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, bean.getBoQnWriter());
		pstmt.setString(2, bean.getBoQnSubject());
		pstmt.setString(3, bean.getBoQnContent());
		pstmt.setString(4, bean.getBoQnRegdate());
		pstmt.setInt(5, bean.getBoQnReadhit());
		pstmt.setInt(6, bean.getBoQnGroupno());
		pstmt.setInt(7, bean.getBoQnOrderno());
		pstmt.setInt(8, bean.getBoQnDepth());
		pstmt.setInt(9, bean.getBoQnNo());
			
		cnt = pstmt.executeUpdate();
		conn.commit();
			
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}

		return cnt;
	}

	public int GetReplyCount(int boQnGroupno) throws Exception {
		System.out.println("검색할 boQnOrderno : " + boQnGroupno);
		
		String sql = " select count(*) as cnt from boQna where boQnGroupno = ?";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		Connection conn = null;
		int cnt = -1;
		
		if(conn==null) {conn = super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, boQnGroupno);
		rs = pstmt.executeQuery();

		if(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(rs != null){rs.close();}
		if(pstmt != null){pstmt.close();}
		if(conn != null){conn.close();}
		
		return cnt;
	}

	public int ReplyData(BoardQna bean, int boQnOrderno) throws Exception {
		System.out.println(bean);
		
		int cnt = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		if(conn==null) {conn=super.getConnection();} 
		conn.setAutoCommit(false);
		
		String sql1 = " update boards set boQnOrderno = boQnOrderno +1";
		sql1 += " where boQnGroupno = ? and boQnOrderno > ?";
		
		pstmt = conn.prepareStatement(sql1);
		pstmt.setInt(1, bean.getBoQnGroupno());
		pstmt.setInt(2, boQnOrderno);
		
		cnt = pstmt.executeUpdate();
		pstmt=null;
		
		String sql2 = " insert into boQna(boQnNo, boQnWriter, boQnSubject, boQnContent, boQnRegdate, boQnGroupno, boQnOrderno, boQnDepth)";
		sql2 += " values(myboard.nextval, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD HH24:MI:SS'), ?, ?, ?)";
			
		pstmt = conn.prepareStatement(sql2);
		
		pstmt.setString(1, bean.getBoQnWriter());
		pstmt.setString(2, bean.getBoQnSubject());
		pstmt.setString(3, bean.getBoQnContent());
		pstmt.setString(4, bean.getBoQnRegdate());
		pstmt.setInt(5, bean.getBoQnGroupno());
		pstmt.setInt(6, bean.getBoQnOrderno());
		pstmt.setInt(7, bean.getBoQnDepth());
			
		cnt = pstmt.executeUpdate();
		conn.commit();
			
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}

		return cnt;
	}

	public int DeleteData(int boQnNo) throws Exception {
		System.out.println("삭제할 게시글 번호 : " + boQnNo);
		
		int cnt = -1;
		String sql = " delete from boQna where boQnNo = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null; 
		if(conn==null) {conn=super.getConnection();} 
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, boQnNo);
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		return cnt;
	}
}
