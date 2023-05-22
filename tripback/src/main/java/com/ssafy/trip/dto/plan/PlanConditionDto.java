package com.ssafy.trip.dto.plan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlanConditionDto {

	private int pgno = 1;
	private int spp = 20;
	private int sidocode = 0;
	private String key = "title";
	private String word = "";

	public PlanConditionDto(int pgno, int spp, int sidocode) {
		this.sidocode = sidocode;
		this.pgno = pgno;
		this.spp = spp;
	}

	public PlanConditionDto(int pgno, String key, String word) {
		super();
		this.pgno = pgno;
		this.key = key;
		this.word = word;
	}

	public PlanConditionDto(int pgno, int spp) {
		super();
		this.pgno = pgno;
		this.spp = spp;
	}

	public PlanConditionDto(int sidocode) {
		super();
		this.sidocode = sidocode;
	}
	
	public int getStart() {
		return (pgno-1)*spp;
	}
}
