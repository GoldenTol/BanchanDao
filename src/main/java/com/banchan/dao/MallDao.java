package com.banchan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.banchan.model.Member;
import com.banchan.model.Order;
import com.banchan.model.ShoppingBasket;

public class MallDao extends SuperDao{

	public List<Order> getHistory(String id) throws Exception {
		String sql = " select * from orders where mid = ? order by oid desc";
		
		List<Order> lists = new ArrayList<Order>();
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		if(conn==null) {conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs= pstmt.executeQuery();
		
		while(rs.next()) {
			lists.add(this.makeOrderBean(rs));
		}
		
		if(rs!=null) {rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return lists;
	}


	private Order makeOrderBean(ResultSet rs) throws Exception {
		Order bean = new Order();
		
		bean.setOid(rs.getInt("oid"));
		bean.setMid(rs.getString("mid"));
		bean.setOrderAllPrice((rs.getInt("orderAllPrice")));
		bean.setOrderDate( String.valueOf( rs.getDate("orderDate")));
		bean.setRemark(rs.getString("remark"));
		bean.setOrderName(rs.getString("orderName"));
		
		return bean;
	}


	public void Calculate(Member payer, Map<Integer, Integer> cartList, int totalPoint) throws Exception {
		// payer : 계산을 하는 분
		//cartList : 카트에 담겨있는 상품 번호와 구매 수량
		//totalPoint : payer에게 적립해줄 포인트 
		

		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		int cnt = -1 ;
		String sql = "" ;
	
		if(conn==null) {conn = super.getConnection();}
		conn.setAutoCommit(false);
		
		//step01 : 주문 테이블(orders)에 매출 1건 입력
		sql = " insert into orders (oid, mid, remark ,orderDate)" ;
		sql += "values(seqoid.nextval,?,'',sysdate)" ;
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, payer.getId());
		
		cnt = pstmt.executeUpdate();
		if(pstmt != null) {pstmt.close();}
		
		//step02 : 1번에 들어간 송장 번호 읽기(why: 잠조 무결성 제약 조건)
		sql = " select max(oid) as invoice from orders " ;
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		int invoice = 0 ; // 송장번호
		if(rs.next()) {
			invoice = rs.getInt("invoice");
		}
		if(pstmt != null) {pstmt.close();}
		
		// 반복문을 사용하여
		Set<Integer> keylist = cartList.keySet() ;
		for(Integer pnum : keylist) {
			
			//step03 : 주문 상세 테이블(OrderDetails)에 데이터 추가
			sql = " insert into orderdetails(odid, oid, pnum, qty,remark)" ;
			sql += " values(seqodid.nextval,?,?,?,' ')" ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, invoice);  // 송장번호
			pstmt.setInt(2, pnum); // 상품 번호
			pstmt.setInt(3, cartList.get(pnum));  // qty 구매수량
			
			
			cnt = pstmt.executeUpdate();
			if(pstmt != null) {pstmt.close();}
			
			
			//step04 : 상품들의 재고 수량을 뺄셈하기
			sql = " update products set stock = stock - ? " ;
			sql += " where num = ?" ;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, cartList.get(pnum)); // qty 구매수량
			pstmt.setInt(2, pnum); // 상품 번호
			
			
			cnt = pstmt.executeUpdate();
			if(pstmt != null) {pstmt.close();}
			
		}
		
		//step05 : 회원에 대한 적립 포인트를 업데이트 하기
		sql = " update members set mpoint = mpoint + ? " ;
		sql += " where id = ? " ;
		
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, totalPoint); // 적립 포인트
		pstmt.setString(2, payer.getId()); // 회원 아이디
		
		cnt = pstmt.executeUpdate();
		if(pstmt != null) {pstmt.close();}
		
		conn.commit();
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
	}
	
	public List<ShoppingBasket> GetShoppingBasket(String id) throws Exception {
		// 나의 wishList 목록을 읽어 옵니다.
		System.out.println("카트 주인 : " + id );
		String sql = " select * from ShoppingBaskets where mid = ? " ;
		
		List<ShoppingBasket> lists = new ArrayList<ShoppingBasket>();
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,id);
		rs = pstmt.executeQuery() ;
			
		while(rs.next()) {
			lists.add(this.makeBean(rs));
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return lists;
	}	
	
	private ShoppingBasket makeBean(ResultSet rs) throws Exception{
		ShoppingBasket bean = new ShoppingBasket();
		
		bean.setMid(rs.getString("mid"));
		bean.setPnum(rs.getInt("pnum"));
		bean.setQty(rs.getInt("qty"));
		
		return bean;
	}

	public int InsertShoppingBasket(String id, Map<Integer, Integer> cartList) throws Exception {
		// 로그인 한 사람의 카트 목록(CartList)을 임시 테이블에 저장합니다.		
		System.out.println("카트 주인 : " + id);	
		
		// 회원에 대한 1건(bean 객체) 데이터를 데이터 베이스에 추가합니다.
		int cnt = -1 ;
		
		Connection conn = null ; 
		PreparedStatement pstmt = null ;
		
		if(conn==null) {conn=super.getConnection();}
		
		conn.setAutoCommit(false); // 오토 커밋 기능을 중지
		
		// step01 : 혹시 남아 있을 지 모르는 나의 이전 내역을 삭제합니다.
		String sql =  "";
		sql += " delete from ShoppingBaskets where mid = ? " ;
		
		pstmt=conn.prepareStatement(sql);		
		pstmt.setString(1, id);		
		cnt = pstmt.executeUpdate() ;//업데이트 전에 치환해야한다 
		
		if(pstmt!=null) {pstmt.close();}
		
		//step02 : 반복문을 사용하여 테이블에 인서트 합니다.
		Set<Integer> keylist = cartList.keySet() ;
		System.out.println("상품 개수 : " + keylist.size());
		
		sql = " insert into ShoppingBaskets(mid, pnum, qty)" ;
		sql += " values(?,?,?)" ;
		
		
		for(Integer pnum : keylist) {
			pstmt=conn.prepareStatement(sql);		
			
			pstmt.setString(1, id);		
			pstmt.setInt(2, pnum);
			pstmt.setInt(3, cartList.get(pnum));
			
			cnt = pstmt.executeUpdate() ;
			if(pstmt!=null) {pstmt.close();}
		}
		
		
		conn.commit();
		
		if(pstmt!=null) {pstmt.close();}
		if(conn!=null) {conn.close();}
		
		return cnt ;
	}

}
