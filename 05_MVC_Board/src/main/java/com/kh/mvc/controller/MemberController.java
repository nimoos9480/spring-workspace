package com.kh.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mvc.model.service.MemberService;
import com.kh.mvc.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	@GetMapping("/member/login")
	public void login() {}
	
	@RequestMapping("/member/login")
	public String signIn(Member vo, HttpServletRequest request) {
		// 비즈니스 로직: 로그인 처리

		Member member = service.login(vo);

		if (member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("vo", member); // 로그인 정보를 세션에 저장
		}
		return "/board/list";

	}
	
	
	@GetMapping("/member/error")
	public void error() {}
	
	

}
