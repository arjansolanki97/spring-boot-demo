package com.anjaneya.spring.boot.demo.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

	private Long id;
	private String name;
	private String email;
	private String token;
}
