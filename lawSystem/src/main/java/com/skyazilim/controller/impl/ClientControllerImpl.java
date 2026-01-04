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

import com.skyazilim.controller.IClientController;
import com.skyazilim.dto.DtoClient;
import com.skyazilim.dto.DtoClientIU;
import com.skyazilim.services.IClientServices;

@RestController
@RequestMapping(path = "/rest/api/client")
public class ClientControllerImpl implements IClientController {
	@Autowired
	private IClientServices clientServices;
	
	@GetMapping(path = "/list")
	@Override
	public List<DtoClient> getAllClient(@RequestParam(name = "searchValue", required = false) String text) {
		// TODO Auto-generated method stub
		return clientServices.getAllClient(text);
	}

	@GetMapping(path = "/list/{id}")
	@Override
	public DtoClient getClientById(@PathVariable(name = "id") String clientId) {
		// TODO Auto-generated method stub
		return clientServices.getClientById(clientId);
	}

	@PostMapping(path = "/save")
	@Override
	public DtoClient saveClient(@RequestBody @Validated DtoClientIU dtoClientIU) {
		// TODO Auto-generated method stub
		return clientServices.saveClient(dtoClientIU);
	}

	@PutMapping(path = "/update")
	@Override
	public DtoClient updateClient(@RequestBody @Validated DtoClientIU dtoClientIU) {
		// TODO Auto-generated method stub
		return clientServices.updateClient(dtoClientIU);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteClient(@PathVariable(name = "id") String clientId) {
		// TODO Auto-generated method stub
		clientServices.deleteClient(clientId);
	}

}
