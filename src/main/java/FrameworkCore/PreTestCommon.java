package FrameworkCore;

import static io.restassured.RestAssured.given;

public class PreTestCommon {

    public static boolean isAppBackendUp() {
        return given()
                .spec(RestAssureInitializer.requestSpec)
                .get().statusCode() == 200;
    }
}