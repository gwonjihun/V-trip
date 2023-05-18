package com.ssafy.trip.model.service;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.PlanLikeDto;

public interface PlanLikeService {

	PlanLikeDto select(PlanLikeDto pld);
	int insert(PlanLikeDto pld);
	int delete(PlanLikeDto pld);
}
