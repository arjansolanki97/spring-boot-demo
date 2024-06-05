package com.anjaneya.spring.boot.demo.services.impl;

import com.anjaneya.spring.boot.demo.dtos.CategoryDto;
import com.anjaneya.spring.boot.demo.entites.CategoryEntity;
import com.anjaneya.spring.boot.demo.exceptions.ResourceNotFoundException;
import com.anjaneya.spring.boot.demo.mapper.ModelMapperService;
import com.anjaneya.spring.boot.demo.repositories.CategoryRepository;
import com.anjaneya.spring.boot.demo.services.CategoryService;
import com.anjaneya.spring.boot.demo.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {

	CategoryRepository categoryRepository;

	ModelMapperService modelMapperService;

	UserService userService;

	private static final String RESOURCE = "Category";

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		CategoryEntity categoryEntity = modelMapperService.convertToType(categoryDto, CategoryEntity.class);
		categoryEntity.setCreatedBy(userService.getLoggedInUserEntity());
		categoryEntity.setUpdatedBy(userService.getLoggedInUserEntity());
		categoryEntity = categoryRepository.save(categoryEntity);
		return modelMapperService.convertToType(categoryEntity, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto) {
		CategoryEntity categoryEntity = categoryRepository.findById(categoryDto.getId()).orElse(null);
		if (categoryEntity == null) {
			throw new ResourceNotFoundException(RESOURCE, categoryDto.getId());
		}
		categoryEntity = modelMapperService.convertToType(categoryDto, CategoryEntity.class);
		categoryEntity.setUpdatedBy(userService.getLoggedInUserEntity());
		categoryEntity = categoryRepository.save(categoryEntity);
		return modelMapperService.convertToType(categoryEntity, CategoryDto.class);
	}

	@Override
	public CategoryDto getCategory(long id) {
		CategoryEntity categoryEntity = categoryRepository.findById(id).orElse(null);
		if (categoryEntity == null) {
			throw new ResourceNotFoundException(RESOURCE, id);
		}
		return modelMapperService.convertToType(categoryEntity, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		return modelMapperService.convertListEntityToListDto(categoryEntities, CategoryDto.class);
	}

	@Override
	public void deleteCategory(long id) {
		CategoryEntity category = categoryRepository.findById(id).orElse(null);
		if (category == null) {
			throw new ResourceNotFoundException(RESOURCE, id);
		}
		categoryRepository.delete(category);
	}
}
