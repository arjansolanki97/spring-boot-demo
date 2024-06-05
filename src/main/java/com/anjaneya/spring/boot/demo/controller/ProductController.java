package com.anjaneya.spring.boot.demo.controller;

import com.anjaneya.spring.boot.demo.constants.ApiPathConstants;
import com.anjaneya.spring.boot.demo.dtos.ProductDto;
import com.anjaneya.spring.boot.demo.dtos.request.ProductRequestDto;
import com.anjaneya.spring.boot.demo.entites.response.ApiResponseContainer;
import com.anjaneya.spring.boot.demo.entites.response.ResponseContainerEntity;
import com.anjaneya.spring.boot.demo.services.ProductService;
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
@RequestMapping(ApiPathConstants.PRODUCT_API)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
	ProductService productService;

	@PostMapping
	public ResponseContainerEntity<ProductDto> addProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
		ProductDto productDto = productService.addProduct(productRequestDto);
		return ApiResponseContainer.getResponse("Product added successfully", productDto, HttpStatus.OK);
	}

	@PutMapping
	public ResponseContainerEntity<ProductDto> updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto) {
		ProductDto productDto = productService.updateProduct(productRequestDto);
		return ApiResponseContainer.getResponse("Product updated successfully", productDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseContainerEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> products = productService.getAllProducts();
		return ApiResponseContainer.getResponse("Product list retrieved successfully", products, HttpStatus.OK);
	}

	@GetMapping(ApiPathConstants.PRODUCT_BY_CATEGORY)
	public ResponseContainerEntity<List<ProductDto>> getProductsByCategoryId(@PathVariable Long categoryId) {
		List<ProductDto> products = productService.getProductsByCategoryId(categoryId);
		return ApiResponseContainer.getResponse("Product list retrieved successfully", products, HttpStatus.OK);
	}

	@GetMapping("/{productId}")
	public ResponseContainerEntity<ProductDto> getProductById(@PathVariable Long productId) {
		ProductDto productDto = productService.getProductById(productId);
		return ApiResponseContainer.getResponse("Product retrieved successfully", productDto, HttpStatus.OK);
	}

	@DeleteMapping("/{productId}")
	public ResponseContainerEntity<Void> deleteProductById(@PathVariable Long productId) {
		productService.deleteProductById(productId);
		return ApiResponseContainer.getResponse("Product deleted successfully", HttpStatus.OK);
	}
}
