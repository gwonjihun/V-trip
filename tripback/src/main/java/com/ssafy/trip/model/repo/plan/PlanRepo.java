package com.ssafy.trip.model.repo.plan;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.plan.PlanDto;

@Mapper
public interface PlanRepo {
	List<PlanDto> selectAll() throws SQLException;//d
	PlanDto select(int plan_id) throws SQLException;//d
//	List<PlanDto> selectHotplace() throws SQLException;
	int delete(int plan_id) throws SQLException;//d
	int updateread(int plan_id) throws SQLException;//d
	int updatePlan(PlanDto plan) throws SQLException;
	int insert(PlanDto plan) throws SQLException;
}
