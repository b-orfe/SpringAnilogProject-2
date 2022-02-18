package com.zdrv.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdrv.domain.User;
import com.zdrv.mapper.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper usermapper;
	

	@Override
	public User getLoginUser(User loginInfo) {
		User user = usermapper.findByLoginId(loginInfo.getLoginId());
		
		if(user==null) {
			System.out.println("ログインIDが不正");
			return null;
		}
		
		if(!BCrypt.checkpw(loginInfo.getLoginPass(), user.getLoginPass())) {
			System.out.println("パスワードが違います");
			return null;
		}
		
		
		
		return user;
	}


	@Override
	public void insertUser(User user) {
		
		usermapper.insert(user);
	}

}
