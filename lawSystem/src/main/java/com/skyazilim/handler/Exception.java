package com.skyazilim.handler;

import java.util.Date;

import lombok.Data;

@Data
public class Exception <E> {
	private String path;
	private Date date;
	private E message;
}
