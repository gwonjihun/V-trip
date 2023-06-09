package com.ssafy.trip.model.repo.board;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.BoardSearchOptionDto;

@Mapper
public interface BoardRepo {
	BoardDto select(int content_id) throws SQLException;
	List<BoardDto> selectAll() throws SQLException;
	List<BoardDto> selectNotice() throws SQLException;
	List<BoardDto> selectOption(BoardSearchOptionDto option) throws SQLException;
	int selectCount(BoardSearchOptionDto option) throws SQLException;
	int insert(BoardDto board) throws SQLException;
	int update(BoardDto board) throws SQLException;
	int delete(int content_id) throws SQLException;
	int deleteAll(String writername) throws SQLException;
	int updateReads(int content_id) throws SQLException;
}
