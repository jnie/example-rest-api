package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Comment entity.
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
