package dk.jnie.example.repository.entity;

import dk.jnie.example.domain.model.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JPA Entity for Post persistence.
 * Maps to the 'posts' table in the database.
 */
@Entity
@Table(name = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    /**
     * Convert domain Post to JPA Entity.
     */
    public static PostEntity fromDomain(Post post) {
        return PostEntity.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .body(post.getBody())
                .build();
    }

    /**
     * Convert JPA Entity to domain Post.
     */
    public Post toDomain() {
        return Post.builder()
                .id(this.id)
                .userId(this.userId)
                .title(this.title)
                .body(this.body)
                .build();
    }
}
