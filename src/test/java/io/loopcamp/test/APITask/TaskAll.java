package io.loopcamp.test.APITask;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;


import io.loopcamp.util.JsonPlaceHolder;


public class TaskAll  extends JsonPlaceHolder {
/**
        API Documentation:https://jsonplaceholder.typicode.com (scroll down to Routes section)

    Q1:
            - Given accept type is Json
- When user sends request to https://jsonplaceholder.typicode.com/posts

            -Then print response body

- And status code is 200
            -e
 */


    @DisplayName("GET/ print json body")
    @Test
   public void Task1(){

        Response response = given().accept(ContentType.JSON)
                .and().get("posts");

        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
        //assertTrue(response.contentType().contains("application/json"));

   }


/**
   - Given accept type is Json
- Path param "id" value is 1
            - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
 ------------------------------------------------------------------
            - Then status code is 200
            -And json body contains "repellat provident"
            - And header ContentType - is Json
- And header "X-Powered-By" value is "Express"
            - And header "X-Ratelimit-Limit" value is 1000
            - And header "Age" value is more than 100

            - And header "NEL" value contains "success_fraction"
 */

@DisplayName("GET/ path param id value is 1")
@Test
public void Task2(){
    Response response = given().accept(ContentType.JSON)
            .and().pathParam("id", 1)
            .when().get("posts/{id}");

    assertEquals(HttpStatus.SC_OK, response.statusCode());

    assertTrue(response.body().asString().contains("repellat provident"));
    assertTrue(response.contentType().contains(ContentType.JSON.toString()));
    assertTrue(response.header("X-Powered-By").contains("Express"));
    assertTrue(response.header("X-Ratelimit-Limit").contains("1000"));
    assertTrue(Integer.parseInt(response.header("Age")) > 100);
    assertTrue(response.header("NEL").contains("success_fraction"));

    response.prettyPrint();
}

/**
 * - Given accept type is Json
 * - Path param "id" value is 12345
 * - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}
 * - Then status code is 404
 * - And json body contains "{}"
 */

@DisplayName("GET /path param id value is 12345")
@Test
public void Task3(){
    Response response = given().accept(ContentType.JSON)
            .and().pathParam("id", 12345)
            .when().get("posts/{id}");

    assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    assertTrue(response.contentType().contains(ContentType.JSON.toString()));

    response.prettyPrint();
}

/**
 * Q4:
 * - Given accept type is Json
 * - Path param "id" value is 2
 * - When user sends request to  https://jsonplaceholder.typicode.com/posts/{id}/comments
 * - Then status code is 200
 * - And header Content - Type is Json
 * - And json body contains "Presley.Mueller@myrl.com",  "Dallas@ole.me" , "Mallory_Kunze@marie.org"
 */

@DisplayName("GET  /posts/{id}/comments")
@Test
public void Task4(){

    Response response = given().accept(ContentType.JSON)
            .pathParam("id", 2)
            .when().get("posts/{id}/comments");

    assertEquals(HttpStatus.SC_OK, response.statusCode());
    assertTrue(response.contentType().contains(ContentType.JSON.toString()));
    assertTrue(response.body().asString().contains("Presley.Mueller@myrl.com"));
    assertTrue(response.body().asString().contains("Dallas@ole.me"));
    assertTrue(response.body().asString().contains("Mallory_Kunze@marie.org"));

    response.prettyPrint();

}

/**
 * - Given accept type is Json
 * - Query Param "postId" value is 1
 * - When user sends request to https://jsonplaceholder.typicode.com/comments
 * - Then status code is 200
 * - And header Content - Type is Json
 * - And header "Connection" value is "keep-alive"
 * - And json body contains "Lew@alysha.tv"
 */

    @DisplayName("GET /comments")
    @Test
    public void Task5(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("postId", 1)
                .when().get("comments");

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
        assertTrue(response.header("Connection").contains("keep-alive"));
        assertTrue(response.body().asString().contains("Lew@alysha.tv"));

        response.prettyPrint();
    }

    /**
     * - Given accept type is Json
     * - Query Param "postId" value is 333
     * - When user sends request to  https://jsonplaceholder.typicode.com/comments
     * - And header Content - Type is Json
     * - And header "Content-Length" value is 2
     * - And json body contains "[]"
     */

    @DisplayName("GET /comments")
    @Test
    public void Task6(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("postId", 333)
                .when().get("/comments");


        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
        assertTrue(response.header("Content-Length").contains("2"));

        response.prettyPrint();

    }







}


