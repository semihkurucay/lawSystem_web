package com.skyazilim.controller;

import java.util.List;

import com.skyazilim.dto.DtoWriter;
import com.skyazilim.dto.DtoWriterIU;

public interface IWriterController {
	public List<DtoWriter> getAllWriters();
	public DtoWriter getWriterById(Long writerId);
	public DtoWriter saveWriter(DtoWriterIU dtoWriterIU);
	public DtoWriter updateWriter(Long writerId, DtoWriterIU dtoWriterIU);
	public void deleteWriter(Long writerId);
}
