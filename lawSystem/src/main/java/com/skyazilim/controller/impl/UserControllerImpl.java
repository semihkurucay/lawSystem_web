package com.skyazilim.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skyazilim.dto.DtoUser;
import com.skyazilim.dto.DtoUserIU;
import com.skyazilim.dto.DtoUserLogin;
import com.skyazilim.services.IUserServices;

@RestController
@RequestMapping(path = "/rest/api/user")
public class UserControllerImpl implements IUserServices {
	@Autowired
	private IUserServices userServices;
	
	@GetMapping(path = "/get/{id}")
	@Override
	public DtoUser getUserById(@PathVariable(name = "id") Long userId) {
		// TODO Auto-generated method stub
		return userServices.getUserById(userId);
	}

	@PostMapping(path = "/login")
	@Override
	public DtoUser loginUser(@RequestBody @Validated DtoUserLogin dtoUserLogin) {
		// TODO Auto-generated method stub
		return userServices.loginUser(dtoUserLogin);
	}

	@PostMapping(path = "/save")
	@Override
	public DtoUser saveUser(@RequestBody @Validated DtoUserIU dtoUserIU) {
		// TODO Auto-generated method stub
		return userServices.saveUser(dtoUserIU);
	}

	@PutMapping(path = "/update/{id}")
	@Override
	public DtoUser updateUser(@PathVariable(name = "id") Long userId, @RequestBody @Validated DtoUserIU dtoUserIU) {
		// TODO Auto-generated method stub
		return userServices.updateUser(userId, dtoUserIU);
	}

	@DeleteMapping(path = "/delete/{id}")
	@Override
	public void deleteUser(@PathVariable(name = "id") Long userId) {
		// TODO Auto-generated method stub
		userServices.deleteUser(userId);
	}

}
