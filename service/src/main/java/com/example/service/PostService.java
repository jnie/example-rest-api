package com.example.service;

import com.example.api.dto.CommentDto;
import com.example.api.dto.CreatePostRequest;
import com.example.api.dto.PostDto;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for Post operations.
 */
public interface PostService {

    /**
     * Get all posts.
     */
    List<PostDto> getAllPosts();

    /**
     * Get a post by ID.
     */
    Optional<PostDto> getPostById(Integer id);

    /**
     * Get posts by user ID.
     */
    List<PostDto> getPostsByUserId(Integer userId);

    /**
     * Create a new post.
     */
    PostDto createPost(CreatePostRequest request);

    /**
     * Update an existing post.
     */
    Optional<PostDto> updatePost(Integer id, CreatePostRequest request);

    /**
     * Delete a post.
     */
    boolean deletePost(Integer id);

    /**
     * Get posts from external API.
     */
    List<PostDto> getExternalPosts();

    /**
     * Get a post from external API.
     */
    Optional<PostDto> getExternalPostById(Integer id);

    /**
     * Get comments for a post.
     */
    List<CommentDto> getPostComments(Integer postId);

    /**
     * Get comments for a post from external API.
     */
    List<CommentDto> getExternalPostComments(Integer postId);
}
