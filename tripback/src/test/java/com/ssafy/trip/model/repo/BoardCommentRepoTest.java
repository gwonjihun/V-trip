package com.ssafy.trip.model.repo;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.dto.BoardCommentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
//@RequiredArgsConstructor
class BoardCommentRepoTest {
	
	@Autowired
	BoardCommentRepo repo;
	
	String comment = "comment";
	String writername = "ssafy";
	int content_id = 1;
	int link = 1;

	@Test
	void testSelect() throws SQLException {
		log.debug("========================1=======================");
		for (BoardCommentDto comment : repo.select(content_id))
			log.debug(comment.toString());
		log.debug("========================1=======================");
	}

	@Test
	@Transactional
	void testInsert() throws SQLException {
		log.debug("========================2=======================");
		repo.insert(new BoardCommentDto(comment, writername, content_id, link));
		for (BoardCommentDto comment : repo.select(content_id))
			log.debug(comment.toString());
		log.debug("========================2=======================");
	}

	@Test
	@Transactional
	void testUpdate() throws SQLException {
		log.debug("========================3=======================");
		repo.update(new BoardCommentDto(1, comment, null, content_id, 0, null, null, null, null));
		for (BoardCommentDto comment : repo.select(content_id))
			log.debug(comment.toString());
		log.debug("========================3=======================");
	}

	@Test
	@Transactional
	void testDelete() throws SQLException {
		log.debug("========================4=======================");
		repo.delete(1);
		for (BoardCommentDto comment : repo.select(content_id))
			log.debug(comment.toString());
		log.debug("========================4=======================");
	}
	
	@Test
	@Transactional
	void testDeleteAll() throws SQLException {
		log.debug("========================5=======================");
		repo.deleteAll(1);
		for (BoardCommentDto comment : repo.select(content_id))
			log.debug(comment.toString());
		log.debug("========================5=======================");
	}

}
