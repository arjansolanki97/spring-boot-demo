package com.anjaneya.spring.boot.demo.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

	private Long id;

	@NotEmpty(message = "name must not be null or empty")
	private String name;

	@NotNull(message = "category must not be null")
	private CategoryDto category;

	private UserDto user;
}
