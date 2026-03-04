package com.example.integration.client;

import com.example.integration.dto.CommentDto;
import com.example.integration.dto.PostDto;
import com.example.integration.dto.UserDto;
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
    Flux<PostDto> getAllPosts();

    /**
     * Fetch a single post by ID from external API.
     */
    Mono<PostDto> getPostById(Integer id);

    /**
     * Fetch posts by user ID from external API.
     */
    Flux<PostDto> getPostsByUserId(Integer userId);

    /**
     * Fetch all comments for a post from external API.
     */
    Flux<CommentDto> getCommentsByPostId(Integer postId);

    /**
     * Fetch all users from external API.
     */
    Flux<UserDto> getAllUsers();

    /**
     * Fetch a single user by ID from external API.
     */
    Mono<UserDto> getUserById(Integer id);

    /**
     * Create a new post on external API.
     */
    Mono<PostDto> createPost(PostDto post);
}
