package com.ssafy.trip.model.service.user;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.trip.dto.user.UserDto;
import com.ssafy.trip.model.repo.board.BoardRepo;
import com.ssafy.trip.model.repo.user.UserRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepo repo;
	private final BoardRepo boardRepo;
	
	@Override
	public UserDto selectUser(String id) throws SQLException {
		return repo.selectUser(id);
	}

	@Override
	public List<UserDto> selectAllUsers() throws SQLException {
		return repo.selectAllUsers();
	}

	@Override
	@Transactional
	public int deleteUser(String id) throws SQLException {
		boardRepo.deleteAll(id);
		return repo.deleteUser(id);
	}

	@Override
	public int updateUser(UserDto user) throws SQLException {
		System.out.println(user);
		return repo.updateUser(user);
	}

	@Override
	@Transactional
	public int insertUser(UserDto user) throws SQLException {
		if (repo.selectUser(user.getId()) != null) return 0;
		int r = repo.insertUser(user);
		return r;
	}
	
	@Override
	public UserDto loginUser(UserDto user) throws SQLException {
		UserDto login = repo.selectUser(user.getId());
		if (login != null && login.getDeleteat() == null &&
				login.getPassword().equals(user.getPassword()))
			return login;
		return null;
	}
	
	@Override
	public List<UserDto> selectOption(String word, int spp) throws SQLException {
		return repo.selectOption(word, spp);
	}
}
