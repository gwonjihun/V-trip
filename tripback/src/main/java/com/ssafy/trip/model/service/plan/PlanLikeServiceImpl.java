package com.ssafy.trip.model.service.plan;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.dto.plan.PlanConditionDto;
import com.ssafy.trip.dto.plan.PlanDto;
import com.ssafy.trip.dto.plan.PlanLikeDto;
import com.ssafy.trip.model.repo.plan.PlanLikeRepo;

import lombok.*;

@Service
@RequiredArgsConstructor
public class PlanLikeServiceImpl implements PlanLikeService{
	
	private final PlanLikeRepo repo;

	@Override
	public PlanLikeDto select(PlanLikeDto pld) {
		// TODO Auto-generated method stub
		return repo.select(pld);
	}

	@Override
	public int insert(PlanLikeDto pld) {
		// TODO Auto-generated method stub
		return repo.insert(pld);
	}

	@Override
	public int delete(PlanLikeDto pld) {
		// TODO Auto-generated method stub
		return repo.delete(pld);
	}


}
