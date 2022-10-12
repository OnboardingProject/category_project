package com.onboarding.accountOverview.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<?> idException(IdNotFoundException ex){
		return new ResponseEntity<>(ex.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> invalidException(MethodArgumentNotValidException ex){
		List<String> errors=ex.getFieldErrors().stream().map(e->e.getField()+" : "+e.getDefaultMessage())
				.collect(Collectors.toList());
		ErrorInfo errorInfo=new ErrorInfo();
		errorInfo.setErrorMessage(errors);
		errorInfo.setErrorDate(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.BAD_REQUEST);
		
	} 
}
