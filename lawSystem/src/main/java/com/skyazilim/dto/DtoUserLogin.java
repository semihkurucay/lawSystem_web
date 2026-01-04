package com.skyazilim.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUserLogin {
	@Email(message = "Mail adresi geçersiz")
	private String mail;
	private String password;
}
