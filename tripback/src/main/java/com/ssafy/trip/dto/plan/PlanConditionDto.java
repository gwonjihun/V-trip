package com.ssafy.trip.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PlanConditionDto {
	private int sido_code = 0;
	private String title = "";
	
	public PlanConditionDto( String title) {
		this.title = title;
	}
	public PlanConditionDto(int sido_code) {
		this.sido_code = sido_code;
	}
	
	
}
