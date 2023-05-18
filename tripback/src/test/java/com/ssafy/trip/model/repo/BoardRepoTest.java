package com.ssafy.trip.model.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.board.BoardSearchOptionDto;
import com.ssafy.trip.model.repo.board.BoardRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class BoardRepoTest {

	@Autowired
	BoardRepo repo;
	
	int id = 1;
	String title = "title01";
	String content = "content01";
	String writername = "ssafy";
	
	String ccontent = "test";
	
	BoardSearchOptionDto option1 = new BoardSearchOptionDto();
	BoardSearchOptionDto option2 = new BoardSearchOptionDto(2);
	BoardSearchOptionDto option3 = new BoardSearchOptionDto("title", "t");
	BoardSearchOptionDto option4 = new BoardSearchOptionDto("writername", "ssafy");
	BoardSearchOptionDto option5 = new BoardSearchOptionDto(1,20,"none",null,true);
	
	@Test
	void testSelect() throws SQLException {
		BoardDto board = repo.select(id);
		assertNotNull(board);
		log.debug(board.toString());
		assertEquals(board.getTitle(), title);
		assertEquals(board.getNickname(), writername);
	}

	@Test
	void testSelectAll() throws SQLException {
		assertNotEquals(repo.selectAll().size(), 0);
	}
	
	@Test
	void testSelectNotice() throws SQLException {
		assertNotEquals(repo.selectNotice().size(), 0);
	}
	
	@Test
	void testSelectOption() throws SQLException {
		log.debug("11111111111111111111111111111111111");
		log.debug(repo.selectOption(option1).toString());
		log.debug(repo.selectOption(option2).toString());
		log.debug(repo.selectOption(option3).toString());
		log.debug(repo.selectOption(option4).toString());
		log.debug(repo.selectOption(option5).toString());
		log.debug("11111111111111111111111111111111111");
	}
	
	@Test
	void testSelectCount() throws SQLException {
		log.debug("222222222222222222222222222222222222222");
		log.debug(""+repo.selectCount(option1));
		log.debug(""+repo.selectCount(option2));
		log.debug(""+repo.selectCount(option3));
		log.debug(""+repo.selectCount(option4));
		log.debug(""+repo.selectCount(option5));
		log.debug("222222222222222222222222222222222222222");
	}

	@Test
	@Transactional
	void testInsert() throws SQLException {
		BoardDto board = new BoardDto(title, content, writername);
		repo.insert(board);
		board = repo.selectAll().get(0);
		log.debug(board.toString());
		assertNotNull(board.getCreateat());
	}

	@Test
	@Transactional
	void testUpdate() throws SQLException {
		BoardDto board = new BoardDto(id, title, ccontent, writername);
		repo.update(board);
		log.debug(repo.select(id).toString());
		assertEquals(repo.select(id).getContent(), ccontent);
		assertNotNull(repo.select(id).getUpdateat());
	}

	@Test
	@Transactional
	void testDelete() throws SQLException {
		repo.delete(id);
		assertNull(repo.select(id).getDeleteat());
	}

}
