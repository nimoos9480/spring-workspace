package com.kh.mvc.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mvc.model.vo.Member;

@Repository
public class MemberDAO {

	@Autowired 
	private SqlSessionTemplate sqlSession; // 가져다 쓸 것 선언, 의존성 주입(@Autowired)
	
	public int registerMember(Member vo) {
		return sqlSession.insert("memberMapper.registerMember", vo);
	}
	
	/*
	 * showAllMember
	 * findMember -> 파라미터 : String keyword 넘겨라, return : List<Member>
	 * login -> 파라미터 : Member vo
	 * updateMember -> 파라미터 : Member vo
	 * */
	
	public List<Member> showAllMember(Member vo) {
		return sqlSession.selectList("memberMapper.showAllMember", vo);
	}
	
	public List<Member> findMember(String keyword) {
		return sqlSession.selectList("memberMapper.findMember", keyword);		
	}
	
	public Member login(Member vo) {
		return sqlSession.selectOne("memberMapper.login", vo);
	}
	
	public int updateMember(Member vo) {
		return sqlSession.update("memberMapper.updateMember", vo);
	}
	
}
