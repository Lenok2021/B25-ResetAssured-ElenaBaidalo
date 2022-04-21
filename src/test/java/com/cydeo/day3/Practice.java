package com.cydeo.day3;

import com.cydeo.utilities.SpartansTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Practice extends SpartansTestBase {


    @Test
    public void test1() {
/*
        Given Accept type is application/json
        When user send GET request to  /api/spartans end point
        Then status code must be 200
        And response content type must be application/json
     */

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans");

        String contentType = response.contentType();
        System.out.println(contentType);

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

    }

    @Test
    public void test2() {

        Response response = given()
                .accept(ContentType.XML)
                .when()
                .get("/api/spartans");

        String contentType = response.contentType();
        int statusCode = response.statusCode();
        System.out.println(contentType);
        System.out.println(statusCode);

        assertEquals(200, response.statusCode());
        assertEquals("application/xml", response.contentType());
        assertTrue(response.body().asString().contains("Meade"));

    }

    @Test
    public void test3() {
        // provide path parameters
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 76)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Arnie"));

    }

    @Test
    public void test4() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 33)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Wilek"));

        System.out.println("response.prettyPrint() = " + response.prettyPrint());
    }

    @Test
    public void test5() {

        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 66)
                .when()
                .get("/api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Quentin"));


    }

    @Test
    public void test6() {

        Response response = given()
                .accept(ContentType.JSON)
                .queryParams("gender", "Male")
                .when()
                .get("/api/spartans");


        assertEquals(200, response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Nels")) ;

    }

    @Test
    public void test7(){

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("gender","Female")
                .when().get("/api/spartans/search") ;




    }
}


