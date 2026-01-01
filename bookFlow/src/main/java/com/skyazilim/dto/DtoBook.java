package com.skyazilim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoBook {
	private Long id;
	private String title;
	private Integer pageCount;
	private Integer year;
	private String writerName;
}
