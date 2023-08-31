package com.kh.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.mvc.model.service.BoardService;
import com.kh.mvc.model.vo.Board;
import com.kh.mvc.model.vo.Criteria;
import com.kh.mvc.model.vo.Paging;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;

/*
 * @RequestMapping("board/list")
	public ModelAndView list() {
		List<Board> list = service.selectAllBoard();
		return new ModelAndView("board/list", "list", list);
	}
 * */	
	
/*
 * 	 @RequestMapping("board/list") 
	 public String list(Model model) { 
		 List<Board> list = service.selectAllBoard(); 
		 model.addAttribute("list", list);
	 return "board/list";
	 
	 */

	 
//	 @RequestMapping(value="/list", method=RequestMethod.GET)을 정리하면 아래와 동일
	 @GetMapping("/list")
	 public void list(Criteria cri, Model model) { 
		 List<Board> list = service.selectAllBoard(cri); 
		 model.addAttribute("list", list);
		 model.addAttribute("paging", new Paging(cri, service.getTotal()));
	 }
	 
//	 @PostMapping("/list")
	 
//	 @putMapping
	 
//	 @DeleteMapping

	 @GetMapping("/insert") // 공통경로를 빼놓았기 때문에 가능
	 public void insert() {
		 
	 }
	 
	 @PostMapping("/insert")
	 public String insert(Board board) {
		 service.insertBoard(board);
		 return "redirect:/board/list";
	 }
	 
	 @GetMapping("/view")
	 public void view(int no, Model model) {
		model.addAttribute("vo", service.selectBoard(no));
		// view.jsp로 값 넘기기
	 }	 
	 
	 
	 @PostMapping("/update")
	 public String update(Board board) {
		 service.updateBoard(board);
		 return "redirect:/board/list";
	 }
	 
	 @GetMapping("/delete")
	 public String delete(int no) {
		 service.deleteBoard(no);
		 return "redirect:/board/list";
	 }
	 
	 
	 
	 

}
