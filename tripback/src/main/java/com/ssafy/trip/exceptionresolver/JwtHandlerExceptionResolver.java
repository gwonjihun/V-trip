package com.ssafy.trip.exceptionresolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.trip.exception.UnAuthorizedException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		try {
			if (ex instanceof UnAuthorizedException) {
				log.info(ex.getMessage());
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
				return new ModelAndView();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
