package com.skyazilim.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	NOT_FOUND_BOOK("1004","Kitap bulunamadı!"),
	NOT_FOUND_WRITER("2004", "Yazar bulunamadı!"),
	NOT_FOUND_READING("3004", "Okuma bulunamadı!"),
	THIS_READING_BOOK("3005", "Kitap okunuyor!"),
	ILLEGAL_PAGE("3006", "Hatalı sayfa girişi!"),
	NOT_FOUND_USER("4004", "Kullanıcı bulunamadı!"),
	REPATITIVE_USER_MAIL("4005", "Mail adresi kayıtlı!"),
	INCORRECT_USER("4006", "Yanlış mail adresi ya da şifre!");
	
	private String code, message;
	
	MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
