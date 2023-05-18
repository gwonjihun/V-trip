package com.ssafy.trip.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor // select문
@ToString
public class PlanLikeDto {
	private int Plan_id;
	private String users_id;
}
