package com.kh.mvc.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.mvc.model.vo.Board;

@Repository
public class BoardDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertBoard(Board board) {
		return sqlSession.insert("board.insert", board);
	}
	
	public List<Board> selectAllBoard() {
		return sqlSession.selectList("board.selectAll");
	}
	
	public List<Board> selectBoard(String keyword) {
		return sqlSession.selectList("board.select", keyword);
	}
	
	public int updateBoard(Board board) {
		return sqlSession.update("board.update", board);
	}
	
	public int deleteBoard(Board board) {
		return sqlSession.delete("board.delete", board);
	}
	
	
	
	
	
	
	
}
