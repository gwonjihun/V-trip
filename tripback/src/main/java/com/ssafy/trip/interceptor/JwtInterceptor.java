package com.ssafy.trip.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.trip.model.service.user.JwtService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

	private static final String HEADER_AUTH = "access-token";

	private final JwtService jwtSvc;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String method = request.getMethod();
		log.debug(method);
		if (method.equals("GET")) return true;

		final String token = request.getHeader(HEADER_AUTH);		

		if(token != null && jwtSvc.checkToken(token)){
			log.info("토큰 사용 가능 : {}", token);
			return true;
		}else{
			log.info("토큰 사용 불가능 : {}", token);
			response.setStatus(401);
			return false;
		}
	}
}
