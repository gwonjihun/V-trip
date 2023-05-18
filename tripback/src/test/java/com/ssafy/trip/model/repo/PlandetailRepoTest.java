package com.ssafy.trip.model.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssafy.trip.dto.PlanDetailDto;
import com.ssafy.trip.dto.PlanDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PlandetailRepoTest {
	@Autowired
	PlanDetailRepo PD_repo;
	@Autowired
	PlanRepo PR_repo;
	int id = 1;


	
	@Test
	void testSelect() throws SQLException {
		List<PlanDetailDto> board = PD_repo.select(id);
		
		log.debug("!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#");
		log.debug(board.toString());
		log.debug("!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#!@#");
		assertNotNull(board);
		assertEquals(0, board.size());
		
	}

	

	

	@Test
	void testInsert() throws SQLException {
		List<PlanDetailDto> rb = new LinkedList<PlanDetailDto>() ;
		int cnt = 0 ;
		for(int i = 1; i<3; i++) {
			for(int j = 1; j<3; j++) {
				cnt ++;
				rb.add(new PlanDetailDto(1, 125266 , i, j));
			}
		}

		
		int board = PD_repo.insert(rb);
		assertEquals(board, cnt);
		board = PD_repo.delete(1);
		assertEquals(board, cnt);
		
	}
}
