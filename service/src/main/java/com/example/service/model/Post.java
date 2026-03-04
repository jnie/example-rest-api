package com.example.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain model for Post entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
