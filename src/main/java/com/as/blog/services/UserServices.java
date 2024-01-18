package com.as.blog.services;

import java.util.List;

import com.as.blog.paylods.UserDto;

public interface UserServices {
	
	UserDto registerNewUser(UserDto userDto);

	UserDto createUser(UserDto userdto);

	UserDto updateUser(UserDto userdto, Integer userId);

	UserDto gerUserById(Integer userId);

	List<UserDto> getAllUser();

	void deleteUser(Integer userId);

}
