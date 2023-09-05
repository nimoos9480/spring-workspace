package com.kh.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mvc.model.service.MemberService;
import com.kh.mvc.model.vo.Member;

@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder bcpe;
	
	@GetMapping("/register")
	public void register() {}

	@PostMapping("/register")
	public String register(Member vo) {
		
		System.out.println("before password : " + vo.getPassword());
		
		// BcryptPasswordEncoder를 이용해서 암호화 처리
		String encodePassword = bcpe.encode(vo.getPassword());		
		System.out.println("after password : " + encodePassword);
		
		vo.setPassword(encodePassword);
		
		service.registerMember(vo);
		return "redirect:/login";
	}
	
	

	@GetMapping("/login")
	public void login() {}
	
	@GetMapping("/logout")
	public void logout() {}
}
