package com.anjaneya.spring.boot.demo.services;

import com.anjaneya.spring.boot.demo.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryEntity);

	CategoryDto updateCategory(CategoryDto categoryEntity);

	CategoryDto getCategory(long id);

	List<CategoryDto> getAllCategories();

	void deleteCategory(long id);
}
