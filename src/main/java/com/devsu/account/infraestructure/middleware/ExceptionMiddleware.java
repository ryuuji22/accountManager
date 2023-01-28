/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devsu.account.infraestructure.middleware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.devsu.account.domain.exceptions.ApplicationDomainException;

/**
 *
 * @author diego
 */
@ControllerAdvice
public class ExceptionMiddleware extends ResponseEntityExceptionHandler {

	private static final Logger logger_ = LoggerFactory.getLogger(ExceptionMiddleware.class);
	public static final String EXCEPTION_MESSAGE = "!!!!!Exception: {}";

	@ExceptionHandler(ApplicationDomainException.class)
	public final ResponseEntity<Object> handleApplicationDomainException(ApplicationDomainException ex,
			WebRequest request) {

		logger_.info(ex.getClass().getName());
		logger_.warn(EXCEPTION_MESSAGE, ex.getMessage());

		return handleExceptionInternal(ex, this.buildErrorObject("Application Exception", ex), new HttpHeaders(),
				HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger_.info(ex.getClass().getName());
		logger_.warn(EXCEPTION_MESSAGE, ex.getMessage());

		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error -> 
			errorMap.put(error.getField(), error.getDefaultMessage())
		);
		return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public final ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
		logger_.info(ex.getClass().getName());
		logger_.warn(EXCEPTION_MESSAGE, ex.getMessage());
		return handleExceptionInternal(ex, this.buildErrorObject("Access Denied", ex), new HttpHeaders(),
				HttpStatus.FORBIDDEN, request);

	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		logger_.info(ex.getClass().getName());
		logger_.error(EXCEPTION_MESSAGE, ex);

		return handleExceptionInternal(ex, this.buildErrorObject("Unregistered Exception", ex), new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private Error buildErrorObject(final String title, Exception ex) {

		Error errorResponse = getError(title);

		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		errorResponse.setErrors(errors);

		return errorResponse;
	}

	private Error getError(final String title) {

		Error errorResponse = new Error();

		errorResponse.setMessage(title);

		return errorResponse;
	}

}
