package com.example.persistence.repository;

import com.example.persistence.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for Post entity.
 */
@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    
    /**
     * Find all posts by user ID.
     */
    List<PostEntity> findByUserId(Integer userId);
}
