package com.ssafy.trip.model.service.plan;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.dto.plan.PlanConditionDto;
import com.ssafy.trip.dto.plan.PlanDto;
import com.ssafy.trip.dto.plan.PlanLikeDto;
import com.ssafy.trip.dto.plan.PlanShareDto;
import com.ssafy.trip.model.repo.plan.PlanLikeRepo;
import com.ssafy.trip.model.repo.plan.PlanShareRepo;

import lombok.*;

@Service
@RequiredArgsConstructor
public class PlanShareServiceImpl implements PlanShareService{
	
	private final PlanShareRepo repo;

	@Override
	public List<PlanShareDto>select(int plan_id) {
		// TODO Auto-generated method stub
		return repo.select(plan_id);
	}

	@Override
	public int insert(PlanShareDto pld) {
		// TODO Auto-generated method stub
		return repo.insert(pld);
	}

	@Override
	public int delete(PlanShareDto pld) {
		// TODO Auto-generated method stub
		return repo.delete(pld);
	}


}
