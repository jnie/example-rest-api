package dk.jnie.example.domain.port.out;

import dk.jnie.example.domain.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Outgoing port for User persistence operations.
 * Defines the contract for user data access.
 */
public interface UserRepository {

    /**
     * Find all users.
     */
    List<User> findAll();

    /**
     * Find user by ID.
     */
    Optional<User> findById(Long id);

    /**
     * Save a user.
     */
    User save(User user);

    /**
     * Delete user by ID.
     */
    void deleteById(Long id);

    /**
     * Check if user exists by ID.
     */
    boolean existsById(Long id);
}
