package com.ncs.test.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ncs.test.member.model.service.MemberService;
import com.ncs.test.member.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping("login")
	public String memberLogin(Member vo, HttpSession session) {
		Member member = service.loginMember(vo);
		if(member != null) {
			session.setAttribute("vo", member);
			return "redirect:/";
		}
		
		return "errorPage";
	}
	
	
}
