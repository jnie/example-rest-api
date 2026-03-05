package dk.jnie.example.outbound.mapper;

import dk.jnie.example.domain.model.Post;
import dk.jnie.example.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting external API DTOs to domain models.
 */
@Component
public class ExternalPostMapper {

    /**
     * External API Post DTO.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExternalPostDto {
        private Long userId;
        private Long id;
        private String title;
        private String body;
    }

    /**
     * External API User DTO.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExternalUserDto {
        private Long id;
        private String name;
        private String username;
        private String email;
        private ExternalAddressDto address;
        private String phone;
        private String website;
        private ExternalCompanyDto company;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExternalAddressDto {
        private String street;
        private String suite;
        private String city;
        private String zipcode;
        private ExternalGeoDto geo;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExternalGeoDto {
        private String lat;
        private String lng;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ExternalCompanyDto {
        private String name;
        private String catchPhrase;
        private String bs;
    }

    /**
     * Map external Post DTO to domain Post.
     */
    public Post toDomain(ExternalPostDto dto) {
        if (dto == null) {
            return null;
        }
        return Post.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .title(dto.getTitle())
                .body(dto.getBody())
                .build();
    }

    /**
     * Map external User DTO to domain User.
     */
    public User toDomain(ExternalUserDto dto) {
        if (dto == null) {
            return null;
        }
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .website(dto.getWebsite())
                .build();
    }
}
