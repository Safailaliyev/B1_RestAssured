package io.loopcamp.util;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRApiTestBase {
    @BeforeAll
    public static void setUp(){ //I want this method to run before all the class/methods.
        baseURI= ConfigurationReader.getProperty("hr.api.url");

    }
}
