package com.skyazilim.controller;

import com.skyazilim.dto.DtoUser;
import com.skyazilim.dto.DtoUserIU;
import com.skyazilim.dto.DtoUserLogin;

public interface IUserController {
	public DtoUser getUserById(Long userId);
	public DtoUser loginUser(DtoUserLogin dtoUserLogin);
	public DtoUser saveUser(DtoUserIU dtoUserIU);
	public DtoUser updateUser(Long userId, DtoUserIU dtoUserIU);
	public void deleteUser(Long userId);
}
