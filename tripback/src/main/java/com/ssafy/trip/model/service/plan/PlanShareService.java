package com.ssafy.trip.model.service.plan;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.plan.PlanConditionDto;
import com.ssafy.trip.dto.plan.PlanDto;
import com.ssafy.trip.dto.plan.PlanLikeDto;
import com.ssafy.trip.dto.plan.PlanShareDto;

public interface PlanShareService {
	List<PlanShareDto> select(int plan_id);
	int insert(PlanShareDto pld);
	int delete(PlanShareDto pld);
}
