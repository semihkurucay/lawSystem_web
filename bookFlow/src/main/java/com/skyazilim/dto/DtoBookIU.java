package com.skyazilim.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoBookIU {
	@NotEmpty(message = "Kitap adı boş geçilemez!")
	private String title;
	
	@NotNull(message = "Sayfa sayısı boş geçilemez!")
	@Positive(message = "Sayfa sayısı eksi olamaz!")
	@Min(value = 1, message = "Sayfa sayısı 0'dan büyük olmalı.")
	private Integer pageCount;
	
	@NotNull(message = "Yıl boş geçilemez!")
	private Integer year;
	
	@NotNull(message = "Yazar eklenmedi!")
	private Long writerId;
}
