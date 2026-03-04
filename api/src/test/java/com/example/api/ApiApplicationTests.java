package com.example.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Integration tests for the REST API.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiApplicationTests {

    @LocalServerPort
    private int port;

    /**
     * Test that the application starts successfully.
     */
    @Test
    void contextLoads() {
        // This ensures the application context loads
    }

    /**
     * Test health endpoint.
     */
    @Test
    void testHealthEndpoint() {
        given()
            .port(port)
            .when()
            .get("/actuator/health")
            .then()
            .statusCode(200)
            .body("status", equalTo("UP"));
    }

    /**
     * Test getting all posts (should return empty initially).
     */
    @Test
    void testGetAllPosts() {
        given()
            .port(port)
            .when()
            .get("/api/posts")
            .then()
            .statusCode(200)
            .body("$", hasSize(greaterThanOrEqualTo(0)));
    }

    /**
     * Test getting external posts from JSONPlaceholder.
     */
    @Test
    void testGetExternalPosts() {
        given()
            .port(port)
            .when()
            .get("/api/posts/external")
            .then()
            .statusCode(200)
            .body("$", hasSize(100));
    }

    /**
     * Test getting a single external post.
     */
    @Test
    void testGetExternalPostById() {
        given()
            .port(port)
            .when()
            .get("/api/posts/external/1")
            .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", notNullValue());
    }

    /**
     * Test creating a post.
     */
    @Test
    void testCreatePost() {
        String requestBody = """
            {
                "userId": 1,
                "title": "Test Post",
                "body": "This is a test post body"
            }
            """;

        given()
            .port(port)
            .contentType(ContentType.JSON)
            .body(requestBody)
            .when()
            .post("/api/posts")
            .then()
            .statusCode(201)
            .body("title", equalTo("Test Post"))
            .body("userId", equalTo(1));
    }

    /**
     * Test getting external users.
     */
    @Test
    void testGetExternalUsers() {
        given()
            .port(port)
            .when()
            .get("/api/users/external")
            .then()
            .statusCode(200)
            .body("$", hasSize(10));
    }

    /**
     * Test getting comments for a post.
     */
    @Test
    void testGetPostComments() {
        given()
            .port(port)
            .when()
            .get("/api/posts/external/1/comments")
            .then()
            .statusCode(200)
            .body("$", hasSize(5));
    }
}
