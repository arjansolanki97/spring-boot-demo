package com.anjaneya.spring.boot.demo.services;

import com.anjaneya.spring.boot.demo.dtos.ProductDto;
import com.anjaneya.spring.boot.demo.dtos.request.ProductRequestDto;

import java.util.List;

public interface ProductService {
	ProductDto addProduct(ProductRequestDto productRequestDto);

	ProductDto updateProduct(ProductRequestDto productRequestDto);

	ProductDto getProductById(Long id);

	List<ProductDto> getAllProducts();

	void deleteProductById(Long id);

	List<ProductDto> getProductsByCategoryId(Long categoryId);
}
