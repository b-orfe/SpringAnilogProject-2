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
import org.springframework.web.bind.annotation.RequestParam;

import com.zdrv.domain.Inquiry;
import com.zdrv.domain.Review;
import com.zdrv.domain.User;
import com.zdrv.service.AnimeService;
import com.zdrv.service.ReviewService;

@Controller
@RequestMapping({"/list",""})
public class ReviewController {
	
	@Autowired
	HttpSession session;
	
	
	@Autowired
	 ReviewService rsimpl ;
	
	@Autowired
	 AnimeService aniimpl;
	
	@GetMapping
	public String list(Model model, @RequestParam(defaultValue="1") Integer page ) {
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("reviews", rsimpl.allGetById(user.getId()));
		model.addAttribute("totalPages", aniimpl.totalPages());
		model.addAttribute("page", page);
		model.addAttribute("animes", aniimpl.getAnimes(page));
		return "list";
	}
	
	@PostMapping
	public String postList(Model model,@RequestParam String moji,@RequestParam(defaultValue="1") Integer page) {	
		User user = (User) session.getAttribute("user");
		model.addAttribute("reviews", rsimpl.allGetById(user.getId()));
		model.addAttribute("totalPages", aniimpl.totalPages());
		model.addAttribute("page", page);
		model.addAttribute("animes", aniimpl.getAnimes(page));
		model.addAttribute("search",aniimpl.getSearchAnimes(moji)); 
		return "list";
	}
	
	@GetMapping("/addReview")
	public String addReview(Model model) {
		model.addAttribute("review",new Review());
		model.addAttribute("animes",aniimpl.getAll());
		
		return "addReview";
	}
	
	@PostMapping("/addReview")
	public String addReviewPost(@Valid Review review,Errors errors,HttpSession session, Model model) {
		
		if(errors.hasErrors()) {
			model.addAttribute("animes",aniimpl.getAll());
			return "addReview";
		}
		
		User user = (User)session.getAttribute("user");
		review.setUser(user);
		rsimpl.insertRe(review);
		return "redirect:/list";
	}
	
	
	@GetMapping("/delete/{animeId}")
	public String delete(@PathVariable int animeId) {
		User user = (User)session.getAttribute("user");
		rsimpl.deleteByIdRe(user.getId(),animeId);
		return "redirect:/list";
	}
	
	@GetMapping("/edit/{animeId}")
	public String edit(@PathVariable int animeId ,Model model) {
		User user = (User) session.getAttribute("user");
		model.addAttribute("review",rsimpl.getById(user.getId(),animeId));
		model.addAttribute("animes",aniimpl.getById(animeId));
		
		return "addReview";
	}
	
	@PostMapping("/edit/{animeId}")
	public String editPost(@Valid Review review,Errors errors) {
		if(errors.hasErrors()) {
			
			return"addReview";
		}
		
		User user = (User) session.getAttribute("user");
		review.setUser(user);
		
		rsimpl.updateRe(review);
		
		return "redirect:/list";
		
	}
	
	@GetMapping("/inquiry")
	public String inquiry(Model model) {
		User user=(User)session.getAttribute("user");
		Inquiry inquiry = new Inquiry();
		
		model.addAttribute("inquiry", inquiry);
		model.addAttribute("user",user);
		return "Inquiry" ;
	}
	
	@PostMapping("/inquiry")
	public String inquiry(@Valid Inquiry inquiry,Errors errors,Model model) {
		
		if(errors.hasErrors()) {
			model.addAttribute("inquiry",inquiry);
			return "Inquiry";
		}
		User user=(User)session.getAttribute("user");
		inquiry.setUserId(user.getId());
		rsimpl.getInquiry(inquiry);
		return "redirect:/inquiry/done";
	}
	
	@GetMapping("/inquiry/done")
	public String inquiryDone() {
		return "addInquiry";
	}

}
