/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.account.infraestructure.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author diego
 */
@Component
public class JWTFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && !authHeader.isEmpty() && authHeader.startsWith("Bearer ")) {
			String jwt = authHeader.substring(7);
			if (jwt == null || jwt.isEmpty()) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token in Bearer Header");
			} else {
				try {
					String identification = jwtUtil.validateTokenAndRetrieveSubject(jwt);
					UserDetails userDetails = userDetailsService.loadUserByUsername(identification);
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
							identification, userDetails.getPassword(), userDetails.getAuthorities());
					if (SecurityContextHolder.getContext().getAuthentication() == null) {
						SecurityContextHolder.getContext().setAuthentication(authToken);
					}
				} catch (UsernameNotFoundException exc) {
					var errorResponse = new ErrorResponse(exc, "User Not Found");
					response.setStatus(HttpStatus.NOT_FOUND.value());
					response.getWriter().write(convertObjectToJson(errorResponse));

				} catch (JWTVerificationException exc) {
					var errorResponse = new ErrorResponse(exc, "Invalid JWT Token");
					response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
					response.getWriter().write(convertObjectToJson(errorResponse));

				}
			}
		}

		filterChain.doFilter(request, response);
	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
