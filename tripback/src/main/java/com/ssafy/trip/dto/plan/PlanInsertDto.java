package com.ssafy.trip.dto.plan;

import java.util.LinkedList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanInsertDto {
	private PlanDto plan;
	private List<PlanDetailDto> planlist;
}
