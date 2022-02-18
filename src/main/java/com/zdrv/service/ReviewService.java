package com.zdrv.service;

import java.util.List;

import com.zdrv.domain.Inquiry;
import com.zdrv.domain.Review;

public interface ReviewService {
	
	List<Review> getAllReview();
	
	List<Review> allGetById(int userId);
	List<Review> animeGetById(int animeId);
	Review getById(int userId,int animeId);
	void insertRe(Review review);
	void deleteByIdRe(int userId,int animeId);
	void updateRe(Review review);
	void getInquiry(Inquiry inquiry);
	
}
