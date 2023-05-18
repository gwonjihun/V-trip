package com.ssafy.trip.model.service.user;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.dto.user.UserDto;

public interface UserService {
	UserDto selectUser(String id) throws SQLException;
	List<UserDto> selectAllUsers() throws SQLException;
	int deleteUser(String id) throws SQLException;
	int updateUser(UserDto user) throws SQLException;
	int insertUser(UserDto user) throws SQLException;
	
	UserDto loginUser(UserDto user) throws SQLException;
}
