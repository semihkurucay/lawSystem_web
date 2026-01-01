package com.skyazilim.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserIU {
	@NotEmpty(message = "İsim alanı boş geçilemez!")
	@Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Sadece harf girin!")
	@Size(min = 2, max = 25, message = "İsim en az 2 ve en fazla 25 karakter olmalı!")
	private String firstName;
	
	@NotEmpty(message = "Soyisim alanı boş geçilemez!")
	@Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "Sadece harf girin!")
	@Size(min = 2, max = 25, message = "Soyisim en az 2 ve en fazla 25 karakter olmalı!")
	private String lastName;
	
	@Email(message = "Geçersiz mail adresi!")
	private String mail;
	
	@NotEmpty(message = "Şifre alanı boş geçilemez!")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-züğışçö])(?=.*[A-ZÜĞİŞÇÖ])(?=.*[@#$%^&+=!]).*$", message = "Şifrenizde en az 1 büyük, 1 küçük harf, 1 sayı ve 1 şekil (@#$%^&+=!) olmalı!")
	@Size(min = 4, max = 12, message = "Şifre en az 4 ve en fazla 12 karakter olmalı!")
	private String password;
}
