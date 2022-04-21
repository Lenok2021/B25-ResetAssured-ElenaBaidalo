package com.cydeo.day3;

import com.cydeo.utilities.SpartansTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartanTestWithJsonPath extends SpartansTestBase {


    /**
     * /*
     * Given accept type is json
     * And path param id is 10
     * When user sends a get request to "api/spartans/{id}"
     * Then status code is 200
     * And content-type is "application/json"
     * And response payload values match the following:
     * id is 10,
     * name is "Lorenza",
     * gender is "Female",
     * phone is 3312820936
     */


    @DisplayName("GET request to \"api/spartans/{id}\"")
    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("api/spartans/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());


        //print the name
        System.out.println(response.path("name").toString());


        // print name
        //response.jsonPath() = return type Json Object
        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.getString("name");
        System.out.println("name = " + name);

        // save into variables
        int id = jsonPath.getInt("id") ;
        String name1 = jsonPath.getString("name") ;
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");


        // assert
        assertEquals(10, id);
    }
}
