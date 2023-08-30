package com.kh.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mvc.model.service.MemberService;
import com.kh.mvc.model.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;

	@RequestMapping("search")
	public String search() {
		return "search";
	}

	@RequestMapping("find")
	public String find(String keyword, Model model) {
		System.out.println(keyword);
		// 서비스 - 비즈니스 로직 처리!
		// --> list 값! 데이터 바인딩을 Model에!
		List<Member> list = service.findMember(keyword);
		
		if (list.size() > 0) {
			model.addAttribute("list", list);
			return "find_ok";
		}

		return "find_fail";
	}

	@RequestMapping("register")
	public String register() {
		return "register";
	}

	@RequestMapping("signUp")
	public String signUp(Member member) {
		// 비즈니스 로직
		service.registerMember(member);

		// register.jsp name과 필드명을 맞추면 getParameter 하지 않고 그냥 Member로 받아올 수 있음
		return "redirect:/"; // views 밖에 위치한 index.jsp로 넘기는 방법
	}

	// login - 페이지 이동
	@RequestMapping("login")
	public String login() {
		return "login";
	}

	// signIn - 비즈니스 로직 포함 : 파라미터 값 -> HttpServletRequest request
	// -> return "login_result";
	@RequestMapping("signIn")
	public String signIn(Member vo, HttpServletRequest request) {
		// 비즈니스 로직: 로그인 처리

		Member member = service.login(vo);

		if (member != null) {
			HttpSession session = request.getSession();
			session.setAttribute("vo", member); // 로그인 정보를 세션에 저장
		}
		return "login_result";

	}

	// allMember - 비즈니스 로직 포함, 데이터 바인딩 - Model
	// return "find_ok";
	@RequestMapping("allMember")
	public String allMember(Model model) {
		List<Member> list = service.showAllMember();
		model.addAttribute("list", list);

		return "find_ok";
	}

	// logout - 로그아웃 기능!
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		if(session.getAttribute("vo") !=null ) {
			session.invalidate();
		}
		return "redirect:/";
	}

	// update - 페이지 이동
	@RequestMapping("update")
	public String update() {
		
		return "update";
	}

	// updateMember - 비즈니스 로직 포함 -> 파라미터 request필요
	@RequestMapping("updateMember")
	public String updateMember(Member vo, HttpSession session) {
		service.updateMember(vo);
		
		if(session.getAttribute("vo")!=null) {
			session.setAttribute("vo", vo);
		}
		
		return "redirect:/";	
	}
}
		
