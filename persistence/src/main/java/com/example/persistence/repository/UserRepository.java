package com.example.persistence.repository;

import com.example.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
