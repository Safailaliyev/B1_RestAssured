package io.loopcamp.test.APITask;
import io.loopcamp.util.HRApiTestBase;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;



public class HRPracticeTasks extends HRApiTestBase {
    /**
     * given accept is json
      * and content type is json
      * When I send post request to "/regions/"
      * With json:
      * {
      *     "region_id":100,
      *     "region_name":"Test Region"
      * }
      * Then status code is 201
      * content type is json
      * region_id is 100
      * region_name is Test Region
      */

     /**
       * given accept is json
       * When I send post request to "/regions/100"
       * Then status code is 200
       * content type is json
       * region_id is 100
       * region_name is Test Region
       */

     @Test
     public void postRegionsTest(){
         Map<String, Object> postRequest = new HashMap<>();
         postRequest.put("region_id", 100);
         postRequest.put("region_name", "Test Region");

         Response response = given().accept(ContentType.JSON)
                 .and().contentType(ContentType.JSON)
                 .and().body(postRequest)
                 .and().when().post("/regions");

         //response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
         assertThat(response.statusCode(), is(201));
         assertThat(response.contentType(),is(ContentType.JSON.toString()));
         assertThat(jsonPath.getString("region_id"), is(equalTo(100)+""));
         assertThat(jsonPath.getString("region_name"), is(equalTo("Test Region")));


         given().accept(ContentType.JSON)
                 .when().get("/regions/100");

         assertThat(response.statusCode(), is(200));
         assertThat(response.contentType(), is("application/json"));
         assertThat(response.jsonPath().getInt("region_id"), is(100));
         assertThat(response.jsonPath().getString("region_name"), is("Test Region"));







     }



}
