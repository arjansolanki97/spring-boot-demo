package com.anjaneya.spring.boot.demo.repositories;

import com.anjaneya.spring.boot.demo.entites.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
