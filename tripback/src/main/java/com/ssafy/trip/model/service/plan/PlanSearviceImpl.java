package com.ssafy.trip.model.service.plan;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.dto.board.BoardDto;
import com.ssafy.trip.dto.plan.PlanConditionDto;
import com.ssafy.trip.dto.plan.PlanDto;
import com.ssafy.trip.model.repo.plan.PlanRepo;

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
	public int selectCount(PlanConditionDto option) throws SQLException{
		return repo.selectCount(option);
	}

	@Override
	public Map<String, Object> selectOption(PlanConditionDto option) throws SQLException{
		Map<String, Object> map = new HashMap<>();
		int count = repo.selectCount(option);
		int start = option.getStart();
		map.put("count", count);
		if (start < count) {
			List<PlanDto> list = repo.selectOption(option);
			map.put("boards", list);
		} else {
			map.put("msg", "pageover");
		}
		return map;
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
