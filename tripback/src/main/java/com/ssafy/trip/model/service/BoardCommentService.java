package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.dto.BoardCommentDto;

public interface BoardCommentService {
	List<BoardCommentDto> select(int content_id) throws SQLException;
	int insert(BoardCommentDto comment) throws SQLException;
	int update(BoardCommentDto comment) throws SQLException;
	int delete(int comment_id) throws SQLException;
	int deleteAll(int content_id) throws SQLException;
}
