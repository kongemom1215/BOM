package com.spring.bom.service.iron;

import com.spring.bom.model.iron.User_Info;

public interface User_InfoService {

	User_Info getLoginUserInfo(int ucode);

	User_Info getUserInfoUatid(User_Info user);
	
}