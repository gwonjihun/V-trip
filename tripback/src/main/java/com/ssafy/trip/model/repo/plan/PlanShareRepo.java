package com.ssafy.trip.model.repo.plan;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.plan.PlanShareDto;

@Mapper
public interface PlanShareRepo {

	List<PlanShareDto> select(int plan_id);
	int insert(PlanShareDto pld);
	int delete(PlanShareDto pld);
}
