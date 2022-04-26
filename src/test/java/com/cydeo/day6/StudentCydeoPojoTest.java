package com.cydeo.day6;

import com.cydeo.utilities.StudentCydeoBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentCydeoPojoTest extends StudentCydeoBase {


    @Test
    public void test() {
        Response response = given()
                .accept(ContentType.JSON)
                .pathParam("id", 24103)
                .when()
                .get("/student/{id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json;charset=UTF-8", response.contentType());
        assertEquals("gzip", response.getHeader("Content-Encoding"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

        System.out.println("response.body().prettyPrint() = " + response.body().prettyPrint());

        JsonPath jsonPath = response.jsonPath();

        String firstName = jsonPath.getString("firstName");



        //payload/body verification
        /*
        firstName Karole                          --> students[0].firstName
        batch 7                                   --> students[0].batch
        major Master of Creative Arts             --> students[0].major
        emailAddress hassan.lebsack@hotmail.com   --> students[0].contact.emailAddress
        companyName Legacy Integration Analyst    --> students[0].company.companyName
        street 6253 Willard Place                 --> students[0].company.address.street
        zipCode 28524                             --> students[0].company.address.zipCode
         */

        assertEquals("Karole", jsonPath.getString("students[0].firstName"));

        assertEquals("Master of Creative Arts", jsonPath.getString("students[0].major"));
        assertEquals("hassan.lebsack@hotmail.com", jsonPath.getString("students[0].contact.emailAddress"));
        assertEquals("Legacy Integration Analyst", jsonPath.getString("students[0].company.companyName"));
        assertEquals("6253 Willard Place", jsonPath.getString("students[0].company.address.street"));
        assertEquals(28524, jsonPath.getInt("students[0].company.address.zipCode"));


    }


}