package com.ssafy.trip.model.service.trip;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.dto.trip.AttractionInfoDto;
import com.ssafy.trip.dto.trip.TripConditionDto;
import com.ssafy.trip.model.repo.trip.AttractionInfoRepo;
@Service
public class AttractionInfoServiceImpl implements AttractionInfoService {

	@Autowired
	private AttractionInfoRepo dao;
	@Override
	public List<AttractionInfoDto> selectAll() throws SQLException {
		return dao.selectAll();
	}
	@Override
	public AttractionInfoDto select(String content_id) throws SQLException {
		return dao.select(Integer.parseInt(content_id));
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
