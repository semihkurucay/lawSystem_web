package com.skyazilim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoClient {
	private String id;
	private String name;
	private Boolean type;
	private String phone;
	private String address;
}
