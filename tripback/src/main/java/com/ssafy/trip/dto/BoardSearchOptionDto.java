package com.ssafy.trip.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardSearchOptionDto {
	
	private int pgno = 1;
	private int spp = 20;
	private String key = "none";
	private String word;
	private Boolean notice = false;
	
	public BoardSearchOptionDto(int pgno, int spp, String key, String word) {
		this.pgno = pgno;
		this.spp = spp;
		this.key = key;
		this.word = word;
	}
	
	public BoardSearchOptionDto(int pgno, String key, String word) {
		this.pgno = pgno;
		this.key = key;
		this.word = word;
	}
	
	public BoardSearchOptionDto(String key, String word) {
		this.key = key;
		this.word = word;
	}
	
	public BoardSearchOptionDto(int pgno, int spp) {
		this.pgno = pgno;
		this.spp = spp;
	}

	public BoardSearchOptionDto(int pgno) {
		this.pgno = pgno;
	}
	
	public int getStart() {
		return (pgno-1)*spp;
	}

}
