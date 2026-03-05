package dk.jnie.example.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Post domain entity representing a blog post.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
