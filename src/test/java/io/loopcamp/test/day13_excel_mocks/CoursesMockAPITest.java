package io.loopcamp.test.day13_excel_mocks;

import io.loopcamp.util.*;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CoursesMockAPITest {

    @BeforeAll
    public static void setUp () {
        baseURI = ConfigurationReader.getProperty("mock_server_api");
    }


    /**
     Given accept type is json
     When I send get request to /courses
     Then status code is 200
     And content type is json
     And body courseIds contain 1,2,3
     And courseNames are "Java SDET", "RPA Developer", "Salesforce Automation"
     */
    @DisplayName("GET /courses -- mock api server")
    @Test
    public void courseMockAPITest () {

        given().accept(ContentType.JSON)
                .when().get("/courses")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType(ContentType.JSON)
                .and().assertThat().body("courseId", hasItems(1,2,3))
                .and().assertThat().body("courseName", hasItems("Java SDET", "RPA Developer", "Salesforce Automation"))
                .log().all();

    }
}
