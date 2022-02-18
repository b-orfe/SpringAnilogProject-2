package com.zdrv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdrv.domain.Inquiry;
import com.zdrv.domain.Review;
import com.zdrv.mapper.ReviewMapper;

@Service
@Transactional

public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	ReviewMapper reviewmapper;
	
	@Override
	public List<Review> getAllReview() {
		
		return reviewmapper.selectAll();
	}
	
	@Override
	public Review getById(int userId, int animeId) {
		
		return reviewmapper.selectById(userId, animeId);
	}
	

	@Override
	public void insertRe(Review review) {
		
		reviewmapper.insert(review);
	}

	@Override
	public void deleteByIdRe(int userId,int animeId) {
		reviewmapper.deleteById(userId,animeId);
		
	}

	@Override
	public void updateRe(Review review) {
		reviewmapper.update(review);
		
	}

	@Override
	public List<Review> allGetById(int userId) {
		
		return reviewmapper.allSelectById(userId);
	}

	@Override
	public List<Review> animeGetById(int animeId) {
		
		return reviewmapper.animeSelectById(animeId);
	}

	@Override
	public void getInquiry(Inquiry inquiry) {
		reviewmapper.addInquiry(inquiry);
		
	}

	

}
