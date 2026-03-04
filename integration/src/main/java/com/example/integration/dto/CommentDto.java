package com.example.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain model for a Comment from external API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;
}
