package com.skyazilim.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyazilim.dto.DtoCaseHistory;
import com.skyazilim.dto.DtoCaseHistoryIU;
import com.skyazilim.entity.Case;
import com.skyazilim.entity.CaseHistory;
import com.skyazilim.exception.BaseException;
import com.skyazilim.exception.ErrorMessage;
import com.skyazilim.exception.MessageType;
import com.skyazilim.repository.ICaseHistoryRepository;
import com.skyazilim.repository.ICaseRepository;
import com.skyazilim.services.ICaseHistoryServices;

@Service
public class CaseHistoryServicesImpl implements ICaseHistoryServices {
	@Autowired
	private ICaseHistoryRepository caseHistoryRepository;

	@Autowired
	private ICaseRepository caseRepository;

	@Override
	public List<DtoCaseHistory> getAllCaseHistory(String caseId) {
		// TODO Auto-generated method stub
		List<CaseHistory> caseHistoryList = caseHistoryRepository.getAllCaseHistory(caseId);
		List<DtoCaseHistory> dtoCaseHistoryList = new ArrayList<>();

		for (CaseHistory caseHistory : caseHistoryList) {
			DtoCaseHistory dtoCaseHistory = new DtoCaseHistory();
			BeanUtils.copyProperties(caseHistory, dtoCaseHistory);
			dtoCaseHistoryList.add(dtoCaseHistory);
		}

		return dtoCaseHistoryList;
	}

	@Override
	public DtoCaseHistory getCaseHistoryById(Long caseHistoryId) {
		// TODO Auto-generated method stub
		Optional<CaseHistory> optionalCaseHistory = caseHistoryRepository.findById(caseHistoryId);
		if (optionalCaseHistory.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CASE_HISTORY_NOT_FOUND, null));
		}

		CaseHistory caseHistory = optionalCaseHistory.get();
		DtoCaseHistory dtoCaseHistory = new DtoCaseHistory();

		BeanUtils.copyProperties(caseHistory, dtoCaseHistory);
		return dtoCaseHistory;
	}

	@Override
	public DtoCaseHistory saveCaseHistory(DtoCaseHistoryIU dtoCaseHistoryIU) {
		// TODO Auto-generated method stub
		CaseHistory caseHistory = new CaseHistory();
		BeanUtils.copyProperties(dtoCaseHistoryIU, caseHistory);

		Optional<Case> optionalCase = caseRepository.findById(dtoCaseHistoryIU.getCaseId());
		if (optionalCase.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CASE_NOT_FOUND, dtoCaseHistoryIU.getCaseId()));
		}

		Case cases = optionalCase.get();
		caseHistory.setCases(cases);
		return getCaseHistoryById(caseHistoryRepository.save(caseHistory).getId());
	}

	@Override
	public DtoCaseHistory updateCaseHistory(Long caseHistoryId, DtoCaseHistoryIU dtoCaseHistoryIU) {
		// TODO Auto-generated method stub
		Optional<CaseHistory> optionalCaseHistory = caseHistoryRepository.findById(caseHistoryId);
		if (optionalCaseHistory.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CASE_HISTORY_NOT_FOUND, null));
		}

		CaseHistory caseHistory = optionalCaseHistory.get();
		caseHistory.setComment(dtoCaseHistoryIU.getComment());
		caseHistory.setDate(dtoCaseHistoryIU.getDate());

		Optional<Case> optionalCase = caseRepository.findById(dtoCaseHistoryIU.getCaseId());
		if (optionalCase.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CASE_NOT_FOUND, dtoCaseHistoryIU.getCaseId()));
		}

		Case cases = optionalCase.get();
		caseHistory.setCases(cases);
		return getCaseHistoryById(caseHistoryRepository.save(caseHistory).getId());
	}

	@Override
	public void deleteCaseHistory(Long caseHistoryId) {
		// TODO Auto-generated method stub
		Optional<CaseHistory> optionalCaseHistory = caseHistoryRepository.findById(caseHistoryId);
		if (optionalCaseHistory.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CASE_HISTORY_NOT_FOUND, null));
		}
		
		CaseHistory caseHistory = optionalCaseHistory.get();
		caseHistoryRepository.delete(caseHistory);
	}

}
