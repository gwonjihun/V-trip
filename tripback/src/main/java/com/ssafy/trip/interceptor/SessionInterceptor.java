package com.ssafy.trip.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.trip.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SessionInterceptor implements HandlerInterceptor {
	static final int UNAUTHORIZED = 401;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String method = request.getMethod();
		log.debug("Method={}", method);
		HttpSession session = request.getSession();
		if (!method.equals("GET")) {
			UserDto login = (UserDto) session.getAttribute("userinfo");
			if (login == null) {
				log.debug("Not login");
				response.sendError(UNAUTHORIZED);
				return false;
			}
		}
		return true;
	}
}
