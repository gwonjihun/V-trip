package com.ssafy.trip.dto.plan;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlanDetailDto {
	/*
	 detail_id : 여행 세부 계획 primary key
	 plan_id : plan 테이블 primary key
	 content_id : 관광지 정보 테이블 primary key
	 days : 여행 일자
	 index : 여행 일자별 순서
	 */
	private int detail_id;
	private int plan_id;
	private int content_id;
	private int days;
	private int index;
	
	public PlanDetailDto(int plan_id, int content_id, int days, int index){
		this.plan_id = plan_id;
		this.content_id = content_id;
		this.days = days;
		this.index = index;
	}
	public PlanDetailDto(int content_id, int days, int index){
		this.content_id = content_id;
		this.days = days;
		this.index = index;
	}
}
