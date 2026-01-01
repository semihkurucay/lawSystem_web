package com.skyazilim.controller.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyazilim.controller.IBookController;
import com.skyazilim.dto.DtoBook;
import com.skyazilim.dto.DtoBookIU;
import com.skyazilim.services.IBookServices;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/rest/api/book")
public class BookControllerImpl implements IBookController {
	@Autowired
	private IBookServices bookServices;
	
	@GetMapping(path = "/list")
	@Override
	public List<DtoBook> getAllBook() {
		// TODO Auto-generated method stub
		return bookServices.getAllBook();
	}

	@GetMapping(path = "/list/{id}")
	@Override
	public DtoBook getBookById(@PathVariable(name = "id") Long bookId) {
		// TODO Auto-generated method stub
		return bookServices.getBookById(bookId);
	}

	@PostMapping(path = "/save")
	@Override
	public DtoBook saveBook(@RequestBody @Validated DtoBookIU dtoBookIU) {
		// TODO Auto-generated method stub
		return bookServices.saveBook(dtoBookIU);
	}

	@PutMapping(path = "/update/{id}")
	@Override
	public DtoBook updateBook(@PathVariable(name = "id") Long bookId, @RequestBody @Validated DtoBookIU dtoBookIU) {
		// TODO Auto-generated method stub
		return bookServices.updateBook(bookId, dtoBookIU);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteBook(@PathVariable(name = "id") Long bookId) {
		// TODO Auto-generated method stub
		bookServices.deleteBook(bookId);
	}

}
