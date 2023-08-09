package io.loopcamp.test.day11_b_access_token;
import io.loopcamp.pojo.Minion;

import io.loopcamp.util.MinionTestBase;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import io.loopcamp.util.MinionTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class DocuportAccessToken {

    /**
     Given accept type is Json
     And content type isJson
     And body is:
     {
     "usernameOrEmailAddress": "b1g1_client@gmail.com",
     "password": "Group1"
     }
     When I send POST request to
     Url: https://beta.docuport.app/api/v1/authentication/account/authenticate
     Then status code is 200
     And accessToken should be present and not empty
     */

    @Test
    public void docuportLoginTest(){
        //you can store this into a MAp and pass it as part of the Body as well
        String jsonBody = "{\n" +
                "     \"usernameOrEmailAddress\": \"b1g3_client@gmail.com\",\n" +
                "     \"password\": \"Group3\"\n" +
                "     }";

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                //some company may pass this username and password as the QUERY PARAM
                //It depends how the endpoint was build
                //
                .and().body(jsonBody)
                .when().post("https://beta.docuport.app/api/v1/authentication/account/authenticate");

       // response.prettyPrint();

        System.out.println("Status code: " + response.statusCode());
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));

        //Can you extract the token from response body

        String accessToken = response.path("user.jwtToken.accessToken");
        System.out.println("Access Token: " + accessToken);

        Assertions.assertTrue(accessToken !=null && !accessToken.isEmpty());

    }
}
