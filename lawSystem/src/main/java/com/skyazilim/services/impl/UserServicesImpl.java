package com.skyazilim.services.impl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skyazilim.dto.DtoUser;
import com.skyazilim.dto.DtoUserIU;
import com.skyazilim.dto.DtoUserLogin;
import com.skyazilim.entity.User;
import com.skyazilim.exception.BaseException;
import com.skyazilim.exception.ErrorMessage;
import com.skyazilim.exception.MessageType;
import com.skyazilim.repository.IUserRepository;
import com.skyazilim.services.IUserServices;

@Service
public class UserServicesImpl implements IUserServices {
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public DtoUser getUserById(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, userId.toString()));
		}
		
		User user = optionalUser.get();
		DtoUser dtoUser = new DtoUser();
		
		BeanUtils.copyProperties(user, dtoUser);
		return dtoUser;
	}

	@Override
	public DtoUser loginUser(DtoUserLogin dtoUserLogin) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.getUserByMail(dtoUserLogin.getMail());
		if(optionalUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.USER_LONGIN_INCORRECT, null));
		}
		
		User user = optionalUser.get();
		if(!user.getPassword().equals(dtoUserLogin.getPassword())){
			throw new BaseException(new ErrorMessage(MessageType.USER_LONGIN_INCORRECT, null));
		}
		
		DtoUser dtoUser = new DtoUser();
		BeanUtils.copyProperties(user, dtoUser);
		return dtoUser;
	}

	@Override
	public DtoUser saveUser(DtoUserIU dtoUserIU) {
		// TODO Auto-generated method stub
		User user = new User();
		BeanUtils.copyProperties(dtoUserIU, user);
		return getUserById(userRepository.save(user).getId());
	}

	@Override
	public DtoUser updateUser(Long userId, DtoUserIU dtoUserIU) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, userId.toString()));
		}
		
		User user = optionalUser.get();
		user.setName(dtoUserIU.getName());
		user.setSurname(dtoUserIU.getSurname());
		user.setMail(dtoUserIU.getMail());
		
		if(dtoUserIU.getPassword() != null && !dtoUserIU.getPassword().isEmpty()) {
			user.setPassword(dtoUserIU.getPassword());
		}
		
		return getUserById(userRepository.save(user).getId());
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.USER_NOT_FOUND, userId.toString()));
		}
		
		User user = optionalUser.get();
		userRepository.delete(user);
	}
}
