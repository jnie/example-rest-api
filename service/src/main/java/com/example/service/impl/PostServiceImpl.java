package com.example.service.impl;

import com.example.api.dto.CommentDto;
import com.example.api.dto.CreatePostRequest;
import com.example.api.dto.PostDto;
import com.example.integration.client.JsonPlaceholderClient;
import com.example.persistence.entity.PostEntity;
import com.example.persistence.repository.PostRepository;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of PostService.
 * Handles business logic for posts, including local database and external API integration.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final JsonPlaceholderClient externalClient;

    @Override
    public List<PostDto> getAllPosts() {
        log.info("Fetching all posts from local database");
        return postRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PostDto> getPostById(Integer id) {
        log.info("Fetching post with id: {}", id);
        return postRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<PostDto> getPostsByUserId(Integer userId) {
        log.info("Fetching posts for user id: {}", userId);
        return postRepository.findByUserId(userId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PostDto createPost(CreatePostRequest request) {
        log.info("Creating new post with title: {}", request.getTitle());
        PostEntity entity = PostEntity.builder()
                .userId(request.getUserId())
                .title(request.getTitle())
                .body(request.getBody())
                .build();
        PostEntity saved = postRepository.save(entity);
        log.info("Created post with id: {}", saved.getId());
        return toDto(saved);
    }

    @Override
    @Transactional
    public Optional<PostDto> updatePost(Integer id, CreatePostRequest request) {
        log.info("Updating post with id: {}", id);
        return postRepository.findById(id)
                .map(entity -> {
                    entity.setUserId(request.getUserId());
                    entity.setTitle(request.getTitle());
                    entity.setBody(request.getBody());
                    return toDto(postRepository.save(entity));
                });
    }

    @Override
    @Transactional
    public boolean deletePost(Integer id) {
        log.info("Deleting post with id: {}", id);
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<PostDto> getExternalPosts() {
        log.info("Fetching all posts from external API");
        return externalClient.getAllPosts()
                .collectList()
                .block()
                .stream()
                .map(this::toApiDtoFromIntegration)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PostDto> getExternalPostById(Integer id) {
        log.info("Fetching post with id {} from external API", id);
        return externalClient.getPostById(id)
                .map(this::toApiDtoFromIntegration)
                .blockOptional();
    }

    @Override
    public List<CommentDto> getPostComments(Integer postId) {
        log.info("Fetching comments for post id: {}", postId);
        // For local posts, we'd need a comment repository
        // For now, return external comments
        return getExternalPostComments(postId);
    }

    @Override
    public List<CommentDto> getExternalPostComments(Integer postId) {
        log.info("Fetching comments for post {} from external API", postId);
        return externalClient.getCommentsByPostId(postId)
                .collectList()
                .block()
                .stream()
                .map(this::toApiCommentDtoFromIntegration)
                .collect(Collectors.toList());
    }

    // Mapping methods
    private PostDto toDto(PostEntity entity) {
        return PostDto.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .title(entity.getTitle())
                .body(entity.getBody())
                .build();
    }

    private PostDto toApiDtoFromIntegration(com.example.integration.dto.PostDto post) {
        return PostDto.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .body(post.getBody())
                .build();
    }

    private CommentDto toApiCommentDtoFromIntegration(com.example.integration.dto.CommentDto comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .postId(comment.getPostId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .build();
    }
}
