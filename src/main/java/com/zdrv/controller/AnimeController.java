package com.zdrv.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdrv.domain.User;
import com.zdrv.domain.ViewAnime;
import com.zdrv.service.AnimeService;
import com.zdrv.service.ReviewService;

@Controller
@RequestMapping("/list")
public class AnimeController {
	
	@Autowired
	AnimeService anime;
	
	@Autowired
	ReviewService service;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/show/{id}")
	private String show(@PathVariable int id,Model model) {
		
		model.addAttribute("anime",anime.getById(id));
		model.addAttribute("review",service.animeGetById(id));
		return "show";
		
	}
	
	@GetMapping("/anime/add")
	private String add(Model model) {
		ViewAnime viewanime = new ViewAnime();
		model.addAttribute("viewanime",viewanime);
		return "viewadd";
		
	}
	
	@PostMapping("/anime/add")
	private String addPost(@Valid ViewAnime viewanime,Errors errors,Model model,HttpSession session) {
		if(errors.hasErrors()) {
			model.addAttribute("viewanime",viewanime);
			return "viewadd";
		}
		
		User user = (User)session.getAttribute("user");
		viewanime.setUser(user);
		
		anime.insertView(viewanime);
		
		return "redirect:/list";
	}
	
	
	
	@GetMapping("/anime/edit/{id}")
	private String edit(@PathVariable int id,Model model) {
		User user = (User)session.getAttribute("user");
		
		model.addAttribute("viewanime",anime.getSelectViewAnimes(user.getId(), id));
		
		return "viewadd";
	}
	
	@PostMapping("/anime/edit/{id}")
	private String editPost(@Valid ViewAnime viewanime,Errors errors,Model model,HttpSession session) {
		if(errors.hasErrors()) {
			model.addAttribute("viewanime",viewanime);
			return "viewadd";
		}
		
		User user = (User)session.getAttribute("user");
		viewanime.setUser(user);
		
		System.out.println(viewanime);
		anime.updateView(viewanime);
		
		return "redirect:/list";
	}
	
	@GetMapping("/anime/comp/{id}")
	private String deleteView(@PathVariable int id) {
		anime.deleteView(id);
		return "redirect:/list";
	}
	
}
