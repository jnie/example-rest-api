package com.example.integration.client;

import com.example.service.model.Comment;
import com.example.service.model.Post;
import com.example.service.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Interface for external API client.
 * Defines contracts for fetching data from JSONPlaceholder API.
 */
public interface JsonPlaceholderClient {

    /**
     * Fetch all posts from external API.
     */
    Flux<Post> getAllPosts();

    /**
     * Fetch a single post by ID from external API.
     */
    Mono<Post> getPostById(Integer id);

    /**
     * Fetch posts by user ID from external API.
     */
    Flux<Post> getPostsByUserId(Integer userId);

    /**
     * Fetch all comments for a post from external API.
     */
    Flux<Comment> getCommentsByPostId(Integer postId);

    /**
     * Fetch all users from external API.
     */
    Flux<User> getAllUsers();

    /**
     * Fetch a single user by ID from external API.
     */
    Mono<User> getUserById(Integer id);

    /**
     * Create a new post on external API.
     */
    Mono<Post> createPost(Post post);
}
