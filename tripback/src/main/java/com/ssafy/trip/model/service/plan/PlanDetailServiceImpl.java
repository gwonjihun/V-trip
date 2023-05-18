package com.ssafy.trip.model.service.plan;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.dto.plan.PlanDetailDto;
import com.ssafy.trip.dto.plan.PlanDto;
import com.ssafy.trip.model.repo.board.BoardCommentRepo;
import com.ssafy.trip.model.repo.plan.PlanDetailRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanDetailServiceImpl implements PlanDetailService {

	
	private final PlanDetailRepo repo;
	
	
	@Transactional
	@Override
	public int insert(List<PlanDetailDto> list) throws SQLException {
		// TODO Auto-generated method stub
		return repo.insert(list);
	}

	@Override
	public List<PlanDetailDto> select(int plan_id) throws SQLException {
		// TODO Auto-generated method stub
		return repo.select(plan_id);
	}

	@Override
	@Transactional
	public int delete(int plan_id) throws SQLException {
		// TODO Auto-generated method stub
		return repo.delete(plan_id);
	}

}
