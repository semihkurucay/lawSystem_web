package com.skyazilim.handler;

import lombok.Data;

@Data
public class ApiError <E>{
	private String id;
	private Integer status;
	private Exception<E> exception;
}
