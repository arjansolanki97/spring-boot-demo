package com.anjaneya.spring.boot.demo.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

	private Long id;

	@NotEmpty(message = "name must not be empty")
	private String name;

	private CategoryDto parentCategory;
}
