package com.ssafy.trip.model.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.dto.PlanLikeDto;
import com.ssafy.trip.model.repo.PlanLikeRepo;

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
