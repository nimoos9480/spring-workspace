package com.kh.mvc.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.mvc.model.dao.MemberDAO;
import com.kh.mvc.model.vo.Member;

@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberDAO dao;
	
	public Member login(Member vo) {
		return dao.login(vo);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = dao.getMemberById(username);  
							// login.jsp에서 username과 id연결함
		if(member == null) {
			throw new UsernameNotFoundException("username " + username + "not found");
		}
		return member;
	}

}
