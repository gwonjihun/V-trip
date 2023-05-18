package com.ssafy.trip.model.service.board;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trip.dto.board.BoardCommentDto;
import com.ssafy.trip.model.repo.board.BoardCommentRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardCommentServiceImpl implements BoardCommentService {

	private final BoardCommentRepo repo;
	
	@Override
	public List<BoardCommentDto> select(int content_id) throws SQLException {
		List<BoardCommentDto> select = repo.select(content_id);
		List<BoardCommentDto> list = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (BoardCommentDto comment: select) {
			if (comment.getDeleteat() != null) {
				comment.setComment(null);
			}
			if (comment.getLink() == 0) {
				map.put(comment.getComment_id(), list.size());
				list.add(comment);
			} else {
				int parent_idx = map.getOrDefault(comment.getLink(), -1);
				if (parent_idx != -1) {
					BoardCommentDto parent = list.get(parent_idx);
					parent.getChild().add(comment);
				}
			}
		}
		return list;
	}

	@Override
	public int insert(BoardCommentDto comment) throws SQLException {
		return repo.insert(comment);
	}

	@Override
	public int update(BoardCommentDto comment) throws SQLException {
		return repo.update(comment);
	}

	@Override
	public int delete(int comment_id) throws SQLException {
		return repo.delete(comment_id);
	}
	
	@Override
	public int deleteAll(int content_id) throws SQLException {
		return repo.deleteAll(content_id);
	}
}
