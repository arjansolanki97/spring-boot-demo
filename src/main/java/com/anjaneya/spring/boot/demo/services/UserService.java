package com.anjaneya.spring.boot.demo.services;

import com.anjaneya.spring.boot.demo.dtos.UserDto;
import com.anjaneya.spring.boot.demo.dtos.request.UserRequestDto;
import com.anjaneya.spring.boot.demo.dtos.request.UserUpdateRequestDto;
import com.anjaneya.spring.boot.demo.dtos.response.UserResponseDto;
import com.anjaneya.spring.boot.demo.entites.UserEntity;

import java.util.List;

public interface UserService {
	UserDto addUser(UserRequestDto requestDto);

	List<UserDto> findAll();

	UserDto findById(Long id);

	void deleteById(Long id);

	UserDto updateUser(UserUpdateRequestDto userUpdateRequestDto);

	UserResponseDto signIn(UserRequestDto userRequestDto);

	UserDto getLoggedInUserDto();

	UserEntity getLoggedInUserEntity();

}
