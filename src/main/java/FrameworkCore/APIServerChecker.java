package FrameworkCore;

import static io.restassured.RestAssured.given;

public class APIServerChecker {
    /**
     * Function used to verify if Backend API is UP
     */
    public static boolean isAPIServerUp() {
        return given()
                .spec(RestAssureInitializer.requestSpec)
                .get().statusCode() == 200;
    }
}