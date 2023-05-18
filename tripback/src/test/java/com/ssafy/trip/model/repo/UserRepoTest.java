package com.ssafy.trip.model.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest()
@Slf4j
class UserRepoTest {

	String id = "test";
	String name = "test";
	String password = "1234";
	String email = "test@email.com";
	String user_type = "사용자"; //admin, user
	String nickname = "test";
	int point = 0;
	
	String cid = "test1";
	String cnickname = "test1";
	int cpoint = 10;
	
	@Autowired
	UserRepo repo;

	@Test
	void testSelectUser() throws SQLException {
		UserDto user = repo.selectUser(id);
		log.info(user.toString());
		assertNotNull(user);
	}

	@Test
	void testSelectAllUsers() throws SQLException {
		assertNotNull(repo.selectAllUsers());
	}

	@Test
	@Transactional
	void testDeleteUser() throws SQLException {
		assertEquals(repo.deleteUser(id), 1);
		System.out.println(repo.selectAllUsers());
	}

	@Test
	@Transactional
	void testUpdateUser() throws SQLException {
		UserDto user = new UserDto();
		user.setId(id);
		user.setNickname(cnickname);
		assertEquals(repo.updateUser(user), 1);
		System.out.println(repo.selectUser(id));

		user.setPoint(cpoint);
		assertEquals(repo.updateUser(user), 1);
		System.out.println(repo.selectUser(id));
	}

	@Test
	@Transactional
	void testInsertUser() throws SQLException {
		UserDto user = new UserDto(cid, name, password, email, user_type, nickname, point, null);
		assertEquals(repo.insertUser(user), 1);
	}

}
