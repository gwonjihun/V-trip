package com.ssafy.trip.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {
	private String id;
	private String name;
	private String password;
	private String email;
	private String user_type; //admin, user
	private String nickname;
	private int point;
	private String deleteat;
	
	public UserDto(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public UserDto(String id, String name, String password, String email, String nickname) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.user_type = "user";
		this.nickname = nickname;
	}

	public UserDto(String id, int point) {
		super();
		this.id = id;
		this.point = point;
	}
}

