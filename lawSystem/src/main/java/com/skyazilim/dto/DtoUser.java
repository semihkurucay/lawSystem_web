package com.skyazilim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoUser {
	private Long id;
	private String name;
	private String surname;
	private String mail;
}
