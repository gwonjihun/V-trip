package com.ssafy.trip.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.dto.AttractionInfoDto;
import com.ssafy.trip.dto.TripConditionDto;
import com.ssafy.trip.model.service.AttractionInfoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/tripapi/search")
public class TripRestController {

	
	@Autowired
	private AttractionInfoService svc;
	
	@GetMapping("/search")
	ResponseEntity<?> search(@RequestParam int sido_code ,@RequestParam int content_type_id,@RequestParam String title) throws SQLException{
		System.out.println(title);
		log.debug(title);
		TripConditionDto dto = new TripConditionDto(sido_code, content_type_id, title);
		System.out.println(dto);
		log.debug(dto.toString());
		List<AttractionInfoDto> aa = svc.selectKeyword(dto);
		if(aa != null) {
		return new ResponseEntity<List<AttractionInfoDto>>(aa,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
//		
//	}
	
	
	@GetMapping("/hotplace")
	protected ResponseEntity<?> selectHotplace()
			throws  SQLException {
		log.debug("hotplace  search starts");
		List<AttractionInfoDto> Attrations = svc.selectHotplace();
		log.debug(Integer.toString(Attrations.size()));
		List<AttractionInfoDto> Hotplaces = sortHotplace(Attrations);
		if(Hotplaces!=null ) {
			return new ResponseEntity<List<AttractionInfoDto>>(Hotplaces,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}

	}
	
	private List<AttractionInfoDto> sortHotplace(List<AttractionInfoDto> Attrations) {
		List<AttractionInfoDto> result = new ArrayList<>();
		HashMap<String, List<AttractionInfoDto>> Attration_geo =  new HashMap<>();
		
		//각 시군구 코드 값을 key값으로 하여 지역별 관광지 정보로 나누어준다.
		for(AttractionInfoDto data : Attrations) {
			String Hash_key =Integer.toString(data.getSido_code());
			if(!Attration_geo.containsKey(Hash_key)) {
				//list를 선언해주면서 add해준다.
				List<AttractionInfoDto> values = new ArrayList<>();
				values.add(data);
				Attration_geo.put(Hash_key, values);
			}else{
				List<AttractionInfoDto> values = Attration_geo.get(Hash_key);
				values.add(data);
			}
		}
		//지역별 관광지 중 인기가 많은 관광지 List를 만든다.
		for( String strKey : Attration_geo.keySet() ){
			List<AttractionInfoDto> strValue = Attration_geo.get(strKey);
			Collections.sort(strValue);
			result.add(strValue.get(0));
//			Collections.sort((List<AttractionInfoDto>) strValue);
//			System.out.println("----"+strKey+"-----------------");
//			for(AttractionInfoDto data : strValue) {
//			System.out.println("123123"+data );
		}
		Collections.sort(result, (AttractionInfoDto o1, AttractionInfoDto o2) -> 
			 Integer.compare(o1.getSido_code(), o2.getSido_code())
				);
		//시군구별  List 완성
		return result;
	}
}
