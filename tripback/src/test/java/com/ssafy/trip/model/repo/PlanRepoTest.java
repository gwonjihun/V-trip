package com.ssafy.trip.model.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.trip.dto.PlanDetailDto;
import com.ssafy.trip.dto.PlanDto;
import com.ssafy.trip.model.service.PlanDetailService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@SpringBootTest
public class PlanRepoTest {
	@Autowired
	PlanDetailRepo svc;
	
	int id = 4;
	String title = null;
	int read = 0;
	String sido = null;
	String writeid = "ssafy";
	String createat = "2023-05-01 16:49:45";

	@Test
	void testSelect() throws SQLException {
		List<PlanDetailDto> board = svc.select(1);
		
		log.debug("!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#");
		log.debug("!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#");
		assertEquals(2, board.size());
		
	}

	
}
