package com.skyazilim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoDashboard {
	private Long totalCase;
	private Long activeCase;
	private Long upcomingCase;
}
