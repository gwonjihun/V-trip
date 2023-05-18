package com.ssafy.trip.model.repo;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.PlanLikeDto;

@Mapper
public interface PlanLikeRepo {

	PlanLikeDto select(PlanLikeDto pld);
	int insert(PlanLikeDto pld);
	int delete(PlanLikeDto pld);
}
