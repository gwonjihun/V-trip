package com.ssafy.trip.model.service.board;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.repo.board.BoardLikeRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardLikeServiceImpl implements BoardLikeService {
	
	private final BoardLikeRepo repo;
	
	@Override
	public int selectLike(int content_id, String users_id) throws SQLException {
		return repo.selectLike(content_id, users_id);
	}

	@Override
	public int insertLike(int content_id, String users_id) throws SQLException {
		return repo.insertLike(content_id, users_id);
	}

	@Override
	public int deleteLike(int content_id, String users_id) throws SQLException {
		return repo.deleteLike(content_id, users_id);
	}
}
