package com.banchan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.banchan.model.CartItem;
import com.banchan.model.Product;
import com.banchan.utility.MyUtility;
import com.banchan.utility.Paging;

public class ProductDao extends SuperDao {

	public CartItem getCartItem(Integer pnum, Integer qty)  throws Exception{
		// 해당 상품 번호에 대한 Product 정보를 CartItem 객체에 대입하고 반환해 줍니다.
		Product bean = this.GetDataByPk(pnum);
		
		CartItem item = new CartItem() ;
		 
		item.setImage01(bean.getImage01());
		item.setMid(null) ; // 임시 저장용 테이블과 관련 있음
		item.setPname(bean.getName());
		item.setPnum(pnum) ;
		item.setPoint(bean.getPoint());
		item.setPrice(bean.getPrice()) ;
		item.setQty(qty) ;
		
		 
		return item;
	}
	
	public int GetTotalRecordCount(String mode, String keyword) throws Exception {
		System.out.print("검색할 필드명 : " + mode);
		System.out.print(", 검색할 키워드 : " + keyword + "\n");
		
		String sql = " select count(*) as cnt from products " ;
		
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		int cnt = -1 ;
		
		if(conn==null) {conn = super.getConnection() ;}		
		pstmt = conn.prepareStatement(sql);					
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			cnt = rs.getInt("cnt") ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
	
		return cnt ;
	}

	private Product makeBean(ResultSet rs) throws Exception {
		Product bean = new Product();

		bean.setNum(rs.getInt("num"));
		bean.setName(rs.getString("name"));
		bean.setComments(rs.getString("comments"));
		bean.setImage01(rs.getString("image01"));
		bean.setStock(rs.getInt("stock"));
		bean.setPrice(rs.getInt("price"));
		bean.setPoint(rs.getInt("point"));
		bean.setCategory(rs.getString("category"));
		bean.setInputdate(String.valueOf(rs.getDate("inputdate")));
		
		 
		 bean.setKcal(rs.getInt("kcal"));
		 bean.setGram(rs.getInt("gram"));
		 bean.setBest(rs.getString("best"));
		 bean.setRemark(rs.getString("remark"));
		 bean.setLimtdate(String.valueOf(rs.getDate("limtdate")));
		
		
		return bean;
	}

	
	 public int insertData(Product bean) throws Exception { // bean 객체 1개를 등록 합니다.
	 System.out.println("객체 확인: " + bean); 
	 int cnt = -1;
	  
	 String sql = " insert into products(num,name,company,comments,image01,stock,price,point,category,inputdate,kcal,gram,best,remark,limtdate)";
	 sql += " values(products.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	 Connection conn = null; 
	 PreparedStatement pstmt = null;
	  
	  if (conn == null) { conn = super.getConnection(); }
	  
	  conn.setAutoCommit(false);
	  
	  pstmt = conn.prepareStatement(sql);
	  
	  pstmt.setString(1, bean.getName());	 
	  pstmt.setString(2, bean.getComments());
	  pstmt.setString(3,bean.getImage01());	
	  pstmt.setInt(4, bean.getStock());
	  pstmt.setInt(5,bean.getPrice());
	  pstmt.setInt(6, bean.getPoint());
	  pstmt.setString(7, bean.getCategory());
	  pstmt.setString(8, bean.getInputdate());
	  
	  pstmt.setInt(9, bean.getKcal());
	  pstmt.setInt(10, bean.getGram());
	  pstmt.setString(11, bean.getBest());
	  pstmt.setString(12, bean.getRemark());
	  pstmt.setString(13, bean.getLimtdate());
	  cnt = pstmt.executeUpdate();
	  
	  conn.commit();
	  
	  if (pstmt != null) { pstmt.close(); } if (conn != null) { conn.close(); }
	  
	  return cnt; }
	  
	 public int getTotalRecordCount(String mode, String keyword) throws Exception{
			System.out.print("검색할 필드명 : " + mode);
			System.out.print(", 검색할 키워드 : " + keyword + "\n");
			
			String sql = " select count(*) as cnt from products " ;
			
			PreparedStatement pstmt = null ;
			ResultSet rs= null ;
			Connection conn = null ;
			int cnt = -1 ;
			
			if(conn==null) {conn = super.getConnection() ;}		
			pstmt = conn.prepareStatement(sql);					
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt") ;
			}
			
			if(rs!=null){rs.close();}
			if(pstmt!=null){pstmt.close();}
			if(conn!=null){conn.close();}
			
