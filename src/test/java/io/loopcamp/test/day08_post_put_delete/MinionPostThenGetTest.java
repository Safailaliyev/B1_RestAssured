package io.loopcamp.test.day08_post_put_delete;
import io.loopcamp.pojo.Minion;
import io.loopcamp.util.MinionRestUtil;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

import com.github.javafaker.Country;
import io.loopcamp.util.HRApiTestBase;
import io.loopcamp.util.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;


import io.loopcamp.pojo.Minion;
import io.loopcamp.util.MinionRestUtil;

import static io.loopcamp.util.MinionRestUtil.*;


import io.loopcamp.pojo.Minion;
import io.loopcamp.util.MinionRestUtil;
import io.loopcamp.util.MinionRestUtil.*;

public class MinionPostThenGetTest extends MinionTestBase{

   Minion newMinion = MinionRestUtil.getNewMinion();

    /**
     * Post a new minion with API POST
     * Then use GEt method to get the info for the same Minion
     * Then assert if they match
     * abd DELETE this minion
     */

    @Test
   public void postNewMinionThenGetTest(){


       //Post a new minion with API POST

      Response response =  given().accept(ContentType.JSON)
               .and().contentType(ContentType.JSON)
               .and().body(newMinion)  //serialization from Pojo to Json
               .when().post("minions");

      response.prettyPrint();
      assertThat(response.statusCode(), is(HttpStatus.SC_CREATED));

      int newMinionID = response.jsonPath().getInt("data.id");


      //send GET request with the newMinionID and we can do assertion
        Map<String,Object> responseMap = MinionRestUtil.getMinionInMap(newMinionID);
        Minion newMinionFromGet = MinionRestUtil.getMinionInPojo(newMinionID);
        System.out.println("GET request body in Map: " + responseMap);
        System.out.println("GET request body in minion Pojo: " + newMinionFromGet);

        //Then assert if they match
        //Expected:
        //Actual:

        assertThat(newMinionFromGet.getGender(), equalTo(newMinion.getGender()));
        assertThat(newMinionFromGet.getName(), equalTo(newMinion.getName()));
        assertThat(newMinionFromGet.getPhone(), equalTo(newMinion.getPhone()));

        //And DELETE minion
        MinionRestUtil.deleteMinionById((newMinionID));




   }


}
