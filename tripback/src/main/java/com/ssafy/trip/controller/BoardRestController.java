package com.ssafy.trip.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.dto.board.BoardCommentDto;
import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.BoardSearchOptionDto;
import com.ssafy.trip.dto.user.UserDto;
import com.ssafy.trip.model.service.board.BoardCommentService;
import com.ssafy.trip.model.service.board.BoardLikeService;
import com.ssafy.trip.model.service.board.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tripapi/board")
@RequiredArgsConstructor
public class BoardRestController {

	private final BoardService svc;
	private final BoardCommentService commentSvc;
	private final BoardLikeService likeSvc;
	
	@GetMapping()
	ResponseEntity<Map<String, Object>> list() throws SQLException {
		Map<String, Object> result = new HashMap<String, Object>();
		List<BoardDto> list = svc.selectAll();
		if (list != null && list.size() > 0) {
			result.put("boards", list);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/notice")
	ResponseEntity<Map<String, Object>> notice() throws SQLException {
		Map<String, Object> result = new HashMap<String, Object>();
		List<BoardDto> list = svc.selectNotice();
		if (list != null && list.size() > 0) {
			result.put("boards", list);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else
			return new ResponseEntity<Map<String, Object>>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/option")
	ResponseEntity<Map<String, Object>> option(BoardSearchOptionDto option) throws SQLException {
		Map<String, Object> result = svc.selectOption(option);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{content_id}")
	ResponseEntity<BoardDto> list(@PathVariable int content_id) throws SQLException {
		BoardDto board = svc.select(content_id);
		if (board != null) {
			return new ResponseEntity<BoardDto>(board, HttpStatus.OK);
		} else
			return new ResponseEntity<BoardDto>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping
	ResponseEntity<Void> insert(@RequestBody BoardDto board, HttpSession session) throws SQLException {
		UserDto login = (UserDto)session.getAttribute("userinfo");
		if (login.getUser_type().equals("admin") || login.getId().equals(board.getWritername())) {
			int r = svc.insert(board);
			if (r != 0)
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
	
	@PutMapping
	ResponseEntity<Void> update(@RequestBody Map<String, String> map, HttpSession session) throws SQLException {
		log.debug(map.toString());
		UserDto login = (UserDto)session.getAttribute("userinfo");
		if (login.getUser_type().equals("admin") || login.getId().equals(map.get("writername"))) {
			int r = svc.update(map);
			if (r != 0)
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
	
	@DeleteMapping("/{content_id}")
	ResponseEntity<Void> delte(@PathVariable int content_id, HttpSession session) throws SQLException {
		UserDto login = (UserDto)session.getAttribute("userinfo");
		BoardDto board = svc.select(content_id);
		if (login.getUser_type().equals("admin") || login.getId().equals(board.getWritername())) {
			int r = svc.delete(content_id);
			if (r != 0)
				return new ResponseEntity<Void>(HttpStatus.OK);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}

	// comment
	@GetMapping("/{content_id}/comment")
	ResponseEntity<List<BoardCommentDto>> commentList(@PathVariable int content_id) throws SQLException {
		return new ResponseEntity<List<BoardCommentDto>>(commentSvc.select(content_id), HttpStatus.OK);
	}
	
	@PostMapping("/{content_id}/comment")
	ResponseEntity<Void> commentPost(@PathVariable int content_id, @RequestBody BoardCommentDto comment, HttpSession session) throws SQLException {
		UserDto login = (UserDto)session.getAttribute("userinfo");
		if (login.getUser_type().equals("admin") || login.getId().equals(comment.getWritername())) {
			int r = commentSvc.insert(comment);
			if (r != 0)
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
	
	@PutMapping("/{content_id}/comment")
	ResponseEntity<Void> commentUpdate(@PathVariable int content_id, @RequestBody BoardCommentDto comment, HttpSession session) throws SQLException {
		UserDto login = (UserDto)session.getAttribute("userinfo");
		if (login.getUser_type().equals("admin") || login.getId().equals(comment.getWritername())) {
			int r = commentSvc.update(comment);
			if (r != 0)
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
	
	@DeleteMapping("/{content_id}/comment")
	ResponseEntity<Void> commentDelete(@PathVariable int content_id, @RequestBody BoardCommentDto comment, HttpSession session) throws SQLException {
		UserDto login = (UserDto)session.getAttribute("userinfo");
		if (login.getUser_type().equals("admin") || login.getId().equals(comment.getWritername())) {
			int r = commentSvc.delete(comment.getComment_id());
			if (r != 0)
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
	
	// like
	@GetMapping("/{content_id}/like")
	ResponseEntity<Integer> likeSelect(@PathVariable int content_id, HttpSession session) throws SQLException {
		UserDto login = (UserDto)session.getAttribute("userinfo");
		if (login != null)
			return new ResponseEntity<Integer>(likeSvc.selectLike(content_id, login.getId()), HttpStatus.OK);
		return new ResponseEntity<Integer>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/{content_id}/like")
	ResponseEntity<Integer> likeInsert(@PathVariable int content_id, HttpSession session) throws SQLException {
		UserDto login = (UserDto)session.getAttribute("userinfo");
		return new ResponseEntity<Integer>(likeSvc.insertLike(content_id, login.getId()), HttpStatus.OK);
	}

	@DeleteMapping("/{content_id}/like")
	ResponseEntity<Integer> likeDelete(@PathVariable int content_id, HttpSession session) throws SQLException {
		UserDto login = (UserDto)session.getAttribute("userinfo");
		return new ResponseEntity<Integer>(likeSvc.deleteLike(content_id, login.getId()), HttpStatus.OK);
	}
}
