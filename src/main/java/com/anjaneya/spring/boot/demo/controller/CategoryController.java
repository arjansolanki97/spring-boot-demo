package com.anjaneya.spring.boot.demo.controller;

import com.anjaneya.spring.boot.demo.constants.ApiPathConstants;
import com.anjaneya.spring.boot.demo.dtos.CategoryDto;
import com.anjaneya.spring.boot.demo.entites.response.ApiResponseContainer;
import com.anjaneya.spring.boot.demo.entites.response.ResponseContainerEntity;
import com.anjaneya.spring.boot.demo.services.CategoryService;
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
@RequestMapping(ApiPathConstants.CATEGORY_API)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

	CategoryService categoryService;

	@PostMapping
	public ResponseContainerEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		categoryDto = categoryService.createCategory(categoryDto);
		return ApiResponseContainer.getResponse("Category added successfully", categoryDto, HttpStatus.OK);
	}

	@PutMapping
	public ResponseContainerEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto) {
		categoryDto = categoryService.updateCategory(categoryDto);
		return ApiResponseContainer.getResponse("Category updated successfully", categoryDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseContainerEntity<List<CategoryDto>> getAllCategories() {
		List<CategoryDto> categories = categoryService.getAllCategories();
		return ApiResponseContainer.getResponse("Category list retrieved successfully", categories, HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseContainerEntity<CategoryDto> getCategory(@PathVariable Long categoryId) {
		CategoryDto categoryDto = categoryService.getCategory(categoryId);
		return ApiResponseContainer.getResponse("Category retrieved successfully", categoryDto, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseContainerEntity<Void> deleteById(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);
		return ApiResponseContainer.getResponse("Category deleted successfully", HttpStatus.OK);
	}
}
