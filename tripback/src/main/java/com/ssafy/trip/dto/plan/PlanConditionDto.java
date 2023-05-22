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
	private String key = "title";
	private int sidocode = 0;
	private String word = "";

	public PlanConditionDto(int sido_code) {
		this.sido_code = sido_code;
	}
	public PlanConditionDto(String key,String word) {
		super();
		this.key = key;
		this.word = word;
	}
	public PlanConditionDto(int sido_code,String word) {
		super();
		this.sido_code = sido_code;
		this.word = word;
	}
	
	
}
