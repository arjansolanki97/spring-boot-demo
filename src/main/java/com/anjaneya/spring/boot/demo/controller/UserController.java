package com.anjaneya.spring.boot.demo.controller;

import com.anjaneya.spring.boot.demo.constants.ApiPathConstants;
import com.anjaneya.spring.boot.demo.dtos.UserDto;
import com.anjaneya.spring.boot.demo.dtos.request.UserRequestDto;
import com.anjaneya.spring.boot.demo.dtos.request.UserUpdateRequestDto;
import com.anjaneya.spring.boot.demo.dtos.response.UserResponseDto;
import com.anjaneya.spring.boot.demo.entites.response.ApiResponseContainer;
import com.anjaneya.spring.boot.demo.entites.response.ResponseContainerEntity;
import com.anjaneya.spring.boot.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiPathConstants.USER_API)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

	UserService userService;

	@GetMapping
	public ResponseContainerEntity<List<UserDto>> findAll() {
		List<UserDto> users = userService.findAll();
		return ApiResponseContainer.getResponse("User list retrieved successfully", users, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseContainerEntity<UserDto> findById(@PathVariable Long userId) {
		UserDto userDto = userService.findById(userId);
		return ApiResponseContainer.getResponse("User retrieved successfully", userDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseContainerEntity<UserDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
		UserDto userDto = userService.addUser(userRequestDto);
		return ApiResponseContainer.getResponse("User added successfully", userDto, HttpStatus.OK);
	}

	@PutMapping
	public ResponseContainerEntity<UserDto> updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
		UserDto userDto = userService.updateUser(userUpdateRequestDto);
		return ApiResponseContainer.getResponse("User updated successfully", userDto, HttpStatus.OK);
	}

	@DeleteMapping("/{userId}")
	public ResponseContainerEntity<Void> deleteById(@PathVariable Long userId) {
		userService.deleteById(userId);
		return ApiResponseContainer.getResponse("User deleted successfully", HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseContainerEntity<UserResponseDto> signIn(@Valid @RequestBody UserRequestDto userRequestDto) {
		UserResponseDto userResponseDto = userService.signIn(userRequestDto);
		return ApiResponseContainer.getResponse("User logged in successfully", userResponseDto, HttpStatus.OK);
	}
}
