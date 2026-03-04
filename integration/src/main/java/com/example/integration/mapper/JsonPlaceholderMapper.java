package com.example.integration.mapper;

import com.example.integration.client.JsonPlaceholderClientImpl.CommentResponse;
import com.example.integration.client.JsonPlaceholderClientImpl.PostRequest;
import com.example.integration.client.JsonPlaceholderClientImpl.PostResponse;
import com.example.integration.client.JsonPlaceholderClientImpl.UserResponse;
import com.example.integration.dto.CommentDto;
import com.example.integration.dto.PostDto;
import com.example.integration.dto.UserDto;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between external API DTOs and integration DTOs.
 */
@Component
public class JsonPlaceholderMapper {

    public PostDto toPostDto(PostResponse response) {
        return PostDto.builder()
                .id(response.id())
                .userId(response.userId())
                .title(response.title())
                .body(response.body())
                .build();
    }

    public CommentDto toCommentDto(CommentResponse response) {
        return CommentDto.builder()
                .id(response.id())
                .postId(response.postId())
                .name(response.name())
                .email(response.email())
                .body(response.body())
                .build();
    }

    public UserDto toUserDto(UserResponse response) {
        return UserDto.builder()
                .id(response.id())
                .name(response.name())
                .username(response.username())
                .email(response.email())
                .phone(response.phone())
                .website(response.website())
                .build();
    }

    public PostRequest toPostRequest(PostDto post) {
        return new PostRequest(post.getUserId(), post.getTitle(), post.getBody());
    }
}
