package dk.jnie.example.inbound.rest;

import dk.jnie.example.domain.model.Post;
import dk.jnie.example.domain.port.in.PostUseCase;
import dk.jnie.example.inbound.rest.dto.PostRequestDto;
import dk.jnie.example.inbound.rest.dto.PostResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for Post operations.
 * Handles HTTP requests and delegates to the application service.
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostUseCase postUseCase;

    /**
     * Get all posts.
     */
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        log.info("GET /api/posts - Fetching all posts");
        List<PostResponseDto> posts = postUseCase.getAllPosts().stream()
                .map(this::toResponseDto)
                .toList();
        return ResponseEntity.ok(posts);
    }

    /**
     * Get post by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        log.info("GET /api/posts/{} - Fetching post by ID", id);
        return postUseCase.getPostById(id)
                .map(post -> ResponseEntity.ok(toResponseDto(post)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get posts by user ID.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostResponseDto>> getPostsByUserId(@PathVariable Long userId) {
        log.info("GET /api/posts/user/{} - Fetching posts by user ID", userId);
        List<PostResponseDto> posts = postUseCase.getPostsByUserId(userId).stream()
                .map(this::toResponseDto)
                .toList();
        return ResponseEntity.ok(posts);
    }

    /**
     * Create a new post.
     */
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto requestDto) {
        log.info("POST /api/posts - Creating new post: {}", requestDto.getTitle());
        Post post = toDomain(requestDto);
        Post created = postUseCase.createPost(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDto(created));
    }

    /**
     * Update an existing post.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequestDto requestDto) {
        log.info("PUT /api/posts/{} - Updating post", id);
        try {
            Post post = toDomain(requestDto);
            Post updated = postUseCase.updatePost(id, post);
            return ResponseEntity.ok(toResponseDto(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a post.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        log.info("DELETE /api/posts/{} - Deleting post", id);
        postUseCase.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Sync posts from external API.
     */
    @PostMapping("/sync")
    public ResponseEntity<List<PostResponseDto>> syncPosts() {
        log.info("POST /api/posts/sync - Syncing posts from external API");
        List<PostResponseDto> posts = postUseCase.syncPostsFromExternalApi().stream()
                .map(this::toResponseDto)
                .toList();
        return ResponseEntity.ok(posts);
    }

    // --- Mappers ---

    private PostResponseDto toResponseDto(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .body(post.getBody())
                .build();
    }

    private Post toDomain(PostRequestDto dto) {
        return Post.builder()
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
    }
}
