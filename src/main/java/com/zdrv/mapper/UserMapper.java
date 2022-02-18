package com.zdrv.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.zdrv.domain.User;

@Mapper
public interface UserMapper {
	User findByLoginId(String LoginId);
	void insert(User user);
}
