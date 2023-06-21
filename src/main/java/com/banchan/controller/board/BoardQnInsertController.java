package com.banchan.controller.board;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.banchan.controller.SuperClass;
import com.banchan.dao.BoardQnDao;
import com.banchan.model.BoardQna;

public class BoardQnInsertController extends SuperClass{
   
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      super.doGet(request, response);
      
      String pattern = "yyyy/MM/dd HH:mm:ss";
      String regdate = new SimpleDateFormat(pattern).format(new Date());
      request.setAttribute("regdate", regdate);
      
      String gotopage = "board/boQnInsertForm.jsp";
      super.GotoPage(gotopage);
      
   }
   
   @Override
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      super.doPost(request, response);
      
      
      String boQnWriter = request.getParameter("boQnWriter");
      String boQnSubject = request.getParameter("boQnSubject");
       String boQnContent = request.getParameter("boQnContent");    
       String boQnRegdate = request.getParameter("boQnRegdate"); 
       
       System.out.println("boQnRegdate : " + boQnRegdate);

       String boQnRemark = request.getParameter("boQnRemark"); 
       
       BoardQna bean = new BoardQna();
       
       
       bean.setBoQnWriter(boQnWriter);
       bean.setBoQnSubject(boQnSubject);
       bean.setBoQnContent(boQnContent);
       bean.setBoQnRegdate(boQnRegdate);
       bean.setBoQnRemark(boQnRemark);
       
       BoardQnDao dao = new BoardQnDao();
       
       int cnt = -1;
       
       try {
         cnt = dao.InsertData(bean);
         if(cnt==-1) {
            new BoardQnInsertController().doGet(request, response);
         }else {
            new BoardQnListController().doGet(request, response);
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}