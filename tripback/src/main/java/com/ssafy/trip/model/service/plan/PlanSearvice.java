package com.ssafy.trip.model.service.plan;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.plan.PlanDto;

public interface PlanSearvice {
	List<PlanDto> selectAll() throws SQLException;//d
	PlanDto select(int plan_id) throws SQLException;//d
//	List<PlanDto> selectHotplace() throws SQLException;
	int delete(int content_id) throws SQLException;//d
	int updateread(int content_id) throws SQLException;//d
	int insert(PlanDto plan) throws SQLException;

	int updatePlan(PlanDto plan) throws SQLException;
}
