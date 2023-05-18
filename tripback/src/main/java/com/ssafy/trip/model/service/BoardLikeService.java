package com.ssafy.trip.model.service;

import java.sql.SQLException;

public interface BoardLikeService {
	int selectLike(int content_id, String users_id) throws SQLException;
	int insertLike(int content_id, String users_id) throws SQLException;
	int deleteLike(int content_id, String users_id) throws SQLException;
}
