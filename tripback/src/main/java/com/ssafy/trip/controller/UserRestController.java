package com.ssafy.trip.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

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
import com.ssafy.trip.model.service.user.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/tripapi/user")
@RequiredArgsConstructor
public class UserRestController {
	
	private final UserService svc;
	
	@PostMapping("/login")
	ResponseEntity<?> login(UserDto user, HttpSession session) throws SQLException {
		UserDto login = svc.loginUser(user);
		if (login != null) {
			session.setAttribute("userinfo", login);;
			return new ResponseEntity<UserDto>(login, HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/logout")
	ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
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
	ResponseEntity<?> update(@RequestBody UserDto user, HttpSession session, Model model) throws SQLException {
		if ( user.getId()== null || svc.updateUser(user) == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			session.setAttribute("userinfo", svc.selectUser(user.getId()));
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<?> delete(@PathVariable String id, HttpSession session, Model model) throws SQLException {
		UserDto login = (UserDto) session.getAttribute("userinfo");
		if (!login.getId().equals(id) && !login.getUser_type().equals("admin")) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		int r = svc.deleteUser(id);
		if (r == 0) {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		if (!login.getUser_type().equals("admin")) {
			session.invalidate();
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<UserDto> userInfo(@PathVariable String id) throws SQLException {
		UserDto user = svc.selectUser(id);
		if (user != null)
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		else
			return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
	}
}
