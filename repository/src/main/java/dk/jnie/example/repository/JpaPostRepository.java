package dk.jnie.example.repository;

import dk.jnie.example.domain.model.Post;
import dk.jnie.example.domain.port.out.PostRepository;
import dk.jnie.example.repository.entity.PostEntity;
import dk.jnie.example.repository.repository.SpringPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA implementation of PostRepository.
 * Implements the domain port interface for post persistence.
 */
@Repository
@RequiredArgsConstructor
public class JpaPostRepository implements PostRepository {

    private final SpringPostRepository springPostRepository;

    @Override
    public List<Post> findAll() {
        return springPostRepository.findAll().stream()
                .map(PostEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return springPostRepository.findById(id)
                .map(PostEntity::toDomain);
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        return springPostRepository.findByUserId(userId).stream()
                .map(PostEntity::toDomain)
                .toList();
    }

    @Override
    public Post save(Post post) {
        PostEntity entity = PostEntity.fromDomain(post);
        PostEntity saved = springPostRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public void deleteById(Long id) {
        springPostRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return springPostRepository.existsById(id);
    }
}
