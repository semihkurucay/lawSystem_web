package com.skyazilim.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skyazilim.controller.ICaseController;
import com.skyazilim.dto.DtoCase;
import com.skyazilim.dto.DtoCaseIU;
import com.skyazilim.dto.DtoDashboard;
import com.skyazilim.services.ICaseServices;

@RestController
@RequestMapping(path = "/rest/api/case")
public class CaseControllerImpl implements ICaseController {
	@Autowired
	private ICaseServices caseServices;
	
	@GetMapping(path = "/list")
	@Override
	public List<DtoCase> getAllCase(@RequestParam(name = "searchValue", required = false) String text) {
		// TODO Auto-generated method stub
		return caseServices.getAllCase(text);
	}

	@GetMapping(path = "/list-active")
	@Override
	public List<DtoCase> getAllActiveCase(@RequestParam(name = "searchValue", required = false) String text) {
		// TODO Auto-generated method stub
		return caseServices.getAllActiveCase(text);
	}
	
	@GetMapping(path = "/list/{id}")
	@Override
	public DtoCase getCaseById(@PathVariable(name = "id") String caseId) {
		// TODO Auto-generated method stub
		return caseServices.getCaseById(caseId);
	}

	@GetMapping(path = "/dashboard")
	@Override
	public DtoDashboard getDtoDashboard() {
		// TODO Auto-generated method stub
		return caseServices.getDtoDashboard();
	}

	@PostMapping(path = "/save")
	@Override
	public DtoCase saveCase(@RequestBody @Validated DtoCaseIU dtoCaseIU) {
		// TODO Auto-generated method stub
		return caseServices.saveCase(dtoCaseIU);
	}

	@PutMapping(path = "/update")
	@Override
	public DtoCase updateCase(@RequestBody @Validated DtoCaseIU dtoCaseIU) {
		// TODO Auto-generated method stub
		return caseServices.updateCase(dtoCaseIU);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteCase(@PathVariable(name = "id") String caseId) {
		// TODO Auto-generated method stub
		caseServices.deleteCase(caseId);
	}

}
