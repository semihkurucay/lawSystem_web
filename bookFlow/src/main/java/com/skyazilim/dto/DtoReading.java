package com.skyazilim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoReading {
	private Long id;
	private String bookTitle;
	private String writerName;
	private Integer currentPage;
	private Integer percentage;
	private Integer totalPage;
	private String status;
}
