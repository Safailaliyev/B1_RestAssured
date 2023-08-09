package io.loopcamp.test.day11_c_specifications;

import io.loopcamp.util.MinionSecureTestBase;
import io.loopcamp.util.MinionTestBase;
import io.loopcamp.util.ConfigurationReader;
import io.loopcamp.util.DocuportApiTestBase;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class MinionSpecTest extends MinionSecureTestBase {

    RequestSpecification requestSpecification = given().accept(ContentType.JSON)
            .and().auth().basic(
                    ConfigurationReader.getProperty("minion.api.username"),
                    ConfigurationReader.getProperty("minion.api.password"));

    ResponseSpecification responseSpecification = expect().statusCode(200)
            .and().contentType("application/json");
    /**
     * Get all minions
     */

    @Test
    public void allMinionsTest(){
//        given().accept(ContentType.JSON)
//                .and().auth().basic(
//                        ConfigurationReader.getProperty("minion.api.username"),
//                        ConfigurationReader.getProperty("minion.api.password"))
        given().spec(requestSpecification)
                .when().get("/minions")

//
//                .then().assertThat().statusCode(200)
//                .and().contentType("application/json");

                .then().spec(responseSpecification).log().all();

    }

    /**
     * Get Single minion
     */

    @Test
    public void singleMinionTest(){
        given().spec(requestSpecification)
                .pathParam("id", 15)
                .when().get("/minions/{id}")
                .then().spec(responseSpecification)
                .and().body("name", equalTo("PutRequest"))  // you can still add some other assertion/verification)
                .log().all();
    }
}
