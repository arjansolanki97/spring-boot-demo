package com.anjaneya.spring.boot.demo.services.impl;

import com.anjaneya.spring.boot.demo.dtos.ProductDto;
import com.anjaneya.spring.boot.demo.dtos.request.ProductRequestDto;
import com.anjaneya.spring.boot.demo.entites.CategoryEntity;
import com.anjaneya.spring.boot.demo.entites.ProductEntity;
import com.anjaneya.spring.boot.demo.exceptions.ResourceNotFoundException;
import com.anjaneya.spring.boot.demo.mapper.ModelMapperService;
import com.anjaneya.spring.boot.demo.repositories.CategoryRepository;
import com.anjaneya.spring.boot.demo.repositories.ProductRepository;
import com.anjaneya.spring.boot.demo.services.ProductService;
import com.anjaneya.spring.boot.demo.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductServiceImpl implements ProductService {

	ProductRepository productRepository;

	CategoryRepository categoryRepository;

	UserService userService;

	ModelMapperService modelMapperService;

	@Override
	public ProductDto addProduct(ProductRequestDto productRequestDto) {

		CategoryEntity categoryEntity = categoryRepository.findById(productRequestDto.getCategory().getId()).orElse(null);
		if (categoryEntity == null) {
			throw new ResourceNotFoundException("Category not found");
		}

		ProductEntity productEntity = modelMapperService.convertToType(productRequestDto, ProductEntity.class);
		productEntity.setCreatedBy(userService.getLoggedInUserEntity());
		productEntity.setUpdatedBy(userService.getLoggedInUserEntity());
		productEntity.setUser(userService.getLoggedInUserEntity());

		productEntity = productRepository.save(productEntity);
		return modelMapperService.convertToType(productEntity, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductRequestDto productRequestDto) {

		CategoryEntity categoryEntity = categoryRepository.findById(productRequestDto.getCategory().getId()).orElse(null);
		if (categoryEntity == null) {
			throw new ResourceNotFoundException("Category not found");
		}

		ProductEntity productEntity = productRepository.findById(productRequestDto.getId()).orElse(null);
		if (productEntity == null) {
			throw new ResourceNotFoundException("Product not found");
		}
		productEntity.setName(productRequestDto.getName());
		productEntity.setCategory(categoryEntity);
		productEntity.setUpdatedBy(userService.getLoggedInUserEntity());
		productEntity = productRepository.save(productEntity);
		return modelMapperService.convertToType(productEntity, ProductDto.class);
	}

	@Override
	public ProductDto getProductById(Long id) {
		ProductEntity productEntity = productRepository.findById(id).orElse(null);
		if (productEntity == null) {
			throw new ResourceNotFoundException("Product", id);
		}
		return modelMapperService.convertToType(productEntity, ProductDto.class);
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductEntity> productEntities = productRepository.findAll();
		return modelMapperService.convertListEntityToListDto(productEntities, ProductDto.class);
	}

	@Override
	public void deleteProductById(Long id) {
		ProductEntity productEntity = productRepository.findById(id).orElse(null);
		if (productEntity == null) {
			throw new ResourceNotFoundException("Product", id);
		}
		productRepository.delete(productEntity);
	}

	@Override
	public List<ProductDto> getProductsByCategoryId(Long categoryId) {
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);
		if (categoryEntity == null) {
			throw new ResourceNotFoundException("Category", categoryId);
		}
		List<ProductEntity> productEntities = productRepository.findByCategory(categoryEntity);
		return modelMapperService.convertListEntityToListDto(productEntities, ProductDto.class);
	}
}
