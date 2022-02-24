package com.zdrv.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ViewAnime {
	private int id;
	private User user;
	private String title;
	private Date date;
	private String text;
}
