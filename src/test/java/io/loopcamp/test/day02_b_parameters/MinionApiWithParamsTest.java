package io.loopcamp.test.day02_b_parameters;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class MinionApiWithParamsTest {
    /**
     * Given Accept type is json
     * And path parameter id is  5
     * When user sends GET request to /api/minions/{id}
     * ---------------------------------
     * Then response status code should be 200
     * And response content type should be “application/json”
     * And json payload body should contain “Blythe”
     */


    String url = "http://44.204.78.14:8000/api/minions/";

    @DisplayName("Get /api/minions/{id}")
    @Test
    public void getSingleMinion(){
        int id = 5;
        given().accept(ContentType.JSON)
                .when().get(url + "/" + id); //id is value which part of the URL

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)//id is the value which passed using the pathParam method--> you can do it this way as well.and().pathParam("id", "/" + id)
                .when().get(url +"/{id}");

        response.prettyPrint();
        System.out.println("Status Code: " + response.statusCode());
        assertEquals(200, response.statusCode());
        assertEquals(HttpStatus.SC_OK,response.statusCode());//We can do it this way as well.

        System.out.println("Content Type: " + response.contentType());
        System.out.println("Content Type: " + response.getHeader("content-type"));
        assertEquals("application/json" , response.contentType());

        assertTrue(response.body().asString().contains("Blythe"));
        assertTrue(response.asString().contains("Blythe"));
    }

    @DisplayName("Get")
    @Test
    public void getSingleMinionNotFound(){
        //This is the REQUEST part
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",1000)
                .when().get(url + "/{id}"); //api/minions/1000

        //Now let's do the RESPONSE part
        System.out.println("Status Code: " + response.statusCode());
        assertEquals(404,response.statusCode());
        assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode()); //This is doing the same thing as above

        assertEquals("application/json", response.contentType());
        assertEquals(ContentType.JSON.toString(), response.contentType()); //this is doing the thing above|The reason we did toSting() is because, assertEquals compare to same data type, in this case String

        //and json response body should contain "Not found" message
        response.prettyPrint();
        assertTrue(response.body().asString().contains("Not Found"));

        System.out.println("asSting: " + response.asString().toUpperCase()); //since we converted response to a String, we can apply all the String methods

        //assertTrue(response.prettyPrint().contains("Not Found")); //You can use prettyPrint() as well but NOT GOOD practice since it will print the hole response body on assertion




    }
}
