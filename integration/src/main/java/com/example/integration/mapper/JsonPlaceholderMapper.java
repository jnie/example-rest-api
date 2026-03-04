package com.example.integration.mapper;

import com.example.integration.client.JsonPlaceholderClientImpl.CommentResponse;
import com.example.integration.client.JsonPlaceholderClientImpl.PostRequest;
import com.example.integration.client.JsonPlaceholderClientImpl.PostResponse;
import com.example.integration.client.JsonPlaceholderClientImpl.UserResponse;
import com.example.service.model.Comment;
import com.example.service.model.Post;
import com.example.service.model.User;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between external API DTOs and domain models.
 */
@Component
public class JsonPlaceholderMapper {

    public Post toPost(PostResponse response) {
        return Post.builder()
                .id(response.id())
                .userId(response.userId())
                .title(response.title())
                .body(response.body())
                .build();
    }

    public Comment toComment(CommentResponse response) {
        return Comment.builder()
                .id(response.id())
                .postId(response.postId())
                .name(response.name())
                .email(response.email())
                .body(response.body())
                .build();
    }

    public User toUser(UserResponse response) {
        return User.builder()
                .id(response.id())
                .name(response.name())
                .username(response.username())
                .email(response.email())
                .phone(response.phone())
                .website(response.website())
                .build();
    }

    public PostRequest toPostRequest(Post post) {
        return new PostRequest(post.getUserId(), post.getTitle(), post.getBody());
    }
}
