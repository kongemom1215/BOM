package com.spring.bom.dao.iron;

import com.spring.bom.model.iron.User_Info;

public interface User_InfoDao {

	User_Info getLoginUserInfo(int ucode);

	User_Info getUserInfoUatid(User_Info user);

	int getUserfollowCount(User_Info someone);

	int getUserfollowerCount(User_Info someone);

	int editProfileData(User_Info ui);

}
