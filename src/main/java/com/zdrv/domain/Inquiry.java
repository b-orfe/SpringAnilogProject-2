package com.zdrv.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Inquiry {
	
	
	
	private int userId;
	@NotBlank
	private String title;
	@NotBlank
	private String text;
}
