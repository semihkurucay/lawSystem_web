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

import com.skyazilim.controller.IWriterController;
import com.skyazilim.dto.DtoWriter;
import com.skyazilim.dto.DtoWriterIU;
import com.skyazilim.services.IWriterServices;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/rest/api/writer")
public class WriterControllerImpl implements IWriterController {
	@Autowired
	private IWriterServices writerServices;
	
	@GetMapping(path = "/list")
	@Override
	public List<DtoWriter> getAllWriters() {
		// TODO Auto-generated method stub
		return writerServices.getAllWriters();
	}
	
	@GetMapping(path = "/list/{id}")
	@Override
	public DtoWriter getWriterById(@PathVariable(name = "id") Long writerId) {
		// TODO Auto-generated method stub
		return writerServices.getWriterById(writerId);
	}

	@PostMapping(path = "/save")
	@Override
	public DtoWriter saveWriter(@RequestBody @Validated DtoWriterIU dtoWriterIU) {
		// TODO Auto-generated method stub
		return writerServices.saveWriter(dtoWriterIU);
	}

	@PutMapping(path = "/update/{id}")
	@Override
	public DtoWriter updateWriter(@PathVariable(name = "id") Long writerId, @RequestBody @Validated DtoWriterIU dtoWriterIU) {
		// TODO Auto-generated method stub
		return writerServices.updateWriter(writerId, dtoWriterIU);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteWriter(@PathVariable(name = "id") Long writerId) {
		// TODO Auto-generated method stub
		writerServices.deleteWriter(writerId);
	}
}
