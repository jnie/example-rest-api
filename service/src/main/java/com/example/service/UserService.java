package com.example.service;

import com.example.api.dto.UserDto;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for User operations.
 */
public interface UserService {

    /**
     * Get all users.
     */
    List<UserDto> getAllUsers();

    /**
     * Get a user by ID.
     */
    Optional<UserDto> getUserById(Integer id);

    /**
     * Get users from external API.
     */
    List<UserDto> getExternalUsers();

    /**
     * Get a user from external API.
     */
    Optional<UserDto> getExternalUserById(Integer id);
}
