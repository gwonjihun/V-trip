package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.dto.PlanDto;
import com.ssafy.trip.model.repo.PlanRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PlanSearviceImpl implements PlanSearvice{
	
	@Autowired
	private PlanRepo repo;
	@Override
	public List<PlanDto> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return repo.selectAll();
	}

	@Override
	public PlanDto select(int plan_id) throws SQLException {
		// TODO Auto-generated method stub
		PlanDto plan = repo.select(plan_id);
		log.debug(plan==null ? "null" : plan.toString());
		return plan;
	}

	@Override
	public int delete(int content_id) throws SQLException {
		// TODO Auto-generated method stub
		return repo.delete(content_id);
	}

	@Override
	public int updateread(int content_id) throws SQLException {
		// TODO Auto-generated method stub
		return repo.updateread(content_id);
	}

	@Override
	public int insert(PlanDto plan) throws SQLException {
		// TODO Auto-generated method stub
		return repo.insert(plan);
	}

	@Override
	public int updatePlan(PlanDto plan) throws SQLException{
		
		return repo.updatePlan(plan);
	}
	

}
