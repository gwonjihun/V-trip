package com.ssafy.trip.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.BoardCommentDto;

@Mapper
public interface BoardCommentRepo {
	List<BoardCommentDto> select(int content_id) throws SQLException;
	int insert(BoardCommentDto comment) throws SQLException;
	int update(BoardCommentDto comment) throws SQLException;
	int delete(int comment_id) throws SQLException;
	int deleteAll(int content_id) throws SQLException;
}
