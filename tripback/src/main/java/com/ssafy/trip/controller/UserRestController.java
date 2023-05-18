package com.ssafy.trip.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.dto.user.UserDto;
import com.ssafy.trip.model.service.user.JwtService;
import com.ssafy.trip.model.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tripapi/user")
@RequiredArgsConstructor
public class UserRestController {
	
	private final UserService svc;
	private final JwtService jwtSvc;
	
	@PostMapping("/login")
	ResponseEntity<?> login(@RequestBody UserDto user) throws SQLException {
		UserDto login = svc.loginUser(user);
		Map<String, Object> result = new HashMap<>();
		if (login != null) {
			Map<String, String> data = new HashMap<>();
			data.put("id", login.getId());
			data.put("user_type", login.getUser_type());
			String accessToken = jwtSvc.createAccessToken(data);
			result.put("access-token", accessToken);
			login.setPassword(null);
			result.put("userinfo", login);
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/logout")
	ResponseEntity<?> logout() {
		// delete refreshToken
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/regist")
	ResponseEntity<?> regist(UserDto user) throws SQLException {
		log.debug(user.toString());
		if (svc.insertUser(user) != 0)
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		else
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	ResponseEntity<?> update(@RequestBody UserDto user, Model model) throws SQLException {
		if ( user.getId()== null || svc.updateUser(user) == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			// token interceptor
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> delete(@PathVariable String id) throws SQLException {
		// token interceptor
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<UserDto> userInfo(@PathVariable String id) throws SQLException {
		UserDto user = svc.selectUser(id);
		if (user != null) {
			user.setPassword(null);
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		}
		else
			return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
	}
}
