package library;

import FrameworkCore.RestAssureInitializer;
import configuration.Environment;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CommentApiHelper {

    public static Response getCommentsOnPost(int postId) {
        Response allCommentsOnPostResponse = given()
                .spec(RestAssureInitializer.requestSpec)
                .param("postId", postId)
                .get(Environment.commentsEndpoint);
        allCommentsOnPostResponse
                .then()
                .assertThat()
                .spec(RestAssureInitializer.successResponseSpec);
        return allCommentsOnPostResponse;
    }

}
