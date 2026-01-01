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
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_USER, null));
		}
		
		User user = optionalUser.get();
		DtoUser dtoUser = new DtoUser();
		
		BeanUtils.copyProperties(user, dtoUser);
		return dtoUser;
	}

	@Override
	public DtoUser userLogin(DtoUserLogin userLogin) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findByMail(userLogin.getMail());
		if(optionalUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.INCORRECT_USER, null));
		}
		
		User user = optionalUser.get();
		if(!user.getPassword().equals(userLogin.getPassword())) {
			throw new BaseException(new ErrorMessage(MessageType.INCORRECT_USER, null));
		}
		
		DtoUser dtoUser = new DtoUser();
		
		BeanUtils.copyProperties(user, dtoUser);
		return dtoUser;
	}

	@Override
	public DtoUser createUser(DtoUserIU dtoUserIU) {
		// TODO Auto-generated method stub
		Optional<User> optional = userRepository.findByMail(dtoUserIU.getMail());
		if(optional.isPresent()) {
			throw new BaseException(new ErrorMessage(MessageType.REPATITIVE_USER_MAIL, null));
		}
		
		User user = new User();
		BeanUtils.copyProperties(dtoUserIU, user);
		
		return getUserById(userRepository.save(user).getId());
	}

	@Override
	public DtoUser updateUser(Long userId, DtoUserIU dtoUserIU) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_USER, null));
		}
		
		User user = optionalUser.get();
		user.setPassword(dtoUserIU.getPassword());
		
		return getUserById(userRepository.save(user).getId());
	}

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			User user = optionalUser.get();
			userRepository.delete(user);
		}else {
			throw new BaseException(new ErrorMessage(MessageType.NOT_FOUND_USER, null));
		}
	}
}
