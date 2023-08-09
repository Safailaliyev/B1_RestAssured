package io.loopcamp.util;


import static io.restassured.RestAssured.baseURI;

import org.junit.jupiter.api.*;

public abstract class MinionTestBase {
    @BeforeAll
    public static void setUp(){ //I want this method to run before all the class/methods.
        baseURI= ConfigurationReader.getProperty("minion.api.url");

    }


}
