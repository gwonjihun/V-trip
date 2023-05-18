package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.dto.AttractionInfoDto;
import com.ssafy.trip.dto.TripConditionDto;

public interface AttractionInfoService {
	List<AttractionInfoDto> selectAll()  throws SQLException;
	List<AttractionInfoDto> selectKeyword(TripConditionDto tripConditionDto)  throws SQLException;
	List<AttractionInfoDto> selectHotplace()  throws SQLException;
}
