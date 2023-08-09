package io.loopcamp.test.day06_a_deserilaziation;

import io.loopcamp.util.JsonPlaceHolder;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class MinionsPojoTest extends MinionTestBase{
    /**
     Given accept type is json
     when I send GET request to /minions
     Then status code is 200
     And content type is json
     And I can convert json to list of minion pojos
     */

    @DisplayName("")
    @Test
    public void allMinionsToPojoTest(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/minions");

        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(response.contentType(), ContentType.JSON.toString());

        //How can I convert response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        //using jsonPath extract List of minions/deserialization
        List<Minion> allMinions = jsonPath.getList("",Minion.class);


        //List<Map<String, Objects>> allMinions2 = response.body().as(List.class); //This is same thing as above but we used POJO class since we have Minion.class

        //How can I get the size of how many Minions we have
        System.out.println("Count of Minions: " + allMinions.size());

        //Get the first minion
        System.out.println("First Minion: " + allMinions.get(0));

        //can you get all the Female minions and store it in another List.
        List<Minion> femaleMinions = new ArrayList<>();
        for (Minion each: allMinions){
            if (each.getGender().equals("Female")){
                femaleMinions.add(each);
            }
        }

        System.out.println("All Female Minions: " + femaleMinions);

        //We can use stream to filter and store the result
        List<Minion> femaleMinions2 = allMinions.stream()
                .filter(each -> each.getGender().equals("Female"))
                .collect(Collectors.toList());
        System.out.println("Female Minions: " + femaleMinions2);


        //How to get  how many Female minions there are?
        System.out.println("Count of Female Minions: " + femaleMinions.size());

        //Get me all the Male minions
        List<Minion> allMales = new ArrayList<>();
        allMinions.forEach(p->{
            if (p.getGender().equals("Male")){

            }
        });

        List<Minion> maleMinions = new ArrayList<>();
        for (Minion each : allMinions){
            if (each.getGender().equals("Male")){
                maleMinions.add(each);
            }
        }
        System.out.println("All male Minions: " + maleMinions);

        List<Minion> maleMinions2 = allMinions.stream()
                .filter(each -> each.equals("Male"))
                .collect(Collectors.toList());

        //How can i get the count of Male Minions
        System.out.println("All Male Minion Count: " +maleMinions2.size());

        //this is another way to get the count with string
        long countMale = allMinions.stream().filter(each->each.getGender().equals("Male")).count();
        System.out.println("All MAle Minion Number: " + countMale);

    }
}
