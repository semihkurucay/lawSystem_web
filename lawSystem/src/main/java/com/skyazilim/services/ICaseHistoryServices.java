package com.skyazilim.services;

import java.util.List;

import com.skyazilim.dto.DtoCaseHistory;
import com.skyazilim.dto.DtoCaseHistoryIU;

public interface ICaseHistoryServices {
	public List<DtoCaseHistory> getAllCaseHistory(String caseId);
	public DtoCaseHistory getCaseHistoryById(Long caseHistoryId);
	public DtoCaseHistory saveCaseHistory(DtoCaseHistoryIU dtoCaseHistoryIU);
	public DtoCaseHistory updateCaseHistory(Long caseHistoryId, DtoCaseHistoryIU dtoCaseHistoryIU);
	public void deleteCaseHistory(Long caseHistoryId);
}
