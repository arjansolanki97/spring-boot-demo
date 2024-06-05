package com.anjaneya.spring.boot.demo.repositories;

import com.anjaneya.spring.boot.demo.entites.CategoryEntity;
import com.anjaneya.spring.boot.demo.entites.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findByCategory(CategoryEntity categoryEntity);
}
