package com.zdrv.domain;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class User {

	private Integer id;
	
	@NotBlank(groups= {LoginGroup.class,AddGroup.class})
	private String loginId;
	
	@NotBlank(groups= {LoginGroup.class,AddGroup.class})
	private String loginPass;
	
	private String confPass;
	
	@NotBlank(groups= {AddGroup.class})
	private String name;
	private Integer level;
}
