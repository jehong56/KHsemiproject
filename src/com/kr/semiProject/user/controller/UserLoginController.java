package com.kr.semiProject.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kr.semiProject.user.model.UserDAOImpl;

@WebServlet("/userLogin.do")
public class UserLoginController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		
		// MemberDAO - userLogin
		UserDAOImpl userDAO = new UserDAOImpl();
		int result = userDAO.userLogin(userId, userPw);
		if(result == 1) {
			int userRole = userDAO.checkRole(userId);
			HttpSession session = request.getSession();
			session.setAttribute("id", userId);
			session.setAttribute("role", userRole);
			session.setAttribute("password", userPw);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
	        dispatcher.forward(request, response);
		}else if(result == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('아이디와 비밀번호를 확인해주세요.');");
			script.println("history.back();");
			script.println("</script>");
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('데이터베이스 오류, 관리자에게 문의하세요.');");
			script.println("history.back();");
			script.println("</script>");
		}
	}
}
// role dao완성, 밥 먹고 로그인 시에 세션에서 사용할 role인자 받기 구현하자.