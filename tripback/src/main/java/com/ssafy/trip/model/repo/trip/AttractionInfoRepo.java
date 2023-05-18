package com.ssafy.trip.model.repo.trip;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.trip.AttractionInfoDto;
import com.ssafy.trip.dto.trip.TripConditionDto;
@Mapper
public interface AttractionInfoRepo {
	List<AttractionInfoDto> selectAll()  throws SQLException;
	List<AttractionInfoDto> selectKeyword(TripConditionDto tripConditionDto)  throws SQLException;
	List<AttractionInfoDto> selectHotplace()  throws SQLException;
}
