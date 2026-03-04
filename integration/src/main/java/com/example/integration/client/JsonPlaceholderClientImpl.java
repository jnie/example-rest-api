package com.example.integration.client;

import com.example.integration.mapper.JsonPlaceholderMapper;
import com.example.service.model.Comment;
import com.example.service.model.Post;
import com.example.service.model.User;
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
    public Flux<Post> getAllPosts() {
        log.info("Fetching all posts from external API");
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(PostResponse.class)
                .map(mapper::toPost);
    }

    @Override
    public Mono<Post> getPostById(Integer id) {
        log.info("Fetching post with id {} from external API", id);
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(PostResponse.class)
                .map(mapper::toPost);
    }

    @Override
    public Flux<Post> getPostsByUserId(Integer userId) {
        log.info("Fetching posts for user {} from external API", userId);
        return webClient.get()
                .uri("/posts?userId={userId}", userId)
                .retrieve()
                .bodyToFlux(PostResponse.class)
                .map(mapper::toPost);
    }

    @Override
    public Flux<Comment> getCommentsByPostId(Integer postId) {
        log.info("Fetching comments for post {} from external API", postId);
        return webClient.get()
                .uri("/posts/{postId}/comments", postId)
                .retrieve()
                .bodyToFlux(CommentResponse.class)
                .map(mapper::toComment);
    }

    @Override
    public Flux<User> getAllUsers() {
        log.info("Fetching all users from external API");
        return webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(UserResponse.class)
                .map(mapper::toUser);
    }

    @Override
    public Mono<User> getUserById(Integer id) {
        log.info("Fetching user with id {} from external API", id);
        return webClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .map(mapper::toUser);
    }

    @Override
    public Mono<Post> createPost(Post post) {
        log.info("Creating new post on external API");
        return webClient.post()
                .uri("/posts")
                .bodyValue(mapper.toPostRequest(post))
                .retrieve()
                .bodyToMono(PostResponse.class)
                .map(mapper::toPost);
    }

    // Internal DTOs for JSONPlaceholder API responses
    private record PostResponse(Integer userId, Integer id, String title, String body) {}
    private record CommentResponse(Integer postId, Integer id, String name, String email, String body) {}
    private record UserResponse(Integer id, String name, String username, String email, String phone, String website) {}
    private record PostRequest(Integer userId, String title, String body) {}
}
