package com.kh.mvc.model.vo;

import lombok.Getter;

@Getter
public class Paging {
	
	private int startPage;	// 첫 페이지 명시
	private int endPage;	// 마지막 페이지 명시
	private boolean prev;	// 이전 페이지
	private boolean next;	// 다음 페이지
	private int num = 10;	// 한 페이지에 보여질 개수
	
	private int total;		// 완전 끝 페이지
	private Criteria cri;	// 현재페이지
	
	public Paging(Criteria cri, int total) {
		this.cri = cri;
		this.total = total; 
		
		this.endPage = (int) Math.ceil((double)cri.getPage() / this.num) * this.num; // (올림)(현재 페이지 / num) * num
		this.startPage = this.endPage - this.num + 1;
		
		int lastPage = (int) Math.ceil((double)total / cri.getAmount());
		
		if(lastPage < this.endPage) {
			this.endPage = lastPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < lastPage;
	}
}
