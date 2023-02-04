package com.tjoeun.interfaceTest2;

public class UserInfoOracleDAO implements UserInfoDAO {

	@Override
	public void insertUserInfo(UserInfoVO userInfoVO) {
		System.out.println("insert into ORACLE DB userId = " + userInfoVO.getUserId());
	}

	@Override
	public void updateUserInfo(UserInfoVO userInfoVO) {
		System.out.println("update into ORACLE DB userId = " + userInfoVO.getUserId());
	}

	@Override
	public void deleteUserInfo(UserInfoVO userInfoVO) {
		System.out.println("delete into ORACLE DB userId = " + userInfoVO.getUserId());
	}

}
