package com.ssafy.trip.model.repo;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.AttractionInfoDto;
import com.ssafy.trip.dto.TripConditionDto;
@Mapper
public interface AttractionInfoRepo {
	List<AttractionInfoDto> selectAll()  throws SQLException;
	List<AttractionInfoDto> selectKeyword(TripConditionDto tripConditionDto)  throws SQLException;
	List<AttractionInfoDto> selectHotplace()  throws SQLException;
}
