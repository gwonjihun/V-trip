package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.dto.BoardDto;
import com.ssafy.trip.dto.BoardSearchOptionDto;

public interface BoardService {
	BoardDto select(int content_id) throws SQLException;
	List<BoardDto> selectAll() throws SQLException;
	List<BoardDto> selectNotice() throws SQLException;
	Map<String, Object> selectOption(BoardSearchOptionDto option) throws SQLException;
	int selectCount(BoardSearchOptionDto option) throws SQLException;
	int insert(BoardDto board) throws SQLException;
	int update(Map<String, String> map) throws SQLException;
	int delete(int content_id) throws SQLException;
	int deleteAll(String writername) throws SQLException;
	int selectLike(int content_id, String users_id) throws SQLException;
	int insertLike(int content_id, String users_id) throws SQLException;
	int deleteLike(int content_id, String users_id) throws SQLException;
}
