package com.example.integration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain model for a Post from external API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
