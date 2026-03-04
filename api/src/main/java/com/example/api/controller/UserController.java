package com.example.api.controller;

import com.example.api.dto.UserDto;
import com.example.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing users.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    /**
     * Get all users from local database and external API.
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Get a specific user by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Fetch users from external JSONPlaceholder API.
     */
    @GetMapping("/external")
    public ResponseEntity<List<UserDto>> getExternalUsers() {
        List<UserDto> users = userService.getExternalUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Fetch a single user from external API.
     */
    @GetMapping("/external/{id}")
    public ResponseEntity<UserDto> getExternalUserById(@PathVariable Integer id) {
        return userService.getExternalUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
