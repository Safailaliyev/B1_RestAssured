package io.loopcamp.test.day2_a_headers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinionApiHelloWorld {

    /**
     *     •When I send a GET request to http://your_ip:8000/api/minions
     *     •Then Response STATUS CODE must be 200
     *     • Then Response body should be equal to “Hello from minion”
     *     • And content type is "text/plain;charset=UTF-8" – This is for headers
     */

    String url = "http://44.204.78.14:8000/api/hello/";

    @DisplayName("GET api/hello")
    @Test
    public void helloApiTest(){
        Response response = RestAssured.get(url);

        System.out.println("Status Code: " +response.statusCode());
        assertEquals(200,response.statusCode());

        response.prettyPrint();
        assertEquals("Hello form minion", response.body().asString());

        //The assertion below is for the RESPONSE HEADER
        System.out.println("Content Type: " + response.contentType());
        assertEquals("text/plain;charset=ISO-8859-1", response.contentType());
        //assertTrue(response.contentType().startsWith("text/plain"));

    }

    //Same test in BDD approach --> Given, when,then
    //DO not worry about the Body assertion yet

    @DisplayName("Get api/hello - bdd")
@Test
    public void helloApiBddTest(){

        when().get(url) //now I already have the response
                .then().assertThat().statusCode(200)
                .and().contentType("text/plain;charset=ISO-8859-1");

    }

}
