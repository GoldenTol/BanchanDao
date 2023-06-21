package com.banchan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.banchan.model.Category;



public class CategoryDao extends SuperDao{

	public List<Category> GetCategoryList() throws Exception{
		// 카테고리 목록을 읽어서 반환해 줍니다.
		String sql = " select * from categories " ; 
		
		List<Category> lists = new ArrayList<Category>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		if(conn==null){conn=super.getConnection();}
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery() ;
		
		while(rs.next()) {
			lists.add(this.makeBean(rs)) ;
		}
		
		if(rs!=null){rs.close();}
		if(pstmt!=null){pstmt.close();}
		if(conn!=null){conn.close();}
	
		System.out.println("category item size : " + lists.size()); 
		return lists;
	}

	private Category makeBean(ResultSet rs) throws  Exception {
		Category bean = new Category() ;
		
		bean.setKorname(rs.getString("korname"));
		
		return bean;
	}

	

}
