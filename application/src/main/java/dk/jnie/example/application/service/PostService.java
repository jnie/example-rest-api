package dk.jnie.example.application.service;

import dk.jnie.example.domain.model.Post;
import dk.jnie.example.domain.port.in.PostUseCase;
import dk.jnie.example.domain.port.out.ExternalApiPort;
import dk.jnie.example.domain.port.out.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Application service implementing PostUseCase.
 * Coordinates between inbound (controllers) and outbound (repository/external API) ports.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PostService implements PostUseCase {

    private final PostRepository postRepository;
    private final ExternalApiPort externalApiPort;

    @Override
    public List<Post> getAllPosts() {
        log.debug("Getting all posts");
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        log.debug("Getting post by ID: {}", id);
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        log.debug("Getting posts by user ID: {}", userId);
        return postRepository.findByUserId(userId);
    }

    @Override
    public Post createPost(Post post) {
        log.info("Creating new post: {}", post.getTitle());
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post post) {
        log.info("Updating post with ID: {}", id);
        
        if (!postRepository.existsById(id)) {
            throw new IllegalArgumentException("Post not found with ID: " + id);
        }
        
        post.setId(id);
        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        log.info("Deleting post with ID: {}", id);
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> syncPostsFromExternalApi() {
        log.info("Syncing posts from external API");
        
        // Fetch posts from external API
        List<Post> externalPosts = externalApiPort.fetchPostsFromExternalApi();
        
        // Save each post to local database
        for (Post post : externalPosts) {
            // Reset ID to let database generate new IDs
            post.setId(null);
            postRepository.save(post);
        }
        
        log.info("Synced {} posts from external API", externalPosts.size());
        return postRepository.findAll();
    }
}
