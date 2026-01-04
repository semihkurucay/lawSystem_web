package com.skyazilim.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError<E> {
	private String id;
	private Integer status;
	private Exception<E> exception;
}
