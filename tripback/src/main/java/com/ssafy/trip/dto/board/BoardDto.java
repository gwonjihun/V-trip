package com.ssafy.trip.dto.board;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDto {
	private int content_id;
	private String title;
	private String content;
	private String writername;
	private int reads;
	private String createat;
	private String updateat;
	private String deleteat;
	
	private int comment_num;
	private int like_num;
	
	private String nickname;

	public BoardDto(String title, String content, String writername) {
		super();
		this.title = title;
		this.content = content;
		this.writername = writername;
	}

	public BoardDto(int content_id, String title, String content, String writername) {
		super();
		this.content_id = content_id;
		this.title = title;
		this.content = content;
		this.writername = writername;
	}
}
