package com.skyazilim.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCaseIU {
	@NotEmpty(message = "Dava dosya no boş geçilmez")
	@Pattern(regexp = "^\\d{4}\\/\\d{1,4}$", message = "Dava dosya no hatalı, örnek dava dosya noları : 2026/1 - 2026/1001 - 2026/894 - 2026/22")
	private String id;
	
	@NotEmpty(message = "Dava türü boş geçilemez")
	private String type;
	
	@NotNull(message = "Dava tarihi boş geçilemez")
	private LocalDate date;
	private Boolean status;
	
	@NotEmpty(message = "Mahkeme alanı boş geçilemez")
	private String court;
	private String description;
	private String clientId;
}
