package com.anjaneya.spring.boot.demo.services.impl;

import com.anjaneya.spring.boot.demo.dtos.UserDto;
import com.anjaneya.spring.boot.demo.dtos.request.UserRequestDto;
import com.anjaneya.spring.boot.demo.dtos.request.UserUpdateRequestDto;
import com.anjaneya.spring.boot.demo.dtos.response.UserResponseDto;
import com.anjaneya.spring.boot.demo.entites.UserEntity;
import com.anjaneya.spring.boot.demo.exceptions.CustomException;
import com.anjaneya.spring.boot.demo.exceptions.ResourceNotFoundException;
import com.anjaneya.spring.boot.demo.jwt.JwtTokenProvider;
import com.anjaneya.spring.boot.demo.mapper.ModelMapperService;
import com.anjaneya.spring.boot.demo.repositories.UserRepository;
import com.anjaneya.spring.boot.demo.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

	UserRepository userRepository;

	ModelMapperService modelMapperService;

	PasswordEncoder passwordEncoder;

	AuthenticationManager authenticationManager;

	JwtTokenProvider jwtTokenProvider;

	@Override
	public UserDto addUser(UserRequestDto userRequestDto) {

		Boolean isUserExist = userRepository.existsByEmail(userRequestDto.getEmail());
		if (Boolean.TRUE.equals(isUserExist)) {
			throw new CustomException("Email is already taken!");
		}

		UserEntity userEntity = modelMapperService.convertToType(userRequestDto, UserEntity.class);
		userEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
		userEntity = userRepository.save(userEntity);
		return modelMapperService.convertToType(userEntity, UserDto.class);
	}

	@Override
	public List<UserDto> findAll() {
		List<UserEntity> userEntities = userRepository.findAll();
		return modelMapperService.convertListEntityToListDto(userEntities, UserDto.class);
	}

	@Override
	public UserDto findById(Long id) {
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		if (userEntity == null) {
			throw new ResourceNotFoundException("User", id);
		}
		return modelMapperService.convertToType(userRepository.findById(id), UserDto.class);
	}

	@Override
	public void deleteById(Long id) {
		UserEntity userEntity = userRepository.findById(id).orElse(null);
		if (userEntity == null) {
			throw new ResourceNotFoundException("User", id);
		}
		userRepository.delete(userEntity);
	}

	@Override
	public UserDto updateUser(UserUpdateRequestDto userUpdateRequestDto) {

		UserEntity userEntity = userRepository.findById(userUpdateRequestDto.getId()).orElse(null);
		if (userEntity == null) {
			throw new ResourceNotFoundException("User not found");
		}

		if (userUpdateRequestDto.getName() != null) {
			userEntity.setName(userUpdateRequestDto.getName());
		}

		if (userUpdateRequestDto.getPassword() != null) {
			userEntity.setPassword(passwordEncoder.encode(userUpdateRequestDto.getPassword()));
		}
		userEntity = userRepository.save(userEntity);
		return modelMapperService.convertToType(userEntity, UserDto.class);
	}

	@Override
	public UserResponseDto signIn(UserRequestDto userRequestDto) {

		Authentication authentication =
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(
								userRequestDto.getEmail(), userRequestDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserEntity userEntity = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(
				() ->
						new UsernameNotFoundException(
								"User not found with email: " + userRequestDto.getEmail()));

		UserResponseDto userResponseDto = modelMapperService.convertToType(userEntity, UserResponseDto.class);

		String token = jwtTokenProvider.generateToken(authentication);
		userResponseDto.setToken(token);

		return userResponseDto;
	}

	@Override
	public UserDto getLoggedInUserDto() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(null);
		return modelMapperService.convertToType(userEntity, UserDto.class);
	}

	@Override
	public UserEntity getLoggedInUserEntity() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		return userRepository.findByEmail(email).orElseThrow(null);
	}
}
