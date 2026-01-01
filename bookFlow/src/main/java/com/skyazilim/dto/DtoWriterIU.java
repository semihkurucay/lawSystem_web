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
public class DtoWriterIU {
	@NotEmpty(message = "Yazar adı boş geçilemez!")
	@Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ\\s]+$", message = "Yazar adı sadece harf olmalı!")
	private String name;
	
	@NotNull(message = "Yazar doğum tarihi boş geçilemez!")
	private LocalDate birthDate;
	private LocalDate deathDate;
}
