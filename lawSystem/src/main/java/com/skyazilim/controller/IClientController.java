package com.skyazilim.controller;

import java.util.List;

import com.skyazilim.dto.DtoClient;
import com.skyazilim.dto.DtoClientIU;

public interface IClientController {
	public List<DtoClient> getAllClient(String text);
	public DtoClient getClientById(String clientId);
	public DtoClient saveClient(DtoClientIU dtoClientIU);
	public DtoClient updateClient(DtoClientIU dtoClientIU);
	public void deleteClient(String clientId);
}
