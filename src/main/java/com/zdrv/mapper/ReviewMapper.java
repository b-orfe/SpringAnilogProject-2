package com.zdrv.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zdrv.domain.Inquiry;
import com.zdrv.domain.Review;

@Mapper
public interface ReviewMapper {
	//一覧
	List<Review> selectAll();
	
	List<Review> allSelectById(int userId);
	List<Review> animeSelectById(int animeId);
	Review selectById(int userId,int animeId);

	//追加
	void insert(Review review);

	//削除
	void deleteById(@Param("userId")int userId,@Param("animeId")int animeId);

	//更新
	void update(Review review);
	
	void addInquiry(Inquiry inquiry);

}
