package com.skyazilim.exception;

import lombok.Getter;

@Getter
public enum MessageType {
	USER_NOT_FOUND("1004","Kullanıcı Bulunamadı"),
	USER_LONGIN_INCORRECT("1005", "Mail ya da şifreniz yanlış, lütfen tekrar deneyin"),
	CLIENT_NOT_FOUND("2004", "Müvekkil bulunamadı"),
	CLIENT_AVAIBLE("2005", "Müvekkil sistemde kayıtlı"),
	CASE_NOT_FOUND("3004", "Dava dosyası bulunamadı"),
	CASE_AVAIBLE("3005", "Dava dosyası sistemde mevcut"),
	CASE_HISTORY_NOT_FOUND("4004", "Dava geçmişi bulunamadı");
	
	private String code;
	private String message;
	
	MessageType(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
