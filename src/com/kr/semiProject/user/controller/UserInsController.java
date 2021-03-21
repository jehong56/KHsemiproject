package com.kr.semiProject.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kr.semiProject.user.model.UserDAOImpl;
import com.kr.semiProject.user.model.UserVO;

@WebServlet("/userIns.do")
public class UserInsController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("userId");
		String pw = request.getParameter("userPw");
		String name = request.getParameter("userName");
		String birth = request.getParameter("userBirth");
		String gender = request.getParameter("userGender");
		String phone = request.getParameter("userPhone");
		String addr = request.getParameter("userAddr");
		String email = request.getParameter("userEmail");
		
		// MemberVO
		UserVO userVO = new UserVO();
		userVO.setUserId(id);
		userVO.setUserPw(pw);
		userVO.setUserName(name);
		userVO.setUserBirth(birth);
		userVO.setUserGender(gender);
		userVO.setUserPhone(phone);
		userVO.setUserAddr(addr);
		userVO.setUserEmail(email);
		
		// UserDAO - userIns
		UserDAOImpl userDAO = new UserDAOImpl();
		int result = userDAO.userIns(userVO);
		if(result == 1) {
			response.sendRedirect("/semiProject_Final/userInsComplete.jsp");
		}else if(result == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			
			script.println("<script>");
			script.println("alert('양식을 확인해주세요.');");
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
