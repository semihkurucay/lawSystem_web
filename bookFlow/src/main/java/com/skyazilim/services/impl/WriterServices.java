package com.skyazilim.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyazilim.dto.DtoWriter;
import com.skyazilim.dto.DtoWriterIU;
import com.skyazilim.entity.Book;
import com.skyazilim.entity.Writer;
import com.skyazilim.exception.BaseException;
import com.skyazilim.exception.ErrorMessage;
import com.skyazilim.exception.MessageType;
import com.skyazilim.repository.IBookRepository;
import com.skyazilim.repository.IReadingRepository;
import com.skyazilim.repository.IWriterRepository;
import com.skyazilim.services.IWriterServices;

@Service
public class WriterServices implements IWriterServices {
	@Autowired
	private IWriterRepository writerRepository;
	
	@Autowired
	private IBookRepository bookRepository;
	
	@Autowired
	private IReadingRepository readingRepository;
	
	@Override
	public List<DtoWriter> getAllWriters() {
		// TODO Auto-generated method stub
		List<Writer> writerList = writerRepository.findAll();
		List<DtoWriter> dtoWriterList = new ArrayList<>();

		for(Writer writer : writerList) {
			DtoWriter dtoWriter = new DtoWriter();
			BeanUtils.copyProperties(writer, dtoWriter);
			dtoWriterList.add(dtoWriter);
		}
		
		return dtoWriterList;
	}
	
	@Override
	public DtoWriter getWriterById(Long writerId) {
		// TODO Auto-generated method stub
		Optional<Writer> optionalWriter = writerRepository.findById(writerId);
		if(optionalWriter.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_WRITER, null));
		}
		
		Writer writer = optionalWriter.get();
		DtoWriter dtoWriter = new DtoWriter();
		
		BeanUtils.copyProperties(writer, dtoWriter);
		return dtoWriter;
	}

	@Override
	public DtoWriter saveWriter(DtoWriterIU dtoWriterIU) {
		// TODO Auto-generated method stub
		Writer writer = new Writer();
		BeanUtils.copyProperties(dtoWriterIU, writer);
		return getWriterById(writerRepository.save(writer).getId());
	}

	@Override
	public DtoWriter updateWriter(Long writerId, DtoWriterIU dtoWriterIU) {
		// TODO Auto-generated method stub
		Optional<Writer> optionalWriter = writerRepository.findById(writerId);
		if(optionalWriter.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_WRITER, null));
		}
		
		Writer writer = optionalWriter.get();
		writer.setName(dtoWriterIU.getName());
		writer.setBirthDate(dtoWriterIU.getBirthDate());
		writer.setDeathDate(dtoWriterIU.getDeathDate());
		
		return getWriterById(writerRepository.save(writer).getId());
	}

	@Override
	public void deleteWriter(Long writerId) {
		// TODO Auto-generated method stub
		Optional<Writer> optionalWriter = writerRepository.findById(writerId);
		if(optionalWriter.isPresent()) {
			Writer writer = optionalWriter.get();
			
			List<Book> bookList = bookRepository.findByWriterId(writerId);
			for(Book book : bookList) {
				readingRepository.deleteByBookId(book.getId());
				bookRepository.delete(book);
			}
			
			writerRepository.delete(writer);
		}else {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_WRITER, null));
		}
	}
}
