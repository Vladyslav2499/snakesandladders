package com.codingtask.snakesandladders.controller.exceptionhandler;

import com.codingtask.snakesandladders.controller.error.ApiError;
import com.codingtask.snakesandladders.exception.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = EntityNotFoundException.class)
	public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException exception) {
		ApiError error = new ApiError(HttpStatus.NOT_FOUND, exception.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
