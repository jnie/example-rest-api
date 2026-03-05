package dk.jnie.example.domain.port.in;

import dk.jnie.example.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Incoming port for User use cases.
 * Defines the contract for user-related operations.
 */
public interface UserUseCase {

    /**
     * Get all users.
     */
    List<User> getAllUsers();

    /**
     * Get user by ID.
     */
    Optional<User> getUserById(Long id);

    /**
     * Create a new user.
     */
    User createUser(User user);

    /**
     * Update an existing user.
     */
    User updateUser(Long id, User user);

    /**
     * Delete a user by ID.
     */
    void deleteUser(Long id);

    /**
     * Fetch users from external API and save to database.
     */
    List<User> syncUsersFromExternalApi();
}
