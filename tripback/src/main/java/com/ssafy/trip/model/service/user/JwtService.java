package com.ssafy.trip.model.service.user;

import java.util.Map;

public interface JwtService {

	<T> String createAccessToken(Map<String, String> data);
	<T> String create(Map<String, String> data, String subject, long expir);
	Map<String, Object> getBody();
	String getUserType();
	String getId();
	boolean checkToken(String jwt);
	boolean checkAuthor(String user_id);
}
