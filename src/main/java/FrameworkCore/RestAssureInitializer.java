package FrameworkCore;

import configuration.Environment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.Matchers.*;


public class RestAssureInitializer {
    public static RequestSpecification requestSpec;
    public static ResponseSpecification successResponseSpec;
    private Logger log = LoggerHook.log;

    /**
     * Function That Builds and Initializing request and response specifications
     */
    public RestAssureInitializer() {
        log.info("Initializing request and response specifications");
        RestAssureInitializer.requestSpec = new RequestSpecBuilder()
                .setBaseUri(Environment.baseUrl)
                .addHeader("Content-Type", "application/json")
                .build();
        RestAssureInitializer.successResponseSpec = new ResponseSpecBuilder()
                .expectStatusCode(anyOf(is(200), is(201)))
                .expectStatusCode(is(both(greaterThanOrEqualTo(100)).and(lessThanOrEqualTo(399)))) // 100-399 being the range of success codes
                .build();
    }
}
