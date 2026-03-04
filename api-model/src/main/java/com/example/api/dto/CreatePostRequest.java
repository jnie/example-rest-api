package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating a new post.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequest {
    
    @NotNull(message = "User ID is required")
    private Integer userId;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Body is required")
    private String body;
}
