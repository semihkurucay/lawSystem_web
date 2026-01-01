package com.skyazilim.controller;

import java.util.List;

import com.skyazilim.dto.DtoReading;
import com.skyazilim.dto.DtoReadingIU;
import com.skyazilim.dto.DtoReadingPageUpdate;

public interface IReadingController {
	public List<DtoReading> getAllReading(Long userId);
	public DtoReading getReadingById(Long readingId);
	public DtoReading saveReading(DtoReadingIU dtoReadingIU);
	public DtoReading updateReading(Long readingId, DtoReadingPageUpdate dtoReadingPageUpdate);
	public void deleteReading(Long readingId);
}
