package library;

import FrameworkCore.RestAssureInitializer;
import configuration.Environment;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class PostApiHelper {

    public static Response getPosts(int userId) {
        Response allPostsResponse = given()
                .spec(RestAssureInitializer.requestSpec)
                .param("userId", userId)
                .get(Environment.postsEndpoint);
        allPostsResponse
                .then()
                .assertThat()
                .spec(RestAssureInitializer.successResponseSpec);
        return allPostsResponse;
    }

    public static Response getPosts() {
        Response allPostsResponse = given()
                .spec(RestAssureInitializer.requestSpec)
                .get(Environment.postsEndpoint);
        allPostsResponse
                .then()
                .assertThat()
                .spec(RestAssureInitializer.successResponseSpec);
        return allPostsResponse;
    }

    private static String getCreatePostPayload(String postTitle, String postBody, int userId) {
        JSONObject createPostJsonObject = new JSONObject();
        createPostJsonObject.put("title", postTitle);
        createPostJsonObject.put("body", postBody);
        createPostJsonObject.put("userId", userId);
        return createPostJsonObject.toString();
    }

    private static String getUpdatePostPayload(String key, String value) {
        JSONObject updatePostJsonObject = new JSONObject();
        updatePostJsonObject.put(key, value);
        return updatePostJsonObject.toString();
    }

    public static Response createPost(String postTitle, String postBody, int userId) {
        Response createPostResponse = given()
                .spec(RestAssureInitializer.requestSpec)
                .body(getCreatePostPayload(postTitle, postBody, userId))
                .post(Environment.postsEndpoint);
        createPostResponse
                .then()
                .assertThat()
                .spec(RestAssureInitializer.successResponseSpec);
        return createPostResponse;
    }

    public static Response updatePost(String updateAttributeKey, String updateAttributeValue, String postId) {
        Response updatePostResponse = given()
                .spec(RestAssureInitializer.requestSpec)
                .body(getUpdatePostPayload(updateAttributeKey, updateAttributeValue))
                .put(Environment.postsEndpoint + "/" + postId);
        updatePostResponse
                .then()
                .assertThat()
                .spec(RestAssureInitializer.successResponseSpec);
        return updatePostResponse;
    }

    public static Response deletePost(String postId) {
        Response deletePostResponse = given()
                .spec(RestAssureInitializer.requestSpec)
                .delete(Environment.postsEndpoint + "/" + postId);
        deletePostResponse
                .then()
                .assertThat()
                .spec(RestAssureInitializer.successResponseSpec);
        return deletePostResponse;
    }
}
