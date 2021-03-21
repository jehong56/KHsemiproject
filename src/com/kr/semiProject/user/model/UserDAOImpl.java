package com.kr.semiProject.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAOImpl implements UserDAO{

	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/pool01");
		return ds.getConnection();
	}
	
	@Override
	public int userIns(UserVO userVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "INSERT INTO SEMI_USER VALUES(?,?,?,?,?,?,?,?,0)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userVO.getUserId());
			pstmt.setString(2, userVO.getUserPw());
			pstmt.setString(3, userVO.getUserName());
			pstmt.setString(4, userVO.getUserGender());
			pstmt.setString(5, userVO.getUserBirth());
			pstmt.setString(6, userVO.getUserPhone());
			pstmt.setString(7, userVO.getUserAddr());
			pstmt.setString(8, userVO.getUserEmail());
			
			int result = pstmt.executeUpdate();
			return result;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
	@Override
	public UserVO getUser(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		UserVO userVO = null;
		String query ="SELECT * FROM SEMI_USER WHERE USER_ID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				userVO = new UserVO();
				userVO.setUserId(rset.getString("USER_ID"));
				userVO.setUserPw(rset.getString("USER_PW"));
				userVO.setUserName(rset.getString("USER_NAME"));
				userVO.setUserGender(rset.getString("USER_GENDER"));
				userVO.setUserBirth(rset.getString("USER_BIRTH"));
				userVO.setUserPhone(rset.getString("USER_PHONE"));
				userVO.setUserAddr(rset.getString("USER_ADDRESS"));
				userVO.setUserEmail(rset.getString("USER_EMAIL"));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userVO;
	}
	@Override
	public int doUserUpdate(String userId, String userPw, String userName, String userGender, String userBirth, String userPhone, String userAddr, String userEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query ="UPDATE SEMI_USER SET USER_PW=?, USER_NAME=?, USER_GENDER=?, USER_BIRTH=?, USER_PHONE=?, USER_ADDRESS=?, USER_EMAIL=? WHERE USER_ID=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, userPw);
			pstmt.setString(2, userName);
			pstmt.setString(3, userGender);
			pstmt.setString(4, userBirth);			
			pstmt.setString(5, userPhone);
			pstmt.setString(6, userAddr);
			pstmt.setString(7, userEmail);
			pstmt.setString(8, userId);
			
			result = pstmt.executeUpdate();
			
			return result;
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	@Override
	public int userLogin(String userId, String userPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT USER_ID, USER_PW FROM SEMI_USER WHERE USER_ID=? AND USER_PW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);			

			rset = pstmt.executeQuery();
			
			if(!rset.next()) {
				return 0;
			}else {
				return 1;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
				try {
					if(rset != null) rset.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return -1;
	}
	
	@Override
	public int checkRole(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int userRole;
		String query = "SELECT USER_ROLE FROM SEMI_USER WHERE USER_ID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);		

			rset = pstmt.executeQuery();
			
			if(!rset.next()) {
				return -1;
			}else {
				userRole = rset.getInt(1);
				return userRole;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
				try {
					if(rset != null) rset.close();
					if(pstmt != null) pstmt.close();
					if(conn != null) conn.close();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
		}
		return -2;
	}
	
	@Override
	public String getUserId(String userName, String userEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT USER_ID FROM SEMI_USER WHERE USER_NAME=? AND USER_EMAIL=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userName);
			pstmt.setString(2, userEmail);
			
			rset = pstmt.executeQuery();
			
			if(!rset.next()) {
				return "";
			}else {
				return rset.getString(1);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rset != null) rset.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	@Override
	public String getUserPw(String userId, String userName, String userEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT USER_PW FROM SEMI_USER WHERE USER_ID=? AND USER_NAME=? AND USER_EMAIL=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userName);
			pstmt.setString(3, userEmail);
			
			rset = pstmt.executeQuery();
			if(!rset.next()) {
				return "";
			}else {
				return rset.getString(1);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(rset != null) rset.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	@Override
	public int userPwUpd(String userId, String userPw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE SEMI_USER SET USER_PW=? WHERE USER_ID=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userPw);
			pstmt.setString(2, userId);
			
			result = pstmt.executeUpdate();
			return result;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
}
