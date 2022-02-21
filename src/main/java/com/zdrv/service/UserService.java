package com.zdrv.service;

import com.zdrv.domain.User;

public interface UserService {
	User getLoginUser(User userInfo);
	void insertUser(User user);
	void setUpdate(User user);
}
