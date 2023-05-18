package com.ssafy.trip.model.service.plan;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.plan.PlanLikeDto;

public interface PlanLikeService {

	PlanLikeDto select(PlanLikeDto pld);
	int insert(PlanLikeDto pld);
	int delete(PlanLikeDto pld);
}
