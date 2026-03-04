package com.example.integration.client;

import com.example.integration.dto.CommentDto;
import com.example.integration.dto.PostDto;
import com.example.integration.dto.UserDto;
import com.example.integration.mapper.JsonPlaceholderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Implementation of JsonPlaceholderClient using WebClient.
 * Communicates with JSONPlaceholder API (https://jsonplaceholder.typicode.com).
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JsonPlaceholderClientImpl implements JsonPlaceholderClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    
    private final WebClient webClient;
    private final JsonPlaceholderMapper mapper;

    @Override
    public Flux<PostDto> getAllPosts() {
        log.info("Fetching all posts from external API");
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(PostResponse.class)
                .map(mapper::toPostDto);
    }

    @Override
    public Mono<PostDto> getPostById(Integer id) {
        log.info("Fetching post with id {} from external API", id);
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(PostResponse.class)
                .map(mapper::toPostDto);
    }

    @Override
    public Flux<PostDto> getPostsByUserId(Integer userId) {
        log.info("Fetching posts for user {} from external API", userId);
        return webClient.get()
                .uri("/posts?userId={userId}", userId)
                .retrieve()
                .bodyToFlux(PostResponse.class)
                .map(mapper::toPostDto);
    }

    @Override
    public Flux<CommentDto> getCommentsByPostId(Integer postId) {
        log.info("Fetching comments for post {} from external API", postId);
        return webClient.get()
                .uri("/posts/{postId}/comments", postId)
                .retrieve()
                .bodyToFlux(CommentResponse.class)
                .map(mapper::toCommentDto);
    }

    @Override
    public Flux<UserDto> getAllUsers() {
        log.info("Fetching all users from external API");
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(UserResponse.class)
                .map(mapper::toUserDto);
    }

    @Override
    public Mono<UserDto> getUserById(Integer id) {
        log.info("Fetching user with id {} from external API", id);
        return webClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(mapper::toUserDto);
    }

    @Override
    public Mono<PostDto> createPost(PostDto post) {
        log.info("Creating new post on external API");
        return webClient.post()
                .uri("/posts")
                .bodyValue(mapper.toPostRequest(post))
                .retrieve()
                .bodyToMono(PostResponse.class)
                .map(mapper::toPostDto);
    }

    // Internal DTOs for JSONPlaceholder API responses
    public record PostResponse(Integer userId, Integer id, String title, String body) {}
    public record CommentResponse(Integer postId, Integer id, String name, String email, String body) {}
    public record UserResponse(Integer id, String name, String username, String email, String phone, String website) {}
    public record PostRequest(Integer userId, String title, String body) {}
}
