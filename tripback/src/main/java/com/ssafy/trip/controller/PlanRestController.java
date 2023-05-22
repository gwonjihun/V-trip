package com.ssafy.trip.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.dto.board.BoardSearchOptionDto;
import com.ssafy.trip.dto.plan.PlanConditionDto;
import com.ssafy.trip.dto.plan.PlanDetailDto;
import com.ssafy.trip.dto.plan.PlanDto;
import com.ssafy.trip.dto.plan.PlanInsertDto;
import com.ssafy.trip.model.service.plan.PlanDetailService;
import com.ssafy.trip.model.service.plan.PlanSearvice;
import com.ssafy.trip.model.service.user.JwtService;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tripapi/plan")
@RequiredArgsConstructor
public class PlanRestController {

	private final PlanSearvice svc;
	private final PlanDetailService pdsvc;
	
	private final JwtService jwtSvc;

	@GetMapping
	ResponseEntity<Map<String, Object>> list() throws SQLException {
		// plan 목록 조회
		Map<String, Object> result = new HashMap<String, Object>();
		List<PlanDto> list = svc.selectAll();
		if (list != null && list.size() > 0) {
			result.put("plans", list);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			// 여행 계획이 0개이거나 list를 못불러온경우
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/option")
	ResponseEntity<Map<String, Object>> option(PlanConditionDto option) throws SQLException {
		Map<String, Object> result = svc.selectOption(option);
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
	@GetMapping("/{plan_id}")
	ResponseEntity<Map<String, Object>> detail(@PathVariable int plan_id) throws SQLException {
		// plan 세부 조회
		Map<String, Object> result = new HashMap<String, Object>();
		PlanDto Dto = svc.select(plan_id);
		List<PlanDetailDto> planlist = pdsvc.select(plan_id);
		// 여기서 디테일 값을 가져와야한다.
		if (Dto != null && planlist != null && planlist.size() > 0) {
			svc.updateread(plan_id);
			result.put("plan", Dto);
			result.put("planlist", planlist);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		} else {
			// 여행 계획이 0개이거나 list를 못불러온경우
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.NO_CONTENT);
		}
	}
	@Transactional
	@PostMapping("")
	ResponseEntity<Void> regist(@RequestBody PlanInsertDto tn_in) throws SQLException {
		// plan 세부 조회
//		log.debug(map.toString());
		
		PlanDto plan = tn_in.getPlan();
		log.debug(plan.toString());
		List<PlanDetailDto> planlist = tn_in.getPlanlist();
		log.debug(planlist.toString());
	
		
		log.debug("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		if (jwtSvc.checkAuthor(plan.getWriterid())) {
			int plan_id = svc.insert(plan);
			for(PlanDetailDto plandetail : planlist) {
				log.debug(plan.getPlan_id());
				plandetail.setPlan_id(Integer.parseInt(plan.getPlan_id()));
			}
			int detail_len = pdsvc.insert(planlist);
			if (detail_len >0)
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
	@DeleteMapping("/{plan_id}")
	ResponseEntity<Void> delete(@PathVariable int plan_id,@RequestBody PlanDto plan) throws SQLException {
		/*
		 * data input
		 * {
		 * 	
		 * }
		 * */
		// plan 세부 조회
		if (jwtSvc.checkAuthor(plan.getWriterid())) {
			int r = svc.delete(plan_id);
			if (r != 0)
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			else
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
	}
	@Transactional
	@PutMapping("/{plan_id}")
	ResponseEntity<Void> detail_update(@PathVariable int plan_id, @RequestBody Map<String, Object> map) throws SQLException {
		/*
		 * { plan :{ plan dto 정보}. planlist : [{plandetaildto}.....] } 으로 데이터를 받자
		 * 1. plan_id를 통해서 plan이 있는지 확인 2. plan과 session아이디가 동일
		 * 3. map에서 plan_id를 찾아서 plan_detail 전부 삭제 
		 * 4. plan 업데이트 
		 * 5. plan_detail insert #
		 * 3,4,5는 하나의 트랜잭션이다.
		 */
		Map<String, Object> result = new HashMap<String, Object>();
		PlanDto plan = svc.select(plan_id); // 기존 데이터
		if (plan == null) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (jwtSvc.checkAuthor(plan.getWriterid())) {
			PlanDto plan_in = (PlanDto) map.get("plan");
			List<PlanDetailDto> plan_list = (LinkedList<PlanDetailDto>) map.get("planlist");
			svc.updatePlan(plan_in);
			pdsvc.delete(plan_id);
			pdsvc.insert(plan_list);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

}
