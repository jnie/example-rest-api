package com.example.api.controller;

import com.example.api.dto.CommentDto;
import com.example.api.dto.CreatePostRequest;
import com.example.api.dto.PostDto;
import com.example.api.dto.UserDto;
import com.example.service.impl.PostServiceImpl;
import com.example.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing posts.
 * Provides endpoints for CRUD operations on posts,
 * including fetching from external JSONPlaceholder API.
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostServiceImpl postService;
    private final UserServiceImpl userService;

    /**
     * Get all posts from local database and external API.
     */
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    /**
     * Get a specific post by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get posts by user ID.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer userId) {
        List<PostDto> posts = postService.getPostsByUserId(userId);
        return ResponseEntity.ok(posts);
    }

    /**
     * Create a new post (saved to local database).
     */
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostRequest request) {
        PostDto createdPost = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    /**
     * Update an existing post.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Integer id, 
                                               @Valid @RequestBody CreatePostRequest request) {
        return postService.updatePost(id, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a post.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        boolean deleted = postService.deletePost(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Fetch posts from external JSONPlaceholder API.
     */
    @GetMapping("/external")
    public ResponseEntity<List<PostDto>> getExternalPosts() {
        List<PostDto> posts = postService.getExternalPosts();
        return ResponseEntity.ok(posts);
    }

    /**
     * Fetch a single post from external API.
     */
    @GetMapping("/external/{id}")
    public ResponseEntity<PostDto> getExternalPostById(@PathVariable Integer id) {
        return postService.getExternalPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get comments for a specific post.
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDto>> getPostComments(@PathVariable Integer id) {
        List<CommentDto> comments = postService.getPostComments(id);
        return ResponseEntity.ok(comments);
    }

    /**
     * Get comments for a post from external API.
     */
    @GetMapping("/external/{id}/comments")
    public ResponseEntity<List<CommentDto>> getExternalPostComments(@PathVariable Integer id) {
        List<CommentDto> comments = postService.getExternalPostComments(id);
        return ResponseEntity.ok(comments);
    }
}
