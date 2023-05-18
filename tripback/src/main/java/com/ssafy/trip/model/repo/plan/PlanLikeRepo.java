package com.ssafy.trip.model.repo.plan;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.plan.PlanLikeDto;

@Mapper
public interface PlanLikeRepo {

	PlanLikeDto select(PlanLikeDto pld);
	int insert(PlanLikeDto pld);
	int delete(PlanLikeDto pld);
}
