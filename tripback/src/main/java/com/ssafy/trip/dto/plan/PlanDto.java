package com.ssafy.trip.dto.plan;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // select문
@ToString
public class PlanDto {
	private String plan_id; //select, update, delete
	private String writerid;// insert select, update
	private String title; // insert select update
	private int reads;  // select
	private int share; // select update
	private String sidocode; // insert select update
	private String startDate; // insert select update
	private String endDate; // insert select update
	private String createat; // insert
	private String updateat; // update
	private String deleteat; // deleteat
	
	private String sidoname; // select에서만 필요
	
	private int likes;// select에서만 필요
	
	
	
	public PlanDto(String writerid,String title,int Share, String sidocode, String startDate, String endDate) {
		//그러면은 
		this.writerid = writerid;
		this.title = title;
		this.sidocode = sidocode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.share = Share;
	}// 이건 insert용
	//update용 생성자는?


	
	
}
