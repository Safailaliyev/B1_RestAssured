package io.loopcamp.test.day11_a_authorization;

import io.loopcamp.util.*;
import org.junit.jupiter.api.DisplayName;
import io.loopcamp.pojo.Minion;
import io.loopcamp.util.MinionTestBase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.loopcamp.util.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class MinionBasicAuthTest extends MinionSecureTestBase {

    /**
     given accept type is json
     and basic auth credentials are loopacademy , loopacademy
     When user sends GET request to /minions
     Then status code is 200
     And content type is json
     And print response
     */

    @DisplayName("GET request /minons with basic auth ")
    @Test
    public void getMinionWithAuthTest(){
        given().accept(ContentType.JSON)
                .and().auth().basic("loopacademy", "loopacademy")
                .when().get("/minions")
                .then().assertThat().statusCode(is(200))
                .and().contentType(ContentType.JSON)
                .log().all();
    }

    /**
     given accept type is json
     When user sends GET request to /minions
     -------
     Then status code is 401
     And content type is json
     And "error" is "Unathorized"
     And print response
     */

    //Negative Test Case -- > rainy day
    @Test
    public void getAllMinionsUnauthorizedTest(){
        given().accept(ContentType.JSON)
                .when().get("/minions")
                .then().statusCode(401)
                .and().contentType(ContentType.JSON)
                .and().body("error", equalTo("Unauthorized"))
                .log().all();
    }
}
