package com.skyazilim.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyazilim.dto.DtoBook;
import com.skyazilim.dto.DtoBookIU;
import com.skyazilim.entity.Book;
import com.skyazilim.entity.Writer;
import com.skyazilim.exception.BaseException;
import com.skyazilim.exception.ErrorMessage;
import com.skyazilim.exception.MessageType;
import com.skyazilim.repository.IBookRepository;
import com.skyazilim.repository.IReadingRepository;
import com.skyazilim.repository.IWriterRepository;
import com.skyazilim.services.IBookServices;

@Service
public class BookServicesImpl implements IBookServices{
	@Autowired
	private IBookRepository bookRepository;

	@Autowired
	private IWriterRepository writerRepository;
	
	@Autowired
	private IReadingRepository readingRepository;
	
	@Override
	public List<DtoBook> getAllBook() {
		// TODO Auto-generated method stub
		List<Book> bookList = bookRepository.findAll();
		List<DtoBook> dtoBookList = new ArrayList<>();
		
		for(Book book : bookList) {
			DtoBook dtoBook = new DtoBook();
			BeanUtils.copyProperties(book, dtoBook);
			
			dtoBook.setWriterName(book.getWriter().getName());
			
			dtoBookList.add(dtoBook);
		}
		
		return dtoBookList;
	}
	
	@Override
	public DtoBook getBookById(Long bookId) {
		// TODO Auto-generated method stub
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_BOOK, bookId.toString()));
		}
		
		Book book = optionalBook.get();
		DtoBook dtoBook = new DtoBook();
		BeanUtils.copyProperties(book, dtoBook);
		
		dtoBook.setWriterName(book.getWriter().getName());
		return dtoBook;
	}

	@Override
	public DtoBook saveBook(DtoBookIU dtoBookIU) {
		// TODO Auto-generated method stub
		Book book = new Book();
		BeanUtils.copyProperties(dtoBookIU, book);
		
		Optional<Writer> optinalWriter = writerRepository.findById(dtoBookIU.getWriterId());
		if(optinalWriter.isEmpty()) {
			return null;
		}
		
		Writer writer = optinalWriter.get();
		book.setWriter(writer);
		return getBookById(bookRepository.save(book).getId());
	}

	@Override
	public DtoBook updateBook(Long bookId, DtoBookIU dtoBookIU) {
		// TODO Auto-generated method stub
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_BOOK, bookId.toString()));
		}
		
		Book book = optionalBook.get();
		book.setPageCount(dtoBookIU.getPageCount());
		book.setTitle(dtoBookIU.getTitle());
		book.setYear(dtoBookIU.getYear());
		
		Optional<Writer> optinalWriter = writerRepository.findById(dtoBookIU.getWriterId());
		if(optinalWriter.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_WRITER, null));
		}
		
		Writer writer = optinalWriter.get();
		book.setWriter(writer);
		
		return getBookById(bookRepository.save(book).getId());
	}

	@Override
	public void deleteBook(Long bookId) {
		// TODO Auto-generated method stub
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isPresent()) {
			Book book = optionalBook.get();
			
			readingRepository.deleteByBookId(book.getId());
			bookRepository.delete(book);
		}else {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_BOOK, bookId.toString()));
		}
	}
}
