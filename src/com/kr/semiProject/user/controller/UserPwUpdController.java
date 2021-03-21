package com.kr.semiProject.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kr.semiProject.user.model.UserDAOImpl;

@WebServlet("/userPwUpd.do")
public class UserPwUpdController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		
		UserDAOImpl userDAO = new UserDAOImpl();
		int result = userDAO.userPwUpd(id, pw);

		if(result == 1) {
			response.sendRedirect("/semiProject_Final/userPwUpdComplete.jsp");
		}else {
			response.sendRedirect("/semiProject_Final/userFindFailed.jsp");
		}
	}
}