			return cnt ;
		}

	public List<Product> SelectAll(Paging pageInfo) throws Exception {

		String sql = " select num, name, comments, image01, stock, price, point, category, inputdate, kcal, gram, best,remark, limtdate";
		sql += " from (select num, name, comments, image01, stock, price, point, category, inputdate, kcal, gram, best, remark, limtdate,";
		sql += " rank() over(order by num desc) as ranking ";
		sql += " from products)";
		sql += " where ranking between ? and ? ";
				 
		 
		List<Product> lists = new ArrayList<Product>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (conn == null) {conn = super.getConnection();}

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

	public Product GetdataByPk(int num) throws Exception{
		// 해당 상품 번호를 이용하여 상품 bean 객체를 반환해 줍니다.
		System.out.println("찾고자 하는 상품 번호: " + num);
		
		String sql = " select * from products" ;
		sql += " where num = ? " ;
		
		PreparedStatement pstmt = null ;
		ResultSet rs= null ;
		Connection conn = null ;
		
		if(conn==null) {conn = super.getConnection() ;}		
		pstmt = conn.prepareStatement(sql);		
		pstmt.setInt(1, num);		
		rs = pstmt.executeQuery();
		
		Product bean = null ;		
		if(rs.next()) {
			bean = this.makeBean(rs) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
		
		return bean;
	}	
	 
	 public int UpdateDate(Product bean) throws Exception { // bean 객체 1개를 등록 합니다.
	 System.out.println("객체 확인: " + bean); 
	 int cnt = -1;
	 
	  // remark 컬럼은 관리자가 상품 삭제시 자동으로 업데이트 됩니다.
	  
	  String sql = " update products set"; 
	  sql +=" name = ?, comments = ?, image01 = ? ,stock = ?, price = ?, point = ?,";
	  sql +=" category = ?, inputdate = ?, kcal = ?, gram = ?, best = ?, remark = ?, limtdate = ?"; 
	  sql +=" where num = ?";
	  
	
	  Connection conn = null;
	  PreparedStatement pstmt =	  null;	  
	  if (conn == null) { conn = super.getConnection(); }	  
	  conn.setAutoCommit(false);	  
	  pstmt = conn.prepareStatement(sql);
	  
	  pstmt.setString(1, bean.getName()) ;					
	  pstmt.setString(2, bean.getComments()) ;	
	  pstmt.setString(3, bean.getImage01()) ;		
	  pstmt.setInt(4, bean.getStock()) ;
	  pstmt.setInt(5, bean.getPrice()) ;
	  pstmt.setInt(6, bean.getPoint()) ;
	  pstmt.setString(7, bean.getCategory()) ;
	  pstmt.setString(8, bean.getInputdate()) ;
	 
	  pstmt.setInt(9, bean.getKcal()) ;
	  pstmt.setInt(10, bean.getGram()) ;
	  pstmt.setString(11, bean.getBest()) ;
	  pstmt.setString(12, bean.getRemark()) ;
	  pstmt.setString(13, bean.getLimtdate()) ;
	  pstmt.setInt(14, bean.getNum()) ;
	  
	  
	  
	  cnt = pstmt.executeUpdate();	  
	  conn.commit();	  
	  if (pstmt != null) { pstmt.close(); }
	  if (conn != null) { conn.close(); }
	  
	  return cnt; }
	 
	public List<Product> SelectAll() throws Exception {
		// 상품 목록을 조회하여 반환해 줍니다.
		// rownum은 한시적, 차후에 수정 예정임.

		String sql = " select * from products order by num desc";

		List<Product> lists = new ArrayList<Product>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		if (conn == null) {
			conn = super.getConnection();
		}
		pstmt = conn.prepareStatement(sql);
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

	public int InsertData(Product bean) throws Exception {
		// bean 객체 1개를 등록합니다.
		System.out.println(bean);
		int cnt = -1;

		String sql = " insert into products(num ,name ,comments ,image01 ,stock ,price ,point ,category,inputdate,kcal,gram,best,remark,limtdate)";
		sql += " values(myproduct.nextval , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		if (conn == null) {
			conn = super.getConnection();
		}
		conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, bean.getName());
		pstmt.setString(2, bean.getComments());
		pstmt.setString(3, bean.getImage01());
		pstmt.setInt(4, bean.getStock());
		pstmt.setInt(5, bean.getPrice());
		pstmt.setInt(6, bean.getPoint());
		pstmt.setString(7, bean.getCategory());
		pstmt.setString(8, bean.getInputdate());
		
		pstmt.setInt(9, bean.getKcal());
		pstmt.setInt(10, bean.getGram());
		pstmt.setString(11, bean.getBest());
		pstmt.setString(12, bean.getRemark());
		pstmt.setString(13, bean.getLimtdate());
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

	public Product GetDataByPk(int num) throws Exception {
		// 해당 상품 번호를 이용하여 상품 bean 객체를 반환해 줍니다.
		System.out.println("찾고자 하는 상품 번호: " + num);

		String sql = " select * from products";
		sql += " where num = ? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;

		if (conn == null) {
			conn = super.getConnection();
		}
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, num);
		
		rs = pstmt.executeQuery();

		Product bean = null;
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

	public int DeleteData(int num) throws Exception {
		// 관리자가 해당 상품 번호 num을 삭제하려고 시도합니다.
				// 참조 무결성 제약 조건을 확인하고, 주문 상세 테이블의 비고(remark) 컬럼에 변경 사항을 기록합니다.
				System.out.println("삭제할 상품 번호 : " + num);
				int cnt = -1 ;
				
				String sql = "" ;
				
				Connection conn = null ;
				PreparedStatement pstmt = null ;		
				if(conn==null) {conn=super.getConnection();}		
				conn.setAutoCommit(false);
				
				// step01 : 주문 상세 테이블의 비고 컬럼에 수정하기
				Product bean = this.GetDataByPk(num) ;		
				
				String remark = MyUtility.getCurrentTime() + bean.getName() + "(상품 번호 : " + num + "번) 상품이 삭제 되었습니다." ;
				
				sql = " update orderdetails set remark = ? where pnum = ? " ;		
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, remark);
				pstmt.setInt(2, num);
				cnt = pstmt.executeUpdate() ;		
				if(pstmt != null) {pstmt.close();}
				
				// step02 : 상품 테이블에서 해당 상품 번호 삭제하기		
				sql = " delete from products where num = ? " ;
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, num);		
				cnt = pstmt.executeUpdate() ;	
				
				conn.commit();		
				if(pstmt!=null) {pstmt.close();}
				if(conn!=null) {conn.close();}		
				return cnt ;
	}

}
