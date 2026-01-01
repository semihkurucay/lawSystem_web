package com.skyazilim.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.skyazilim.exception.BaseException;

@ControllerAdvice(basePackages = "com.skyazilim")
public class GlobalExceptionHandler {
	@ExceptionHandler(value = {BaseException.class})
	public ResponseEntity<ApiError> handlerException(BaseException exception, WebRequest request){
		return ResponseEntity.badRequest().body(createApiError(exception.getMessage(), request));
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request){
		Map<String, List<String>> errorMap = new HashMap<>();
		
		for(ObjectError objectError : e.getBindingResult().getAllErrors()) {
			String filedName = ((FieldError)objectError).getField();
			
			if(errorMap.containsKey(filedName)) {
				errorMap.put(filedName, addToErrorList(errorMap.get(filedName), objectError.getDefaultMessage()));
			}else {
				errorMap.put(filedName, addToErrorList(new ArrayList<>(), objectError.getDefaultMessage()));
			}
		}
		
		return ResponseEntity.badRequest().body(createApiError(errorMap, request));
	}
	
	private <E> ApiError<E> createApiError(E message, WebRequest request){
		ApiError<E> apiError = new ApiError<>();
		apiError.setStatus(HttpStatus.BAD_REQUEST.value());
		apiError.setId(UUID.randomUUID().toString());
		
		Exception<E> exception = new Exception<>();
		exception.setCreateDate(new Date());
		exception.setMessage(message);
		exception.setPath(request.getDescription(false).substring(4));
		
		apiError.setException(exception);
		return apiError;
	}
	
	private List<String> addToErrorList(List<String> errorList, String message){
		errorList.add(message);
		return errorList;
	}
}
