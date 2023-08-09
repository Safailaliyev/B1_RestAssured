package io.loopcamp.test.day07_a_hamcrest;
import io.loopcamp.util.HRApiTestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import io.loopcamp.util.MinionTestBase;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import java.time.LocalDate;
import static io.restassured.RestAssured.*;


public class ORDSHamcrestTest extends HRApiTestBase {
    /**
     given accept type is json
     when I send get request to /countries
     Then status code is 200
     and content type is json
     and count should be 25
     and country ids should contain "AR,AU,BE,BR,CA"
     and country names should have "Argentina,Australia,Belgium,Brazil,Canada"

     items[0].country_id ==> AR
     items[1].country_id ==> AU
     */

    @DisplayName("GET /countries --> hamsrest assertion")
    @Test
    public void countriesTest(){
        given().accept(ContentType.JSON)
                .when().get("/countries")
                .then().assertThat().statusCode(200)
                .and().assertThat().contentType(ContentType.JSON)
                .and().assertThat().body("count", is(25),
                "items.country_id", hasItems("AR", "AU", "BE", "BR", "CA"),
                        "items.country_name", hasItems("Argentina","Australia","Belgium","Brazil","Canada"),
                        "items[0].country_id", equalTo("AR"),
                        "items[1].country_id", is(equalTo("AU")));
    }
}
