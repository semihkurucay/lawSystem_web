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
	@Pattern(regexp = "^[a-zA-ZüğışçöÜĞİŞÇÖ\\s]+$", message = "İsmizde sadece harf olmalı")
	private String name;
	
	@Pattern(regexp = "^[a-zA-ZüğışçöÜĞİŞÇÖ]+$", message = "Soyismizde sadece harf olmalı")
	private String surname;
	
	@NotEmpty(message = "Mail adresi boş geçilemez")
	@Email(message = "Mail adresi geçersiz")
	private String mail;
	
	@Size(min = 4, max = 12, message = "Şifreniz en az 4, en fazla 12 karakterli olmalı")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-züğışçö])(?=.*[A-ZÜĞİŞÇÖ])(?=.*[@#$%^&+=!]).*$", message = "Şifrenizde en az 1 büyük, 1 küçük harf, 1 sayı ve 1 şekil (@#$%^&+=!) olmalı!")
	private String password;
}
