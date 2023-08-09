package io.loopcamp.test.APITask;

import io.loopcamp.util.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;


import io.loopcamp.util.JsonPlaceHolder;

public class HomeworkPractice extends HRApiTestBase {


    /**
     * Q2:
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     * Q3:
     * - Given accept type is Json
     * -Query param value q= region_id 3
     * - When users sends request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore
     */

    @DisplayName("GET /countries - value-US ")
    @Test
    public void Task1() {
        /**
         * Given accept type is Json
         * - Path param value- US
         * - When users sends request to /countries
         * - Then status code is 200
         * - And Content - Type is Json
         * - And country_id is US
         * - And Country_name is United States of America
         * - And Region_id is 2
         */


        Response response = given().accept(ContentType.JSON)
                .and().pathParam("counrty_id", "US")
                .when().get("/countries/{counrty_id}");


        response.prettyPrint();

        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(response.contentType(), ContentType.JSON.toString());
//        assertTrue(response.body().asString().contains("United States of America"));
//        assertTrue(response.body().asString().contains("2"));
//        assertTrue(response.body().asString().contains("US"));

        JsonPath jsonPath = response.jsonPath();

        assertEquals("US", jsonPath.getString("country_id"));
        assertEquals("United States of America", jsonPath.getString("country_name"));
        assertEquals("10", jsonPath.getString("region_id"));

    }

    /**
     * Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */

    @DisplayName("GET /employees  ")
    @Test
            public void Test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q={department_id:80}")
                .when().get("/employees");
        JsonPath jsonPath = response.jsonPath();

        //response.prettyPrint();
        assertEquals(HttpStatus.SC_OK,response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals("job_ids",response.path ("SA"));
        assertEquals("80", response.path("department_ids"));
        assertEquals("25", response.path("count"));


    }


}

