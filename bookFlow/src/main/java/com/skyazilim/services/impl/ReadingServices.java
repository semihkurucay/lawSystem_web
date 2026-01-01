package com.skyazilim.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyazilim.dto.DtoReading;
import com.skyazilim.dto.DtoReadingIU;
import com.skyazilim.dto.DtoReadingPageUpdate;
import com.skyazilim.entity.Book;
import com.skyazilim.entity.Reading;
import com.skyazilim.entity.User;
import com.skyazilim.exception.BaseException;
import com.skyazilim.exception.ErrorMessage;
import com.skyazilim.exception.MessageType;
import com.skyazilim.repository.IBookRepository;
import com.skyazilim.repository.IReadingRepository;
import com.skyazilim.repository.IUserRepository;
import com.skyazilim.services.IReadingServices;

@Service
public class ReadingServices implements IReadingServices {
	@Autowired
	private IReadingRepository readingRepository;
	
	@Autowired
	private IBookRepository bookRepository;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public List<DtoReading> getAllReading(Long userId) {
		// TODO Auto-generated method stub
		List<Reading> readingList = readingRepository.getAllBookByUserId(userId);
		List<DtoReading> dtoReadingList = new ArrayList<>();
		
		for(Reading reading : readingList) {
			DtoReading dtoReading = new DtoReading();
			BeanUtils.copyProperties(reading, dtoReading);
			
			dtoReading.setBookTitle(reading.getBook().getTitle());
			dtoReading.setWriterName(reading.getBook().getWriter().getName());
			dtoReading.setTotalPage(reading.getBook().getPageCount());
			
			dtoReadingList.add(dtoReading);
		}
		
		return dtoReadingList;
	}

	@Override
	public DtoReading getReadingById(Long readingId) {
		// TODO Auto-generated method stub
		Optional<Reading> optionalReading = readingRepository.findById(readingId);
		if(optionalReading.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_READING, readingId.toString()));
		}
		
		Reading reading = optionalReading.get();
		DtoReading dtoReading = new DtoReading();
		BeanUtils.copyProperties(reading, dtoReading);
		
		dtoReading.setBookTitle(reading.getBook().getTitle());
		dtoReading.setWriterName(reading.getBook().getWriter().getName());
		dtoReading.setTotalPage(reading.getBook().getPageCount());
		
		return dtoReading;
	}

	@Override
	public DtoReading saveReading(DtoReadingIU dtoReadingIU) {
		// TODO Auto-generated method stub
		Reading reading = new Reading();
		BeanUtils.copyProperties(dtoReadingIU, reading);
		
		Optional<Book> optionalBook = bookRepository.findById(dtoReadingIU.getBookId());
		if(optionalBook.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_BOOK, null));
		}
		Book book = optionalBook.get();
		reading.setBook(book);
		
		Optional<User> optionalUser = userRepository.findById(dtoReadingIU.getUserId());
		if(optionalUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_USER, null));
		}
		User user = optionalUser.get();
		reading.setUser(user);
		
		Optional<Reading> optionalIsReadBook = readingRepository.isReadBook(user.getId(), book.getId());
		if(optionalIsReadBook.isPresent()) {
			throw new BaseException(new ErrorMessage(MessageType.THIS_READING_BOOK, null));
		}
		
		
		return getReadingById(readingRepository.save(reading).getId());
	}

	@Override
	public DtoReading updateReading(Long readingId, DtoReadingPageUpdate dtoReadingPageUpdate) {
		// TODO Auto-generated method stub
		Optional<Reading> optionalReading = readingRepository.findById(readingId);
		if(optionalReading.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_READING, null));
		}
		
		Reading reading = optionalReading.get();
		if(reading.getBook().getPageCount() < dtoReadingPageUpdate.getCurrentPage()) {
			throw new BaseException(new ErrorMessage(MessageType.ILLEGAL_PAGE, null));
		}
		
		reading.setCurrentPage(dtoReadingPageUpdate.getCurrentPage());
		
		int percentage = (int) (((double) reading.getCurrentPage() / reading.getBook().getPageCount()) * 100);
		reading.setPercentage(percentage);
		
		if(percentage == 100) {
			reading.setStatus("KİTAP OKUNDU");
		}else {
			reading.setStatus("KİTAP OKUNUYOR");
		}
		
		return getReadingById(readingRepository.save(reading).getId());
	}

	@Override
	public void deleteReading(Long readingId) {
		// TODO Auto-generated method stub
		Optional<Reading> optionalReading = readingRepository.findById(readingId);
		if(optionalReading.isPresent()) {
			Reading reading = optionalReading.get();
			readingRepository.delete(reading);
		}
	}

}
