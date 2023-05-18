package com.ssafy.trip.model.repo.user;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.dto.user.UserDto;

@Mapper
public interface UserRepo {
	UserDto selectUser(String id) throws SQLException;
	List<UserDto> selectAllUsers() throws SQLException;
	int deleteUser(String id) throws SQLException;
	int updateUser(UserDto user) throws SQLException;
	int insertUser(UserDto user) throws SQLException;
}
