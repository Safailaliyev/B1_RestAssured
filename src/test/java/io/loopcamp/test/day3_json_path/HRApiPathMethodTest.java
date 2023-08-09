package io.loopcamp.test.day3_json_path;

import io.loopcamp.util.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class HRApiPathMethodTest extends HRApiTestBase {

    /**
     * Given accept is json
     * When I send GET request to /countries
     * ----------
     * Then status code is 200
     * And content type is json
     * Verify navigating through json array
     * Verify 1st county ID is AR
     * Verify 1st county Name is Argentina
     */
@DisplayName("Get/ countries with path")
@Test
    public void readCountriesUsingPathTest(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("ct", "countries")
                .when().get("/{ct}");
                //.when().get("/countries");

        assertEquals(HttpStatus.SC_OK, response.statusCode());


        //get me the first country id
    System.out.println("1st country id: " + response.path("items[0].country_id"));

    //get me the first country name
    System.out.println("1st country name: " + response.path("items[0].country_name"));

    assertEquals("AR", response.path("items[0].country_id"), "Id is not available");
    assertEquals("Argentina", response.path("items[0].country_name"), "Country name is not valid");


    //get the all countries names
    List<String> allCountryNames = response.path("items.country_name");
    System.out.println("All Country names: " + allCountryNames);

    }
}
