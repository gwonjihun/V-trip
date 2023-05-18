package com.ssafy.trip.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.BoardCommentDto;
import com.ssafy.trip.dto.PlanDetailDto;
import com.ssafy.trip.dto.PlanDto;

@Mapper
public interface PlanDetailRepo {
	int insert(List<PlanDetailDto> list)throws SQLException;
	List<PlanDetailDto> select(int plan_id) throws SQLException;
	int delete(int plan_id) throws SQLException;
}
