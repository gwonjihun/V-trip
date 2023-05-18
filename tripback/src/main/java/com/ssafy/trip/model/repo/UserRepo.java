package com.ssafy.trip.model.repo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.UserDto;

@Mapper
public interface UserRepo {
	UserDto selectUser(String id) throws SQLException;
	List<UserDto> selectAllUsers() throws SQLException;
	int deleteUser(String id) throws SQLException;
	int updateUser(UserDto user) throws SQLException;
	int insertUser(UserDto user) throws SQLException;
}
