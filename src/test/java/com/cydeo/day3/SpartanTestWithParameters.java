package com.cydeo.day3;

import com.cydeo.utilities.SpartansTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.apache.commons.lang3.BooleanUtils.and;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTestWithParameters extends SpartansTestBase {

/*   Given accept type is Json
          And Id parameter value is 5
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload
       */


    @DisplayName("GET request to /api/spartans/{id}")
    @Test
    public void test1() {


        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("abc", 5)
                .when()
                .get("/api/spartans/{abc}");

        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json", response.contentType());
        //verify "Blythe" inside the payload
        assertTrue(response.body().asString().contains("Blythe"));

    }


    @Test
    public void test2() {

         /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
        */

        Response response = given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");


        // verify status code
        assertEquals(404, response.statusCode());

        //verify content type
        // assertEquals("application/json", response.contentType());
        assertEquals("application/json", response.header("Content-Type"));

        // verify message body
        assertTrue(response.body().asString().contains("Not Found"));

        System.out.println("response.prettyPrint() = " + response.prettyPrint());


    }
    // HOW TO PASS QUERY PARAMETERS WITH REST-ASSURED LIBRARY
    @DisplayName("GET request to /api/spartans/search")
    @Test
    public void test3() {
        /**
         *         Given accept type is Json
         *         And query parameter values are:
         *         gender|Female
         *         nameContains|e
         *         When user sends GET request to /api/spartans/search
         *         Then response status code should be 200
         *         And response content-type: application/json
         *         And "Female" should be in response payload
         *         And "Janette" should be in response payload
         */

        /**
         * IF YOU WANT TO GET ON CONSOLE YOUR INPUT
         * log().all()
         * log().parameters()
         * log().uri()
         *
         */


        Response response = given().log().all()

                .accept(ContentType.JSON)
                .and().queryParam("gender","Female")
                .and().queryParam("nameContains","e")
                .when().get("/api/spartans/search");

        assertEquals(200, response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));



    }
    @DisplayName("GET request to /api/spartans/search")
    @Test
    public void testCollection(){
        /**
         * same task  with Map
         */

        //create a map and store query params information
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        Response response = given().log().all().
                accept(ContentType.JSON).
                queryParams(queryMap)
                .when().
                get("/api/spartans/search");

        //verify status code
        assertEquals(200,response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());
        //verify Female inside body
        assertTrue(response.body().asString().contains("Female"));
        //verify Janette inside the json body
        assertTrue(response.body().asString().contains("Janette"));

    }


}
