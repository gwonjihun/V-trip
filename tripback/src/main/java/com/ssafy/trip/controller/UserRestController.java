package com.ssafy.trip.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String DUPLICATED= "duplicated";
	static final String NOT_EXIST= "not_exist";
	
	private final UserService svc;
	private final JwtService jwtSvc;
	
	@Value("${file.path}")
	private String uploadPath;
	
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
	ResponseEntity<String> regist(@RequestBody UserDto user) throws SQLException {
		if (svc.selectUser(user.getId()) != null)
			return new ResponseEntity<String>(DUPLICATED, HttpStatus.OK);
		log.debug(user.toString());
		if (svc.insertUser(user) != 0)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.CREATED);
		else
			return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	ResponseEntity<String> update(@RequestBody UserDto user, Model model) throws SQLException {
		log.debug(user.toString());
		if ( user == null || svc.updateUser(user) == 0) {
			return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> delete(@PathVariable String id) throws SQLException {
		if (svc.selectUser(id) == null || svc.deleteUser(id) == 0)
			return new ResponseEntity<String>(NOT_EXIST, HttpStatus.NO_CONTENT);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<UserDto> userInfo(@PathVariable String id) throws SQLException {
		UserDto user = svc.selectUser(id);
		if (user != null) {
			log.debug(user.toString());
			user.setPassword(null);
			return new ResponseEntity<UserDto>(user, HttpStatus.OK);
		}
		else
			return new ResponseEntity<UserDto>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/option")
	ResponseEntity<List<UserDto>> selectUserOption(String word, int spp) throws SQLException {
		log.debug("{}, {}", word, spp);
		if (word == null) word = "";
		return new ResponseEntity<List<UserDto>>(svc.selectOption(word, spp), HttpStatus.OK);
	}
	
	@PostMapping("/img/{id}")
	ResponseEntity<Void> postUserImg(@PathVariable String id, @RequestParam("img") MultipartFile file) throws IllegalStateException, IOException {
//		FileUpload 관련 설정.
		if (file != null)
			log.debug("MultipartFile.isEmpty : {}", file.isEmpty());
		else
			log.debug("MultipartFile is null");
		if (file != null && !file.isEmpty()) {
			String saveFolder = new ClassPathResource(uploadPath).getFile().getAbsolutePath();
			log.debug("저장 폴더 : {}", saveFolder);
			File folder = new File(saveFolder);
			if (!folder.exists())
				folder.mkdirs();

			String originalFileName = file.getOriginalFilename();
			String saveFileName = id
					+ originalFileName.substring(originalFileName.lastIndexOf('.'));
			log.debug("원본 파일 이름 : {}, 실제 저장 파일 이름 : {}", file.getOriginalFilename(), saveFileName);
			file.transferTo(new File(folder, saveFileName));
			
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
