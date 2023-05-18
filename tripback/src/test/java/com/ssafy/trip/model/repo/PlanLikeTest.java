package com.ssafy.trip.model.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.trip.dto.PlanLikeDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PlanLikeTest {
	@Autowired
	PlanLikeRepo repo;
	PlanLikeDto dto = new PlanLikeDto(1,"ssafy");
	@Test
	void selecttest() {
		PlanLikeDto result = repo.select(dto);
		log.debug(result.toString());
		assertEquals(result.toString(),dto.toString());
		
	}
	@Test
	void inserttest() {
		dto = new PlanLikeDto(2,"ssafy");
		int result = repo.insert(dto);
		assertEquals(1, result);
		
	}
	@Test
	void deletetest() {
		int result = repo.delete(dto);
		assertEquals(1, result);
		
	}

}
