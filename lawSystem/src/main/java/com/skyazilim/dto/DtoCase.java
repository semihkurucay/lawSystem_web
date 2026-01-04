package com.skyazilim.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCase {
	private String id;
	private String type;
	private LocalDate date;
	private Boolean status;
	private String court;
	private String description;
	private String clientName;
	private String clientId;
}
