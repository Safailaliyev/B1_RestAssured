package io.loopcamp.test.day06_a_deserilaziation;

import io.loopcamp.pojo.MinionSearch;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.loopcamp.pojo.Minion;
import io.loopcamp.util.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class MinionSearchPojoTest extends MinionTestBase{

    /**
     Given accept type is json
     And query param nameContains value is "e"
     And query param gender value is "Female"
     When I send get request to /minions/search
     Then status code is 200
     And content type is Json
     And json response data is as expected
     */

    @DisplayName("GET/minions/search ")
    @Test
    public void minionSearchPojoTest(){
        Map<String, String>queryPar = new HashMap();
        queryPar.put("nameContains", "e");
        queryPar.put("gender", "Female");
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryPar)
                .when().get("/minions/search");

        //response.prettyPrint();

        assertEquals(200, response.getStatusCode());
        assertEquals(response.contentType(), ContentType.JSON.toString());

        //deserialize json payload body to MinionSearch pojo class
        MinionSearch minionSearch = response.as(MinionSearch.class);

        //Total elements
        System.out.println("Total Element: " + minionSearch.getTotalElement());
        System.out.println("All Minions:" + minionSearch.getContent());
        System.out.println("");





    }
}
