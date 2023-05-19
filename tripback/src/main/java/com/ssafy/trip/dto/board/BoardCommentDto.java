package com.ssafy.trip.dto.board;

import java.util.ArrayList;
import java.util.List;

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
public class BoardCommentDto {
	private int comment_id;
	private String comment;
	private String writername;
	private int content_id;
	private int link;
	private String createat;
	private String updateat;
	private String deleteat;
	
	private String nickname;
	
	private List<BoardCommentDto> children = new ArrayList<>();

	public BoardCommentDto(int comment_id, String comment, String writername, int content_id, int link, String createat,
			String updateat, String deleteat, String nickname) {
		super();
		this.comment_id = comment_id;
		this.comment = comment;
		this.writername = writername;
		this.content_id = content_id;
		this.link = link;
		this.createat = createat;
		this.updateat = updateat;
		this.deleteat = deleteat;
		this.nickname = nickname;
	}

	public BoardCommentDto(String comment, String writername, int content_id, int link) {
		super();
		this.comment = comment;
		this.writername = writername;
		this.content_id = content_id;
		this.link = link;
	}
}
