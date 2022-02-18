package com.zdrv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdrv.service.AnimeService;
import com.zdrv.service.ReviewService;

@Controller
@RequestMapping("/list")
public class AnimeController {
	
	@Autowired
	AnimeService anime;
	
	@Autowired
	ReviewService service;
	
	@GetMapping("/show/{id}")
	private String show(@PathVariable int id,Model model) {
		
		model.addAttribute("anime",anime.getById(id));
		model.addAttribute("review",service.animeGetById(id));
		return "show";
		
	}
}
