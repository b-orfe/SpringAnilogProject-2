package com.zdrv.domain;

import lombok.Data;

@Data
public class Review {
	
	private User user;
	private Anime anime;
	
	private Integer score;
	
	
	private String article;

}
