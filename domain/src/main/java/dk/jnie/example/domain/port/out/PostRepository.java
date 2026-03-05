package dk.jnie.example.domain.port.out;

import dk.jnie.example.domain.model.Post;

import java.util.List;
import java.util.Optional;

/**
 * Outgoing port for Post persistence operations.
 * Defines the contract for post data access.
 */
public interface PostRepository {

    /**
     * Find all posts.
     */
    List<Post> findAll();

    /**
     * Find post by ID.
     */
    Optional<Post> findById(Long id);

    /**
     * Find posts by user ID.
     */
    List<Post> findByUserId(Long userId);

    /**
     * Save a post.
     */
    Post save(Post post);

    /**
     * Delete post by ID.
     */
    void deleteById(Long id);

    /**
     * Check if post exists by ID.
     */
    boolean existsById(Long id);
}
