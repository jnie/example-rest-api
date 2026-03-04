package com.example.service.impl;

import com.example.api.dto.UserDto;
import com.example.integration.client.JsonPlaceholderClient;
import com.example.persistence.entity.UserEntity;
import com.example.persistence.repository.UserRepository;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of UserService.
 * Handles business logic for users, including local database and external API integration.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JsonPlaceholderClient externalClient;

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Fetching all users from local database");
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(Integer id) {
        log.info("Fetching user with id: {}", id);
        return userRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<UserDto> getExternalUsers() {
        log.info("Fetching all users from external API");
        return externalClient.getAllUsers()
                .collectList()
                .block()
                .stream()
                .map(this::toDtoFromIntegration)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getExternalUserById(Integer id) {
        log.info("Fetching user with id {} from external API", id);
        return externalClient.getUserById(id)
                .map(this::toDtoFromIntegration)
                .blockOptional();
    }

    // Mapping methods
    private UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .website(entity.getWebsite())
                .build();
    }

    private UserDto toDtoFromIntegration(com.example.integration.dto.UserDto user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .website(user.getWebsite())
                .build();
    }
}
