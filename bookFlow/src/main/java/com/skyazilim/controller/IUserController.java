package com.skyazilim.controller;

import com.skyazilim.dto.DtoUser;
import com.skyazilim.dto.DtoUserIU;
import com.skyazilim.dto.DtoUserLogin;

public interface IUserController {
	public DtoUser getUserById(Long userId);
	public DtoUser userLogin(DtoUserLogin userLogin);
	public DtoUser createUser(DtoUserIU dtoUserIU);
	public DtoUser updateUser(Long userId, DtoUserIU dtoUserIU);
	public void deleteUser(Long userId);
}
