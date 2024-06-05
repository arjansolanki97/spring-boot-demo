package com.anjaneya.spring.boot.demo.repositories;

import com.anjaneya.spring.boot.demo.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Boolean existsByEmail(String email);

	Optional<UserEntity> findByEmail(String email);
}
