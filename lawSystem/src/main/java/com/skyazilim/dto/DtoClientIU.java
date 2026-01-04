package com.skyazilim.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoClientIU {
	@NotEmpty(message = "Müvekkil TC/VKN boş geçilemez")
	@Pattern(regexp = "^[0-9]+$", message = "TC/VKN sadece sayı olmalı")
	@Size(min = 10, max = 11, message = "Müvekkil VKN 10, TC 11 karakter olmalı")
	private String id;
	
	@NotEmpty(message = "Müvekkil adı boş geçilemez")
	private String name;
	
	@NotEmpty(message = "Müvekkil telefon no boş geçilemez")
	@Pattern(regexp = "^[0-9]+$", message = "Telefon no sadece sayı olmalı")
	@Size(min = 7, max = 13, message = "Müvekkil telefon no en az 7, en fazla 13 karakter olmalı")
	private String phone;
	
	@NotEmpty(message = "Müvekkil address boş geçilemez")
	private String address;
}
