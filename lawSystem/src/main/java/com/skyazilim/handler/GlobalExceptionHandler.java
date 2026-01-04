package com.skyazilim.handler;

import java.io.ObjectInputStream.GetField;
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
	@ExceptionHandler(exception = BaseException.class)
	public ResponseEntity<ApiError> handlerException(BaseException exception, WebRequest request){
		return ResponseEntity.badRequest().body(createApiError(exception.getMessage(), request));
	}
	
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, WebRequest request){
		Map<String, List<String>> mapError = new HashMap<>();
		
		for(ObjectError objectError : e.getBindingResult().getAllErrors()) {
			String fieldName = ((FieldError)objectError).getField();
			
			if(mapError.containsKey(fieldName)) {
				mapError.put(fieldName, addValueToList(mapError.get(fieldName), objectError.getDefaultMessage()));
			}else {
				mapError.put(fieldName, addValueToList(new ArrayList<>(), objectError.getDefaultMessage()));
			}
		}
		
		return ResponseEntity.badRequest().body(createApiError(mapError, request));
	}

	private <E> ApiError<E> createApiError(E message, WebRequest request){
		ApiError<E> apiError = new ApiError<>();
		apiError.setId(UUID.randomUUID().toString());
		apiError.setStatus(HttpStatus.BAD_REQUEST.value());
		
		Exception exception = new Exception<>();
		exception.setDate(new Date());
		exception.setPath(request.getDescription(false).substring(4));
		exception.setMessage(message);
		
		apiError.setException(exception);
		
		return apiError;
	}
	
	private List<String> addValueToList(List<String> list, String message){
		list.add(message);
		return list;
	}
}
