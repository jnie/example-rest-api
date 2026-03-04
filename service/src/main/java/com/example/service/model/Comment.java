package com.example.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain model for Comment entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;
}
