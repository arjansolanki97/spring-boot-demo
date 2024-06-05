package com.anjaneya.spring.boot.demo.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {

	private Long id;
	private String name;

	@Email(message = "email format not valid")
	@NotEmpty(message = "email must not be empty")
	private String email;

	@NotEmpty(message = "password must not be empty")
	private String password;
}
