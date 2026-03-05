package dk.jnie.example.domain.port.in;

import dk.jnie.example.domain.model.Post;

import java.util.List;
import java.util.Optional;

/**
 * Incoming port for Post use cases.
 * Defines the contract for post-related operations.
 */
public interface PostUseCase {

    /**
     * Get all posts.
     */
    List<Post> getAllPosts();

    /**
     * Get post by ID.
     */
    Optional<Post> getPostById(Long id);

    /**
     * Get posts by user ID.
     */
    List<Post> getPostsByUserId(Long userId);

    /**
     * Create a new post.
     */
    Post createPost(Post post);

    /**
     * Update an existing post.
     */
    Post updatePost(Long id, Post post);

    /**
     * Delete a post by ID.
     */
    void deletePost(Long id);

    /**
     * Fetch posts from external API and save to database.
     */
    List<Post> syncPostsFromExternalApi();
}
