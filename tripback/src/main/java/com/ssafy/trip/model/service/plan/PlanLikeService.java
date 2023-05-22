package com.ssafy.trip.model.service.plan;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.plan.PlanConditionDto;
import com.ssafy.trip.dto.plan.PlanDto;
import com.ssafy.trip.dto.plan.PlanLikeDto;

public interface PlanLikeService {
	PlanLikeDto select(PlanLikeDto pld);
	int insert(PlanLikeDto pld);
	int delete(PlanLikeDto pld);
}
