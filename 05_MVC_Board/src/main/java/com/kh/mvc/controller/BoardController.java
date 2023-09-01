package com.kh.mvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.mvc.model.service.BoardService;
import com.kh.mvc.model.vo.Board;
import com.kh.mvc.model.vo.Criteria;
import com.kh.mvc.model.vo.Paging;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	// 경로 전역변수로 빼놓음
	String path = "D:\\spring-workspace\\05_MVC_Board\\src\\main\\webapp\\upload\\";
	
	@Autowired
	private BoardService service;

	/*
	 * @RequestMapping("board/list") public ModelAndView list() { List<Board> list =
	 * service.selectAllBoard(); return new ModelAndView("board/list", "list",
	 * list); }
	 */

	/*
	 * @RequestMapping("board/list") public String list(Model model) { List<Board>
	 * list = service.selectAllBoard(); model.addAttribute("list", list); return
	 * "board/list";
	 * 
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
	public String insert(Board board) throws IllegalStateException, IOException{	
		
		// 파일 업로드 기능
		fileUpload(board);
		
		service.insertBoard(board);
		return "redirect:/board/list";
	}

	@GetMapping("/view")
	public void view(int no, Model model) {				
		model.addAttribute("vo", service.selectBoard(no));
		// view.jsp로 값 넘기기
	}

	@GetMapping("/update")
	public void update(int no, Model model) {
		model.addAttribute("vo", service.selectBoard(no));
	}
	
	
	// 파일 업로드 기능 추가
	public void fileUpload(Board board) throws IllegalStateException, IOException {
		
		MultipartFile file = board.getUploadFile();
		System.out.println("file : " + file);
		
		if(!file.isEmpty()) { // 업로드한 파일이 있는 경우!
			
			// 기존에 파일이 있는 경우 삭제!
			if(board.getUrl()!=null) {
				File delFile = new File(path + board.getUrl().replace("/upload/", ""));
				delFile.delete();
			}
			System.out.println("파일의 사이즈 : " + file.getSize());
			System.out.println("업로드된 파일명 : " + file.getOriginalFilename());
			System.out.println("파일의 파라미터명 : " + file.getName());
			
//			String path = "D:\\spring-workspace\\05_MVC_Board\\src\\main\\webapp\\upload\\";
			
			// 중복 방지를 위한 UUID 적용
			UUID uuid = UUID.randomUUID();
			String filename = uuid.toString() + "_" + file.getOriginalFilename();
	
			File copyFile = new File(path + filename);
			file.transferTo(copyFile); // 업로드한 파일이 지정한 path 위치로 저장 // 여기까지 하면 업로드 됨

			// 데이터베이스에 경로 저장
			board.setUrl("/upload/" + filename);
		}	
		
	}

	
	@PostMapping("/update")
	public String update(Board board) throws IllegalStateException, IOException {

		// 파일 업로드 기능 추가
		fileUpload(board);

		// mapper update에 url까지 담아줘야 파일 들어감
		service.updateBoard(board);
		return "redirect:/board/list";
	}

	@GetMapping("/delete")
	public String delete(int no) {
		
		// 이미지 url 남아있으면 삭제
		Board board = service.selectBoard(no);
		if(board.getUrl()!=null) {
			File delFile = new File(path + board.getUrl().replace("/upload/", ""));
			delFile.delete();
		}
		
		service.deleteBoard(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/download")
	public ModelAndView downloadFile(String filename) {
		HashMap map = new HashMap();
		map.put("path", path);
		return new ModelAndView("downloadView", map);
	
	}

}
