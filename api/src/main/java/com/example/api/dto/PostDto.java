package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Post entity.
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
