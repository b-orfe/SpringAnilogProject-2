package com.zdrv.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Inquiry {
	
	
	@NotBlank
	private String userId;
	@NotBlank
	private String title;
	@NotBlank
	private String text;
}
