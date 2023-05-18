package com.ssafy.trip.model.repo;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardLikeRepo {
	int selectLike(int content_id, String users_id) throws SQLException;
	int insertLike(int content_id, String users_id) throws SQLException;
	int deleteLike(int content_id, String users_id) throws SQLException;
}
