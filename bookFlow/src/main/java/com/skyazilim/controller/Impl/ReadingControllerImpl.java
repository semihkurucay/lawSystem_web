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

import com.skyazilim.controller.IReadingController;
import com.skyazilim.dto.DtoReading;
import com.skyazilim.dto.DtoReadingIU;
import com.skyazilim.dto.DtoReadingPageUpdate;
import com.skyazilim.services.IReadingServices;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/rest/api/reading")
public class ReadingControllerImpl implements IReadingController {
	@Autowired
	private IReadingServices readingServices;
	
	@GetMapping(path = "/list/{id}")
	@Override
	public List<DtoReading> getAllReading(@PathVariable(name = "id") Long userId) {
		// TODO Auto-generated method stub
		return readingServices.getAllReading(userId);
	}

	@GetMapping(path = "/list/get/{id}")
	@Override
	public DtoReading getReadingById(@PathVariable(name = "id") Long readingId) {
		// TODO Auto-generated method stub
		return readingServices.getReadingById(readingId);
	}

	@PostMapping(path = "/save")
	@Override
	public DtoReading saveReading(@RequestBody @Validated DtoReadingIU dtoReadingIU) {
		// TODO Auto-generated method stub
		return readingServices.saveReading(dtoReadingIU);
	}

	@PutMapping(path = "/update/{id}")
	@Override
	public DtoReading updateReading(@PathVariable(name = "id") Long readingId, @RequestBody @Validated DtoReadingPageUpdate dtoReadingPageUpdate) {
		// TODO Auto-generated method stub
		return readingServices.updateReading(readingId, dtoReadingPageUpdate);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteReading(Long readingId) {
		// TODO Auto-generated method stub
		readingServices.deleteReading(readingId);
	}

}
