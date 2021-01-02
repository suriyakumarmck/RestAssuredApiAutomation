package library;

import FrameworkCore.RestAssureInitializer;
import configuration.Environment;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserApiHelper {

    public static Response getAllUsers() {
        Response allUsersResponse = given()
                .spec(RestAssureInitializer.requestSpec)
                .get(Environment.usersEndpoint);
        allUsersResponse
                .then()
                .assertThat()
                .spec(RestAssureInitializer.successResponseSpec);
        return allUsersResponse;
    }

    public static Response getUser(String username) {
        Response allUsersResponse = given()
                .spec(RestAssureInitializer.requestSpec)
                .param("username", username)
                .get(Environment.usersEndpoint);
        allUsersResponse
                .then()
                .assertThat()
                .spec(RestAssureInitializer.successResponseSpec);
        return allUsersResponse;
    }
}
