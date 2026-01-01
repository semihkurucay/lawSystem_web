package com.skyazilim.controller;

import java.util.List;

import com.skyazilim.dto.DtoBook;
import com.skyazilim.dto.DtoBookIU;

public interface IBookController {
	public List<DtoBook> getAllBook();
	public DtoBook getBookById(Long bookId);
	public DtoBook saveBook(DtoBookIU dtoBookIU);
	public DtoBook updateBook(Long bookId, DtoBookIU dtoBookIU);
	public void deleteBook(Long bookId);
}
