package com.ssafy.trip.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ComentDto {
	private String coment_id;
	private String coment;
	private String writername;
	private String content_id;
	private String link;
	private String createat;
	private String updateat;
	private String deleteat;
}
