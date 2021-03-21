package com.kr.semiProject.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.kr.semiProject.user.model.UserDAOImpl;
import com.kr.semiProject.user.model.UserVO;

@WebServlet("/usersData.do")
public class UsersAjaxController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String userId = request.getParameter("user");
		
		UserDAOImpl userDAO = new UserDAOImpl();
		UserVO userVO = userDAO.getUser(userId);
		
		JSONObject jsonData = new JSONObject();

		if(userVO == null) {
			jsonData.put("userDup", "okay");
		}else {
			jsonData.put("userDup", "dup");
		}
		
		response.getWriter().append(jsonData.toJSONString());
	}
}
