package dk.jnie.example.domain.port.out;

import dk.jnie.example.domain.model.Post;
import dk.jnie.example.domain.model.User;

import java.util.List;

/**
 * Outgoing port for external API calls.
 * Defines the contract for fetching data from external sources.
 */
public interface ExternalApiPort {

    /**
     * Fetch all posts from external API.
     */
    List<Post> fetchPostsFromExternalApi();

    /**
     * Fetch all users from external API.
     */
    List<User> fetchUsersFromExternalApi();
}
