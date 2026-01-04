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

import com.skyazilim.controller.ICaseHistoryController;
import com.skyazilim.dto.DtoCaseHistory;
import com.skyazilim.dto.DtoCaseHistoryIU;
import com.skyazilim.services.ICaseHistoryServices;

@RestController
@RequestMapping(path = "/rest/api/casehistory")
public class CaseHistoryControllerImpl implements ICaseHistoryController {
	@Autowired
	private ICaseHistoryServices caseHistoryServices;

	@GetMapping(path = "/list")
	@Override
	public List<DtoCaseHistory> getAllCaseHistory(@RequestParam("caseId") String caseId) {
		// TODO Auto-generated method stub
		return caseHistoryServices.getAllCaseHistory(caseId);
	}

	@GetMapping(path = "/get/{id}")
	@Override
	public DtoCaseHistory getCaseHistoryById(@PathVariable(name = "id") Long caseHistoryId) {
		// TODO Auto-generated method stub
		return caseHistoryServices.getCaseHistoryById(caseHistoryId);
	}

	@PostMapping(path = "/save")
	@Override
	public DtoCaseHistory saveCaseHistory(@RequestBody @Validated DtoCaseHistoryIU dtoCaseHistoryIU) {
		// TODO Auto-generated method stub
		return caseHistoryServices.saveCaseHistory(dtoCaseHistoryIU);
	}

	@PutMapping(path = "/update/{id}")
	@Override
	public DtoCaseHistory updateCaseHistory(@PathVariable(name = "id") Long caseHistoryId, @RequestBody @Validated DtoCaseHistoryIU dtoCaseHistoryIU) {
		// TODO Auto-generated method stub
		return caseHistoryServices.updateCaseHistory(caseHistoryId, dtoCaseHistoryIU);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteCaseHistory(@PathVariable(name = "id") Long caseHistoryId) {
		// TODO Auto-generated method stub
		caseHistoryServices.deleteCaseHistory(caseHistoryId);
	}
}
