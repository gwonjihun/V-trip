package com.ssafy.trip.model.service.board;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.BoardSearchOptionDto;
import com.ssafy.trip.model.repo.board.BoardCommentRepo;
import com.ssafy.trip.model.repo.board.BoardLikeRepo;
import com.ssafy.trip.model.repo.board.BoardRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepo repo;
	private final BoardCommentRepo commentRepo;
	private final BoardLikeRepo likeRepo;

	@Override
	public BoardDto select(int content_id) throws SQLException {
		BoardDto board = repo.select(content_id);
		if (repo.updateReads(content_id) != 0)
			board.setReads(board.getReads()+1);
		return board;
	}

	@Override
	public List<BoardDto> selectAll() throws SQLException {
		return repo.selectAll();
	}
	
	@Override
	public List<BoardDto> selectNotice() throws SQLException {
		return repo.selectNotice();
	}

	@Override
	public Map<String, Object> selectOption(BoardSearchOptionDto option) throws SQLException {
		Map<String, Object> map = new HashMap<>();
		int count = repo.selectCount(option);
		int start = option.getStart();
		map.put("count", count);
		if (start < count) {
			List<BoardDto> list = repo.selectOption(option);
			map.put("boards", list);
		} else {
			map.put("msg", "pageover");
		}
		return map;
	}

	@Override
	public int selectCount(BoardSearchOptionDto option) throws SQLException {
		return repo.selectCount(option);
	}

	@Override
	public int insert(BoardDto board) throws SQLException {
		return repo.insert(board);
	}

	@Override
	public int update(Map<String, String> map) throws SQLException {
		return repo.update(map);
	}

	@Override
	@Transactional
	public int delete(int content_id) throws SQLException {
		commentRepo.deleteAll(content_id);
		return repo.delete(content_id);
	}

	@Override
	@Transactional
	public int deleteAll(String writername) throws SQLException {
		return repo.deleteAll(writername);
	}

	@Override
	public int selectLike(int content_id, String users_id) throws SQLException {
		return likeRepo.selectLike(content_id, users_id);
	}

	@Override
	public int insertLike(int content_id, String users_id) throws SQLException {
		return likeRepo.insertLike(content_id, users_id);
	}

	@Override
	public int deleteLike(int content_id, String users_id) throws SQLException {
		return likeRepo.deleteLike(content_id, users_id);
	}

}
