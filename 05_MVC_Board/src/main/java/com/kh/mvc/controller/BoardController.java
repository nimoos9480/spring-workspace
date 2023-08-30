package com.kh.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.mvc.model.service.BoardService;
import com.kh.mvc.model.vo.Board;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;

	
//	@RequestMapping("board/list")
//	public ModelAndView list() {
//		List<Board> list = service.selectAllBoard();
//		return new ModelAndView("board/list", "list", list);
//	}

//	 @RequestMapping("board/list") 
//	 public String list(Model model) { 
//		 List<Board> list = service.selectAllBoard(); 
//		 model.addAttribute("list", list);
//	 return "board/list";
	 
//	 @RequestMapping(value="/list", method=RequestMethod.GET)을 정리하면 아래
	 @GetMapping("/list")
	 public void list(Model model) { 
		 List<Board> list = service.selectAllBoard(); 
		 model.addAttribute("list", list);
	 }
	 
//	 @PostMapping("/list")
	 
//	 @putMapping
	 
//	 @DeleteMapping

	 @RequestMapping("/insert") // 공통경로를 빼놓았기 때문에 가능
	 public void insert() {
		 
	 }

}
