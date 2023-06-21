package com.banchan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.banchan.model.CartItem;
import com.banchan.model.Pay;

public class PayDao extends SuperDao{
	public int InsertData(Pay bean) throws Exception{
		int cnt = -1;
		
		String sql = " insert into pays(prNum, prDate, prName, prAllPrice, address)";
		sql += " values(myPays.nextval, TO_CHAR(TO_DATE('1970-01-01', 'YYYY-MM-DD') + numtodsinterval(? + 32400, 'SECOND'), 'YYYY-MM-DD HH24:MI:SS'), ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		if(conn == null) {conn = super.getConnection();}
		conn.setAutoCommit(false);
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bean.getPrDate());
		pstmt.setString(2, bean.getPrName());
		pstmt.setInt(3, bean.getPrAllPrice());
		pstmt.setString(4, bean.getAddress());
		
		cnt = pstmt.executeUpdate();
		conn.commit();
		
		if(pstmt != null) {pstmt.close();}
		if(conn != null) {conn.close();}
		
		return cnt;
	}

	public List<Pay> getPayList() throws Exception{
		String sql = " select * from pays order by prNum desc";
		
		List<Pay> lists = new ArrayList<Pay>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		if(conn == null) {conn = super.getConnection();}
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
	
	private Pay makeBean(ResultSet rs) throws Exception{
		Pay bean = new Pay();
		
		bean.setPrNum(rs.getInt("prNum"));
		bean.setPrDate(String.valueOf(rs.getString("prDate")));
		bean.setPrName(rs.getString("prName"));
		bean.setPrAllPrice(rs.getInt("prAllPrice"));
		bean.setAddress(rs.getString("address"));
		
		return bean;
	}
}
