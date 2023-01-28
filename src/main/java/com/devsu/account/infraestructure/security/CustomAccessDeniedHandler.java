package com.devsu.account.infraestructure.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.devsu.account.domain.utils.ConversionUtil;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AccessDeniedException e) throws IOException, ServletException {

		var errorResponse = new ErrorResponse("Unauthorized access","You have no access for this resource");
		httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		httpServletResponse.getWriter().write(ConversionUtil.convertObjectToJson(errorResponse));
	}

}
