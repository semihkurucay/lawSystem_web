package com.skyazilim.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyazilim.dto.DtoClient;
import com.skyazilim.dto.DtoClientIU;
import com.skyazilim.entity.Client;
import com.skyazilim.exception.BaseException;
import com.skyazilim.exception.ErrorMessage;
import com.skyazilim.exception.MessageType;
import com.skyazilim.repository.IClientRepository;
import com.skyazilim.services.IClientServices;

@Service
public class ClientServicesImpl implements IClientServices {
	@Autowired
	private IClientRepository clientRepository;

	@Override
	public List<DtoClient> getAllClient(String text) {
		// TODO Auto-generated method stub
		List<Client> clientList = clientRepository.getAllClient(text);
		List<DtoClient> dtoClientList = new ArrayList<>();

		for (Client client : clientList) {
			DtoClient dtoClient = new DtoClient();
			BeanUtils.copyProperties(client, dtoClient);
			dtoClientList.add(dtoClient);
		}

		return dtoClientList;
	}

	@Override
	public DtoClient getClientById(String clientId) {
		// TODO Auto-generated method stub
		Optional<Client> optionalClient = clientRepository.findById(clientId);
		if(optionalClient.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CLIENT_NOT_FOUND, clientId));
		}
		
		Client client = optionalClient.get();
		DtoClient dtoClient = new DtoClient();
		
		BeanUtils.copyProperties(client, dtoClient);
		return dtoClient;
	}

	@Override
	public DtoClient saveClient(DtoClientIU dtoClientIU) {
		// TODO Auto-generated method stub
		Optional<Client> optionalClient = clientRepository.findById(dtoClientIU.getId());
		if(optionalClient.isPresent()) {
			throw new BaseException(new ErrorMessage(MessageType.CLIENT_AVAIBLE, dtoClientIU.getId()));
		}
		
		Client client = new Client();
		if(dtoClientIU.getId().length() == 10) {
			client.setType(false);
		}
		
		BeanUtils.copyProperties(dtoClientIU, client);
		return getClientById(clientRepository.save(client).getId());
	}

	@Override
	public DtoClient updateClient( DtoClientIU dtoClientIU) {
		// TODO Auto-generated method stub
		Optional<Client> optionalClient = clientRepository.findById(dtoClientIU.getId());
		if(optionalClient.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CLIENT_NOT_FOUND, dtoClientIU.getId()));
		}
		
		Client client = optionalClient.get();
		client.setName(dtoClientIU.getName());
		client.setPhone(dtoClientIU.getPhone());
		client.setAddress(dtoClientIU.getAddress());
		
		return getClientById(clientRepository.save(client).getId());
	}

	@Override
	public void deleteClient(String clientId) {
		// TODO Auto-generated method stub
		Optional<Client> optionalClient = clientRepository.findById(clientId);
		if(optionalClient.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.CLIENT_NOT_FOUND, clientId));
		}
		
		Client client = optionalClient.get();
		clientRepository.delete(client);
	}

}
