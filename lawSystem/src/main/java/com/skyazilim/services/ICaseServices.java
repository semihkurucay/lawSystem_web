package com.skyazilim.services;

import java.util.List;

import com.skyazilim.dto.DtoCase;
import com.skyazilim.dto.DtoCaseIU;
import com.skyazilim.dto.DtoDashboard;

public interface ICaseServices {
	public List<DtoCase> getAllCase(String text);
	public List<DtoCase> getAllActiveCase(String text);
	public DtoCase getCaseById(String caseId);
	public DtoDashboard getDtoDashboard();
	public DtoCase saveCase(DtoCaseIU dtoCaseIU);
	public DtoCase updateCase(DtoCaseIU dtoCaseIU);
	public void deleteCase(String caseId);
}
