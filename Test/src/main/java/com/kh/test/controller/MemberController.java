package com.kh.test.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.test.model.service.MemberService;
import com.kh.test.model.vo.Member;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	
	// "search" 페이지를 요청했을 때 실행되는 메서드
	// "search" 페이지를 반환
	@RequestMapping("search")
	public String search() {
		return "search";
	}
	

    // "find" 페이지를 요청했을 때 실행되는 메서드
    // 비즈니스 로직: 입력된 keyword를 사용해 회원을 검색하고 결과를 리스트에 저장
    // 검색 결과를 모델에 추가
    // 검색 결과가 있으면 "find_ok" 페이지를 반환
    // 검색 결과가 없으면 "find_fail" 페이지를 반환
	@RequestMapping("find")
	public String find(String keyword, Model model) {
		List<Member> list = service.findMember(keyword);
		if(list.size() > 0) {
			model.addAttribute("list", list);
			return "find_ok";
		}
		return "find_fail";
	}
	
    

    // "register" 페이지를 요청했을 때 실행되는 메서드
    // "register" 페이지를 반환
	@RequestMapping("register")
	public String register() {
		return "register";
	}

    // 회원 가입을 처리하는 메서드
	// 회원 등록 비즈니스 로직 호출
    // 홈 페이지로 리다이렉션
	@RequestMapping("signUp")
	public String signUp(Member member) {
		service.registerMember(member);
		return "redirect:/";
	}
    

    // "login" 페이지를 요청했을 때 실행되는 메서드
    // "login" 페이지를 반환
	@RequestMapping("login")
	public String login() {
		return "login";
	}


    // 로그인을 처리하는 메서드
    // 로그인 비즈니스 로직 호출
	// 세션에 로그인 정보 저장
    // 로그인 결과 페이지를 반환
	@RequestMapping("signUp")
	public String signUp(Member vo, HttpSession session) {
		Member member = service.login(vo);
		if(member != null) {
			session.setAttribute("vo", member);
		}
		return "login_result";
	}
	   

    // 모든 회원 정보를 가져오는 메서드
    // 모든 회원 정보 가져오기
    // 모델에 추가
    // "find_ok" 페이지를 반환
	@RequestMapping("allMember")
	public String allMember(Model model) {
		List<Member> list = service.showAllMember();
		model.addAttribute("list", list);
		return "find_ok";
		
	}	
    

    // 로그아웃을 처리하는 메서드
    // 세션 무효화
    // 홈 페이지로 리다이렉션
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("vo") != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
    

    // "update" 페이지를 요청했을 때 실행되는 메서드
    // "update" 페이지를 반환
	@RequestMapping("update")
	public String update() {
		return "update";
	}
	
    

    // 회원 정보 업데이트를 처리하는 메서드
    // 회원 정보 업데이트 비즈니스 로직 호출
    // 세션 업데이트
    // 홈 페이지로 리다이렉션
	@RequestMapping("updateMember")
	public String updateMember(Member vo, HttpSession session) {
		service.updateMember(vo);
		if(session.getAttribute("vo")!= null) {
			session.setAttribute("vo", vo);
		}
		return "redirece:/";
	}
	
	
}
