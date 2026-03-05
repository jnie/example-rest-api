package dk.jnie.example.repository.repository;

import dk.jnie.example.repository.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for Post entities.
 */
@Repository
public interface SpringPostRepository extends JpaRepository<PostEntity, Long> {

    /**
     * Find all posts by user ID.
     */
    List<PostEntity> findByUserId(Long userId);
}
