package com.skyazilim.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCaseHistoryIU {
	private LocalDate date;
	
	@NotEmpty(message = "Geçmiş boş değer eklenemez")
	private String comment;
	private String caseId;
}
