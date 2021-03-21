package com.kr.semiProject.user.model;

public interface UserDAO {

	public int userIns(UserVO userVO);

	public UserVO getUser(String userId);
	
	public int doUserUpdate(
			String userId, String userPw,
			String userName, String userGender,
			String userBirth, String userPhone,
			String userAddr, String userEmail);
	
	public int userLogin(String userId, String userPw);

	public int checkRole(String userId);
	
	public String getUserId(String userName, String userEmail);
	
	public String getUserPw(String userId, String userName, String userEmail);
	
	public int userPwUpd(String userId, String userPw);
}

