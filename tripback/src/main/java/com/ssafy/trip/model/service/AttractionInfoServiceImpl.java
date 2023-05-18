package com.ssafy.trip.model.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.repo.AttractionInfoRepo;
import com.ssafy.trip.dto.AttractionInfoDto;
import com.ssafy.trip.dto.TripConditionDto;
@Service
public class AttractionInfoServiceImpl implements AttractionInfoService {

	@Autowired
	private AttractionInfoRepo dao;
	@Override
	public List<AttractionInfoDto> selectAll() throws SQLException {
		return dao.selectAll();
	}


	@Override
	public List<AttractionInfoDto> selectKeyword(TripConditionDto tripConditionDto) throws SQLException {
		
		return dao.selectKeyword(tripConditionDto);
	}

	@Override
	public List<AttractionInfoDto> selectHotplace() throws SQLException {
		// TODO Auto-generated method stub
		return dao.selectHotplace();
	}

//	public static void main(String[] args) throws SQLException {
//		AttractionInfoService dao = AttractionInfoServiceImpl.getInstance();
//		List<AttractionInfoDto> rs = dao.selectKeyword(6,12,"해운대");
//		for(AttractionInfoDto a : rs) {
//			System.out.println(a);
//		}
//	}
}
