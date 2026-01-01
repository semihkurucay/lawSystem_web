package com.skyazilim.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoReadingIU {
	@NotNull(message = "Kitap eklenmedi!")
	private Long bookId;
	
	@NotNull(message = "Kullanıcı eklenmedi!")
	private Long userId;
}
