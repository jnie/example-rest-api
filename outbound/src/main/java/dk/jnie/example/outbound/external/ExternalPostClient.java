package dk.jnie.example.outbound.external;

import dk.jnie.example.domain.model.Post;
import dk.jnie.example.domain.model.User;
import dk.jnie.example.domain.port.out.ExternalApiPort;
import dk.jnie.example.outbound.mapper.ExternalPostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * Implementation of ExternalApiPort using WebClient.
 * Fetches data from jsonplaceholder.typicode.com
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ExternalPostClient implements ExternalApiPort {

    private final WebClient webClient;
    private final ExternalPostMapper mapper;

    @Value("${external.api.base-url:https://jsonplaceholder.typicode.com}")
    private String baseUrl;

    @Override
    public List<Post> fetchPostsFromExternalApi() {
        log.info("Fetching posts from external API: {}", baseUrl);
        
        List<ExternalPostMapper.ExternalPostDto> posts = webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(ExternalPostMapper.ExternalPostDto.class)
                .collectList()
                .block();

        if (posts == null) {
            log.warn("No posts received from external API");
            return List.of();
        }

        log.info("Fetched {} posts from external API", posts.size());
        return posts.stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public List<User> fetchUsersFromExternalApi() {
        log.info("Fetching users from external API: {}", baseUrl);

        List<ExternalPostMapper.ExternalUserDto> users = webClient.get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(ExternalPostMapper.ExternalUserDto.class)
                .collectList()
                .block();

        if (users == null) {
            log.warn("No users received from external API");
            return List.of();
        }

        log.info("Fetched {} users from external API", users.size());
        return users.stream()
                .map(mapper::toDomain)
                .toList();
    }
}
