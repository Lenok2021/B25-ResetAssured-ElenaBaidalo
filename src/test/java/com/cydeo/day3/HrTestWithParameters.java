package com.cydeo.day3;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class HrTestWithParameters extends HrTestBase {

    @Test
    public void test1(){


        Response response = get("/regions");
        response.prettyPrint();

    }
    @DisplayName("GET request to \"/countries\"")
    @Test
    public void test2(){
        /**
         *  /*
         *         Given accept type is Json
         *         And parameters: q = {"region_id":2}
         *         When users sends a GET request to "/countries"
         *         Then status code is 200
         *         And Content type is application/json
         *         And Payload should contain "United States of America"
         *      */

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .queryParams("q","{\"region_id\":2}" )
                .log().all()
                .when().get("/countries") ;

       assertEquals(200,response.statusCode());
       assertEquals("application/json", response.contentType());
       assertTrue(response.body().asString().contains("United States of America"));

    }



}
