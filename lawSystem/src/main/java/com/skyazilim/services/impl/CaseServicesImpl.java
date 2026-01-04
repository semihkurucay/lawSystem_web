package com.skyazilim.services.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyazilim.dto.DtoCase;
import com.skyazilim.dto.DtoCaseIU;
import com.skyazilim.dto.DtoDashboard;
import com.skyazilim.entity.Case;
import com.skyazilim.entity.Client;
import com.skyazilim.exception.BaseException;
import com.skyazilim.exception.ErrorMessage;
import com.skyazilim.exception.MessageType;
import com.skyazilim.repository.ICaseRepository;
import com.skyazilim.repository.IClientRepository;
import com.skyazilim.services.ICaseServices;

@Service
public class CaseServicesImpl implements ICaseServices {
	@Autowired
	private ICaseRepository caseRepository;
	
	@Autowired
	private IClientRepository clientRepository;
	
	@Override
	public List<DtoCase> getAllCase(String text) {
		// TODO Auto-generated method stub
		List<Case> caseList = caseRepository.getAllCase(text);
		List<DtoCase> dtoCaseList = new ArrayList<>();
		
		for(Case cases : caseList) {
			DtoCase dtoCase = new DtoCase();
			BeanUtils.copyProperties(cases, dtoCase);
			
			dtoCase.setClientId(cases.getClient().getId());
			dtoCase.setClientName(cases.getClient().getName());
			
			dtoCaseList.add(dtoCase);
		}
		
		return dtoCaseList;
	}
	
	@Override
	public List<DtoCase> getAllActiveCase(String text) {
		// TODO Auto-generated method stub
		List<Case> caseList = caseRepository.getAllActiveCase(text);
		List<DtoCase> dtoCaseList = new ArrayList<>();
		
		for(Case cases : caseList) {
			DtoCase dtoCase = new DtoCase();
			BeanUtils.copyProperties(cases, dtoCase);
			
			dtoCase.setClientId(cases.getClient().getId());
			dtoCase.setClientName(cases.getClient().getName());
			
			dtoCaseList.add(dtoCase);
		}
		
		return dtoCaseList;
	}

	@Override
	public DtoCase getCaseById(String caseId) {
		// TODO Auto-generated method stub
		Optional<Case> optionalCase = caseRepository.findById(caseId);
		if(optionalCase.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CASE_NOT_FOUND, caseId));
		}
		
		Case cases = optionalCase.get();
		DtoCase dtoCase = new DtoCase();
		
		dtoCase.setClientId(cases.getClient().getId());
		dtoCase.setClientName(cases.getClient().getName());
		
		BeanUtils.copyProperties(cases, dtoCase);
		return dtoCase;
	}

	@Override
	public DtoDashboard getDtoDashboard() {
		// TODO Auto-generated method stub
		DtoDashboard dtoDashboard = new DtoDashboard();
		dtoDashboard.setTotalCase(caseRepository.getTotalCase());
		dtoDashboard.setActiveCase(caseRepository.getActiveCase());
		dtoDashboard.setUpcomingCase(caseRepository.getUpcomingCase(LocalDate.now().plusWeeks(1)));
		return dtoDashboard;
	}

	@Override
	public DtoCase saveCase(DtoCaseIU dtoCaseIU) {
		// TODO Auto-generated method stub
		Optional<Case> optionalCase = caseRepository.findById(dtoCaseIU.getId());
		if(optionalCase.isPresent()) {
			throw new BaseException(new ErrorMessage(MessageType.CASE_AVAIBLE, dtoCaseIU.getId()));
		}
		
		Optional<Client> optionalClient = clientRepository.findById(dtoCaseIU.getClientId());
		if(optionalClient.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CLIENT_NOT_FOUND, dtoCaseIU.getClientId()));
		}
		
		Client client = optionalClient.get();
		
		Case cases = new Case();
		BeanUtils.copyProperties(dtoCaseIU, cases);
		cases.setClient(client);
		
		return getCaseById(caseRepository.save(cases).getId());
	}

	@Override
	public DtoCase updateCase(DtoCaseIU dtoCaseIU) {
		// TODO Auto-generated method stub
		Optional<Case> optionalCase = caseRepository.findById(dtoCaseIU.getId());
		if(optionalCase.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CASE_NOT_FOUND, dtoCaseIU.getId()));
		}
		
		Optional<Client> optionalClient = clientRepository.findById(dtoCaseIU.getClientId());
		if(optionalClient.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CLIENT_NOT_FOUND, dtoCaseIU.getClientId()));
		}
		
		Client client = optionalClient.get();
		Case cases = optionalCase.get();
		cases.setClient(client);
		cases.setCourt(dtoCaseIU.getCourt());
		cases.setDate(dtoCaseIU.getDate());
		cases.setStatus(dtoCaseIU.getStatus());
		cases.setType(dtoCaseIU.getType());
		cases.setDescription(dtoCaseIU.getDescription());
		
		return getCaseById(caseRepository.save(cases).getId());
	}

	@Override
	public void deleteCase(String caseId) {
		// TODO Auto-generated method stub
		Optional<Case> optionalCase = caseRepository.findById(caseId);
		if(optionalCase.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CASE_NOT_FOUND, caseId));
		}
		
		Case cases = optionalCase.get();
		caseRepository.delete(cases);
	}
}
