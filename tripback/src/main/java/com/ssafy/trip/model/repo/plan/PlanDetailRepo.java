package com.ssafy.trip.model.repo.plan;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.board.BoardCommentDto;
import com.ssafy.trip.dto.plan.PlanDetailDto;
import com.ssafy.trip.dto.plan.PlanDto;

@Mapper
public interface PlanDetailRepo {
	int insert(List<PlanDetailDto> list)throws SQLException;
	List<PlanDetailDto> select(int plan_id) throws SQLException;
	int delete(int plan_id) throws SQLException;
}
