package com.zdrv.controller;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zdrv.domain.AddGroup;
import com.zdrv.domain.LoginGroup;
import com.zdrv.domain.User;
import com.zdrv.service.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	UserServiceImpl userservice;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user",new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(HttpSession session, @Validated(LoginGroup.class) User user,Errors errors) {
		if(errors.hasErrors()) {
			return "login";
		}
		
		User loginUser = userservice.getLoginUser(user);
		
		if(loginUser==null) {
			errors.rejectValue("loginId", "error.login","ログインIDまたはパスワードが違います");
			return "login";
		}
		
		session.setAttribute("user", loginUser);
		return "redirect:/list";
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
	}
	
	@GetMapping("/newAccount")
	public String newAccount(Model model) {
		model.addAttribute("user",new User());
		return "newAccount";
	}
	
	@PostMapping("/newAccount")
	public String newAccountPost(@Validated(AddGroup.class) User user,Errors errors,Model model) {
		
		if(errors.hasErrors()) {
			model.addAttribute("user", user);
			return "newAccount";
		}
		
		String conf = user.getConfPass();
		
		if(!user.getLoginPass().equals(conf)) {
			
			errors.rejectValue("confPass", "not.same.pass");
			return "newAccount";
		}
		
		
		String hashed = BCrypt.hashpw(user.getLoginPass(), BCrypt.gensalt());
		
		user.setLoginPass(hashed);
		
		userservice.insertUser(user);
		return "addDone";
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model) {
		model.addAttribute("user",session.getAttribute("user"));
		return "mypage";
		
	}
	
	@GetMapping("/user/data")
	public String userData(Model model) {
		
		User user = (User) session.getAttribute("user");
		System.out.println(user);
		model.addAttribute("user",user);
		return "newAccount";
	}
	
	@PostMapping("/user/data")
	public String postUserData(@Validated(LoginGroup.class) User user,Model model,Errors errors ) {
		if(errors.hasErrors()) {
			model.addAttribute("user",user);
			return "newAccount";
		}
		System.out.println(user);
		String conf = user.getConfPass();
		
		if(!user.getLoginPass().equals(conf)) {
			errors.rejectValue("confPass", "not.same.pass");
			return "newAccount";
		}
		
		String hashed = BCrypt.hashpw(user.getLoginPass(), BCrypt.gensalt());
		user.setLoginPass(hashed);
		
		User setUser = (User)session.getAttribute("user");
		
		user.setId(setUser.getId());
		System.out.println(user);
		userservice.setUpdate(user);
		
		return "redirect:/user/data/done";
	}
}
